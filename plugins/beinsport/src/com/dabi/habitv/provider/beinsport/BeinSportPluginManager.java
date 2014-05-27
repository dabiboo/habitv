package com.dabi.habitv.provider.beinsport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.dabi.habitv.api.plugin.api.PluginProviderDownloaderInterface;
import com.dabi.habitv.api.plugin.dto.CategoryDTO;
import com.dabi.habitv.api.plugin.dto.DownloadParamDTO;
import com.dabi.habitv.api.plugin.dto.EpisodeDTO;
import com.dabi.habitv.api.plugin.exception.DownloadFailedException;
import com.dabi.habitv.api.plugin.exception.TechnicalException;
import com.dabi.habitv.api.plugin.holder.DownloaderPluginHolder;
import com.dabi.habitv.api.plugin.holder.ProcessHolder;
import com.dabi.habitv.framework.FrameworkConf;
import com.dabi.habitv.framework.plugin.api.BasePluginWithProxy;
import com.dabi.habitv.framework.plugin.utils.DownloadUtils;
import com.dabi.habitv.framework.plugin.utils.SoccerUtils;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class BeinSportPluginManager extends BasePluginWithProxy implements
		PluginProviderDownloaderInterface { // NO_UCD

	@Override
	public String getName() {
		return BeinSportConf.NAME;
	}

	@Override
	public Set<EpisodeDTO> findEpisode(final CategoryDTO category) {
		switch (category.getId()) {
		case BeinSportConf.VIDEOS_CATEGORY:
			return findEpisodeByRSS(category, BeinSportConf.VIDEOS_URL_RSS);
		case BeinSportConf.REPLAY_CATEGORY:
			final Set<EpisodeDTO> episodeDTOs = new HashSet<>();
			for (final CategoryDTO subCategory : findReplaycategories()) {
				episodeDTOs.addAll(findEpisodeByCategory(subCategory,
						BeinSportConf.HOME_URL + subCategory.getId()));
			}
			return episodeDTOs;
		default:
			return findEpisodeByCategory(category, BeinSportConf.HOME_URL
					+ category.getId());
		}
	}

	private Set<EpisodeDTO> findEpisodeByRSS(final CategoryDTO category,
			final String videosUrl) {
		final Set<EpisodeDTO> episodeList;
		try {

			final SyndFeedInput input = new SyndFeedInput();
			final SyndFeed feed = input.build(new XmlReader(
					getInputStreamFromUrl(videosUrl)));
			episodeList = convertFeedToEpisodeList(feed, category);
		} catch (IllegalArgumentException | FeedException | IOException e) {
			throw new TechnicalException(e);
		}
		return episodeList;
	}

	private static Set<EpisodeDTO> convertFeedToEpisodeList(
			final SyndFeed feed, final CategoryDTO category) {
		final Set<EpisodeDTO> episodeList = new HashSet<EpisodeDTO>();
		final List<?> entries = feed.getEntries();
		if (!entries.isEmpty()) {
			for (final Object object : entries) {
				final SyndEntry entry = (SyndEntry) object;
				final List<?> enclosures = entry.getEnclosures();
				String url;
				if (!enclosures.isEmpty()) {
					url = ((SyndEnclosure) enclosures.get(0)).getUrl();
				} else {
					url = entry.getLink();
				}
				final String name = SoccerUtils.maskScore(entry.getTitle());
				episodeList.add(new EpisodeDTO(category, name, url));
			}
		}
		return episodeList;
	}

	@Override
	public Set<CategoryDTO> findCategory() {
		final Set<CategoryDTO> categoryDTOs = new HashSet<>();
		CategoryDTO videoCategory = new CategoryDTO(BeinSportConf.VIDEOS_CATEGORY,
				BeinSportConf.VIDEOS_CATEGORY, BeinSportConf.VIDEOS_CATEGORY,
				BeinSportConf.EXTENSION);
		videoCategory.setDownloadable(true);
		categoryDTOs.add(videoCategory);
		final CategoryDTO replayCategory = new CategoryDTO(
				BeinSportConf.REPLAY_CATEGORY, BeinSportConf.REPLAY_CATEGORY,
				BeinSportConf.REPLAY_CATEGORY, BeinSportConf.EXTENSION);
		replayCategory.addSubCategories(findReplaycategories());
		categoryDTOs.add(replayCategory);
		return categoryDTOs;
	}

	@Override
	public ProcessHolder download(final DownloadParamDTO downloadParam,
			final DownloaderPluginHolder downloaders)
			throws DownloadFailedException {
		if (downloadParam.getDownloadInput().endsWith(FrameworkConf.MP4)) {
			return DownloadUtils.download(downloadParam, downloaders);
		} else {
			final String finalVideoUrl = findFinalRtmpUrl(downloadParam
					.getDownloadInput());
			final String[] tab = finalVideoUrl.split("/");
			final String contextRoot = tab[3];
			final String rtmpdumpCmd = BeinSportConf.RTMPDUMP_CMD2
					.replace("#PROTOCOL#", tab[0]).replace("#HOST#", tab[2])
					.replaceAll("#CONTEXT_ROOT#", contextRoot);
			final String relativeUrl = finalVideoUrl.substring(finalVideoUrl
					.indexOf("/" + contextRoot + "/") + 1);

			downloadParam.addParam(FrameworkConf.PARAMETER_ARGS, rtmpdumpCmd);
			return DownloadUtils.download(DownloadParamDTO.buildDownloadParam(
					downloadParam, relativeUrl), downloaders,
					FrameworkConf.RTMDUMP);
		}
	}

	private static final Pattern VIDEOID_PATTERN = Pattern
			.compile(".*videoId\\s+=\\s+\\\"(.*)\\\";.*");

	private Set<EpisodeDTO> findEpisodeByCategory(final CategoryDTO category,
			final String url) {
		final Set<EpisodeDTO> episodeList = new HashSet<>();

		final org.jsoup.nodes.Document doc = Jsoup.parse(getUrlContent(url));

		Elements divTabContainer = doc.select("#tabContainer");
		if (divTabContainer.isEmpty()) {
			divTabContainer = doc.select("#newsIndex");
		}

		for (final Element article : divTabContainer.select("article")) {
			final Element aHref = article.select("a").first();
			final Element h4 = article.select("h4").first();
			final String href = aHref.attr("href");
			String name = h4.text();
			name = SoccerUtils.maskScore(name);
			episodeList.add(new EpisodeDTO(category, name, href));
		}

		return episodeList;
	}

	private Collection<CategoryDTO> findReplaycategories() {
		final Set<CategoryDTO> categories = new HashSet<>();

		final org.jsoup.nodes.Document doc = Jsoup
				.parse(getUrlContent(BeinSportConf.REPLAY_URL));

		final Elements divTabContainer = doc.select("#tabContainer");
		for (final Element h2 : divTabContainer.select("h2")) {
			final Element aHref = h2.child(0);
			final String href = aHref.attr("href");
			final String title = aHref.text();
			CategoryDTO category = new CategoryDTO(BeinSportConf.NAME, title, href,
					BeinSportConf.EXTENSION);
			category.setDownloadable(true);
			categories.add(category);
		}
		return categories;
	}

	private String findFinalRtmpUrl(final String url) {
		final String content = getUrlContent(url.startsWith("http") ? url
				: (BeinSportConf.HOME_URL + url));
		final String clipId = findMediaId(content);
		ArrayList<String> urlList;
		try {
			urlList = findUrlList(clipId);
		} catch (XPathExpressionException | ParserConfigurationException
				| SAXException | IOException e) {
			throw new TechnicalException(e);
		}
		if (!urlList.isEmpty()) {
			return urlList.get(urlList.size() - 1);
		} else {
			throw new TechnicalException("No link found");
		}
	}

	private ArrayList<String> findUrlList(final String clipId)
			throws ParserConfigurationException, XPathExpressionException,
			SAXException, IOException {
		final XPathFactory factory = XPathFactory.newInstance();
		final XPath xpath = factory.newXPath();
		final XPathExpression expr = xpath.compile("//file");

		final DocumentBuilderFactory domFactory = DocumentBuilderFactory
				.newInstance();
		domFactory.setNamespaceAware(true);
		final DocumentBuilder builder = domFactory.newDocumentBuilder();
		final Document doc = builder
				.parse(getInputStreamFromUrl(BeinSportConf.XML_INFO + clipId));

		final NodeList nodes = (NodeList) expr.evaluate(doc,
				XPathConstants.NODESET);
		final ArrayList<String> urlList = new ArrayList<>();
		for (int i = 0; i < nodes.getLength(); i++) {
			urlList.add(nodes.item(i).getAttributes()
					.getNamedItem("externalPath").getTextContent());
		}

		return urlList;
	}

	private static String findMediaId(final String content) {

		final Matcher matcher = VIDEOID_PATTERN.matcher(content);
		final boolean hasMatched = matcher.find();
		String ret = null;
		if (hasMatched) {
			ret = matcher.group(matcher.groupCount());
		} else {
			throw new TechnicalException("can't find mediaId");
		}
		return ret;
	}

	@Override
	public DownloadableState canDownload(final String downloadInput) {
		return DownloadableState.IMPOSSIBLE;
	}

}