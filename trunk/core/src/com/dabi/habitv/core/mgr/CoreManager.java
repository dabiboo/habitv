package com.dabi.habitv.core.mgr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dabi.habitv.config.entities.Config;
import com.dabi.habitv.config.entities.Downloader;
import com.dabi.habitv.config.entities.Exporter;
import com.dabi.habitv.config.entities.Proxy;
import com.dabi.habitv.config.entities.TaskDefinition;
import com.dabi.habitv.core.plugin.PluginFactory;
import com.dabi.habitv.core.token.TokenReplacer;
import com.dabi.habitv.framework.plugin.api.downloader.PluginDownloaderInterface;
import com.dabi.habitv.framework.plugin.api.dto.CategoryDTO;
import com.dabi.habitv.framework.plugin.api.dto.DownloaderDTO;
import com.dabi.habitv.framework.plugin.api.dto.ExportDTO;
import com.dabi.habitv.framework.plugin.api.dto.ExporterDTO;
import com.dabi.habitv.framework.plugin.api.dto.ProxyDTO;
import com.dabi.habitv.framework.plugin.api.dto.ProxyDTO.ProtocolEnum;
import com.dabi.habitv.framework.plugin.api.exporter.PluginExporterInterface;
import com.dabi.habitv.framework.plugin.api.provider.PluginProviderInterface;

public final class CoreManager {

	private CategoryManager categoryManager;

	private EpisodeManager episodeManager;

	private final Config config;

	private Collection<PluginProviderInterface> providerList;

	private final Map<String, Integer> buildTaskName2PoolSizeMap;

	private Map<String, Map<ProxyDTO.ProtocolEnum, ProxyDTO>> plugin2protocol2proxy;

	public CoreManager(final Config config) {
		this.config = config;
		final PluginFactory<PluginProviderInterface> pluginProviderFactory = new PluginFactory<>(PluginProviderInterface.class, config.getProviderPluginDir());
		providerList = pluginProviderFactory.getAllPlugin();
		buildTaskName2PoolSizeMap = buildTaskName2PoolSizeMap(config.getTaskDefinition());
		TokenReplacer.setCutSize(config.getFileNameCutSize());
		setProxy(config);
	}

	private void setProxy(final Config config) {
		plugin2protocol2proxy = new HashMap<>();
		buildProxyMap(config);

		// set the defaut http proxy
		final Map<ProtocolEnum, ProxyDTO> defaultProxyMap = plugin2protocol2proxy.get(null);
		if (defaultProxyMap != null) {
			final ProxyDTO httpProxy = defaultProxyMap.get(ProxyDTO.ProtocolEnum.HTTP);
			if (httpProxy != null) {
				setHttpProxy(httpProxy);
			}
		}

		// set the proxy to each plugin provider
		for (final PluginProviderInterface provider : providerList) {
			Map<ProtocolEnum, ProxyDTO> pluginProxy = plugin2protocol2proxy.get(provider.getName());
			if (pluginProxy == null) {
				pluginProxy = defaultProxyMap;
			}
			provider.setProxy(pluginProxy);
		}
	}

	private void buildProxyMap(final Config config) {
		final List<Proxy> configProxyList = config.getProxy();

		if (configProxyList != null && !configProxyList.isEmpty()) {
			for (final Proxy proxy : configProxyList) {
				if (proxy.getPluginSupport() != null) {
					for (final String plugin : proxy.getPluginSupport().getPlugin()) {
						setProxyByPluginName(proxy, plugin);
					}
				} else {
					// default proxy
					setProxyByPluginName(proxy, null);
				}
			}
		}
	}

	private void setProxyByPluginName(final Proxy proxy, final String plugin) {
		Map<ProxyDTO.ProtocolEnum, ProxyDTO> protocol2Proxy;
		protocol2Proxy = plugin2protocol2proxy.get(plugin);
		if (protocol2Proxy == null) {
			protocol2Proxy = new HashMap<>();
			plugin2protocol2proxy.put(plugin, protocol2Proxy);
		}
		protocol2Proxy.put(ProxyDTO.ProtocolEnum.valueOf(proxy.getProtocol()), new ProxyDTO(proxy.getHost(), proxy.getPort()));
	}

	private void setHttpProxy(final ProxyDTO httpProxy) {
		System.setProperty("http.proxyHost", httpProxy.getHost());
		System.setProperty("http.proxyPort", String.valueOf(httpProxy.getPort()));
	}

	public CategoryManager getCategoryManager() {
		if (categoryManager == null) {
			categoryManager = new CategoryManager(providerList, buildTaskName2PoolSizeMap);
		}
		return categoryManager;
	}

	private Map<String, Integer> buildTaskName2PoolSizeMap(final List<TaskDefinition> taskList) {
		final Map<String, Integer> taskName2PoolSizeMap = new HashMap<>();
		for (final TaskDefinition taskDefinition : taskList) {
			taskName2PoolSizeMap.put(taskDefinition.getTaskName(), taskDefinition.getSize());
		}
		return taskName2PoolSizeMap;
	}

	private List<ExportDTO> buildExporterListDTO(final List<Exporter> exporterList) {
		final List<ExportDTO> exportDTOList = new ArrayList<>(exporterList.size());
		ExportDTO exportDTO;
		String reference;
		String pattern;
		for (final Exporter exporter : exporterList) {

			if (exporter.getCondition() != null) {
				reference = exporter.getCondition().getReference();
				pattern = exporter.getCondition().getPattern();
			} else {
				reference = null;
				pattern = null;
			}
			exportDTO = new ExportDTO(reference, pattern, exporter.getName(), exporter.getOutput(), config.getCmdProcessor(), exporter.getCmd(),
					buildExporterListDTO(exporter.getExporter()));
			exportDTOList.add(exportDTO);
		}
		return exportDTOList;
	}

	public EpisodeManager getEpisodeManager() {
		if (episodeManager == null) {
			initEpisodeManager(providerList, buildTaskName2PoolSizeMap);
		}
		return episodeManager;
	}

	private Map<String, String> buildDownloadersBinPath(final List<Downloader> downloaders) {
		final Map<String, String> downloaderName2BinPath = new HashMap<>(downloaders.size());
		for (final Downloader downloader : downloaders) {
			downloaderName2BinPath.put(downloader.getName(), downloader.getBinPath());
		}
		return downloaderName2BinPath;
	}

	private void initEpisodeManager(final Collection<PluginProviderInterface> pluginProviderList, final Map<String, Integer> taskName2PoolSize) {
		// downloaders factory
		final PluginFactory<PluginDownloaderInterface> pluginDownloaderFactory = new PluginFactory<>(PluginDownloaderInterface.class,
				config.getDownloaderPluginDir());
		// map of downloaders by name
		final Map<String, PluginDownloaderInterface> downloaderName2downloader = pluginDownloaderFactory.getAllPluginMap();
		// downloaders bin path
		final Map<String, String> downloaderName2BinPath = buildDownloadersBinPath(config.getDownloader());
		// DL DTO
		final DownloaderDTO downloader = new DownloaderDTO(config.getCmdProcessor(), downloaderName2downloader, downloaderName2BinPath,
				config.getDownloadOuput(), config.getIndexDir());
		// exporters factory
		final PluginFactory<PluginExporterInterface> pluginExporterFactory = new PluginFactory<>(PluginExporterInterface.class, config.getExporterPluginDir());
		// map of exporters by name
		final Map<String, PluginExporterInterface> exporterName2exporter = pluginExporterFactory.getAllPluginMap();
		// export DTO
		final ExporterDTO exporter = new ExporterDTO(exporterName2exporter, buildExporterListDTO(config.getExporter()));

		// manager
		episodeManager = new EpisodeManager(downloader, exporter, pluginProviderList, taskName2PoolSize, config.getMaxAttempts());
	}

	public void retreiveEpisode(final Map<String, Set<CategoryDTO>> categoriesToGrab) {
		getEpisodeManager().retreiveEpisode(categoriesToGrab);
	}

	public Map<String, Set<CategoryDTO>> findCategory() {
		return getCategoryManager().findCategory();
	}

	public void forceEnd() {
		if (episodeManager != null) {
			episodeManager.forceEnd();
		}
		if (categoryManager != null) {
			categoryManager.forceEnd();
		}
	}

	public void reDoExport() {
		episodeManager.reTryExport();
	}

	public boolean hasExportToResume() {
		return episodeManager.hasExportToResume();
	}

	public void clearExport() {
		episodeManager.clearExport();
	}

	public void reloadPlugin() {
		final PluginFactory<PluginProviderInterface> pluginProviderFactory = new PluginFactory<>(PluginProviderInterface.class, config.getProviderPluginDir());
		providerList = pluginProviderFactory.getAllPlugin();
		episodeManager.setPluginProviderList(providerList);
	}

}
