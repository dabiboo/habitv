package com.dabi.habitv.provider.pluzz;

import java.util.Collection;
import java.util.Set;
import java.util.LinkedHashSet;

import com.dabi.habitv.api.plugin.api.PluginProviderDownloaderInterface;
import com.dabi.habitv.api.plugin.dto.CategoryDTO;
import com.dabi.habitv.api.plugin.dto.DownloadParamDTO;
import com.dabi.habitv.api.plugin.dto.EpisodeDTO;
import com.dabi.habitv.api.plugin.exception.DownloadFailedException;
import com.dabi.habitv.api.plugin.holder.DownloaderPluginHolder;
import com.dabi.habitv.api.plugin.holder.ProcessHolder;
import com.dabi.habitv.framework.plugin.api.BasePluginWithProxy;
import com.dabi.habitv.framework.plugin.utils.DownloadUtils;
import com.dabi.habitv.framework.plugin.utils.M3U8Utils;
import com.dabi.habitv.provider.pluzz.jpluzz.Archive;
import com.dabi.habitv.provider.pluzz.jpluzz.JsonArchiveParser;

public class PluzzPluginManager extends BasePluginWithProxy implements
		PluginProviderDownloaderInterface {

	private Archive cachedArchive;

	private long cachedTimeMs;

	private JsonArchiveParser jsonArchiveParser;

	@Override
	public String getName() {
		return PluzzConf.NAME;
	}

	@Override
	public Set<EpisodeDTO> findEpisode(final CategoryDTO category) {
		final Set<EpisodeDTO> episodeList = new LinkedHashSet<>();
		if (!category.getSubCategories().isEmpty()) {
			for (final CategoryDTO subCat : category.getSubCategories()) {
				episodeList.addAll(findEpisode(subCat));
			}
		}
		final Collection<EpisodeDTO> collection = getCachedArchive()
				.getCatName2Episode().get(category.getId());
		if (collection != null) {
			for (final EpisodeDTO episode : collection) {
				final EpisodeDTO newEp = new EpisodeDTO(category,
						episode.getName(), episode.getId());
				newEp.setNum(episode.getNum());
				episodeList.add(newEp);
			}
		}
		return episodeList;
	}

	public JsonArchiveParser getJsonArchiveParser() {
		if (jsonArchiveParser == null) {
			jsonArchiveParser = new JsonArchiveParser(PluzzConf.ZIP_URL,
					getHttpProxy());
		}
		return jsonArchiveParser;
	}

	private Archive getCachedArchive() {
		final long now = System.currentTimeMillis();
		if (cachedArchive == null
				|| (now - cachedTimeMs) > PluzzConf.MAX_CACHE_ARCHIVE_TIME_MS) {
			cachedArchive = getJsonArchiveParser().load();
			cachedTimeMs = now;
		}
		return cachedArchive;
	}

	@Override
	public Set<CategoryDTO> findCategory() {
		return new LinkedHashSet<>(getCachedArchive().getCategories());
	}

	@Override
	public ProcessHolder download(final DownloadParamDTO downloadParam,
			final DownloaderPluginHolder downloaders)
			throws DownloadFailedException {
		final String videoUrl = M3U8Utils.keepBestQuality(PluzzConf.BASE_URL
				+ downloadParam.getDownloadInput());
		return DownloadUtils.download(
				DownloadParamDTO.buildDownloadParam(downloadParam, videoUrl),
				downloaders);
	}

	@Override
	public DownloadableState canDownload(final String downloadInput) {
		return DownloadableState.IMPOSSIBLE;
	}

}