package com.dabi.habitv.core.mgr;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.dabi.habitv.api.plugin.dto.CategoryDTO;
import com.dabi.habitv.api.plugin.dto.ProxyDTO;
import com.dabi.habitv.api.plugin.dto.ProxyDTO.ProtocolEnum;
import com.dabi.habitv.core.config.UserConfig;
import com.dabi.habitv.core.token.TokenReplacer;
import com.dabi.habitv.framework.FWKProperties;

public final class CoreManager {

	private final CategoryManager categoryManager;

	private final EpisodeManager episodeManager;

	private final Map<String, Integer> taskName2PoolSizeMap;

	private static final Logger LOG = Logger.getLogger(CoreManager.class);

	private final PluginManager pluginManager;

	public CoreManager(final UserConfig config) {
		LOG.info("habitv version " + FWKProperties.getVersion());
		taskName2PoolSizeMap = config.getTaskDefinition();
		TokenReplacer.setCutSize(config.getFileNameCutSize());
		pluginManager = new PluginManager(config);
		episodeManager = new EpisodeManager(pluginManager.getDownloadersHolder(), pluginManager.getExportersHolder(), pluginManager.getProvidersHolder(),
				taskName2PoolSizeMap, config.getMaxAttempts());
		categoryManager = new CategoryManager(pluginManager.getProvidersHolder(), taskName2PoolSizeMap);

		setProxy(config);
	}

	private void setProxy(final UserConfig config) {
		final Map<String, Map<ProtocolEnum, ProxyDTO>> plugin2protocol2proxy = config.getProxy();

		// set the defaut http proxy
		final Map<ProtocolEnum, ProxyDTO> defaultProxyMap = plugin2protocol2proxy.get(null);
		if (defaultProxyMap != null) {
			final ProxyDTO httpProxy = defaultProxyMap.get(ProxyDTO.ProtocolEnum.HTTP);
			if (httpProxy != null) {
				setHttpProxy(httpProxy);
			}
		}

		pluginManager.setProxy(defaultProxyMap, plugin2protocol2proxy);
	}

	private void setHttpProxy(final ProxyDTO httpProxy) {
		System.setProperty("http.proxyHost", httpProxy.getHost());
		System.setProperty("http.proxyPort", String.valueOf(httpProxy.getPort()));
	}

	public CategoryManager getCategoryManager() {
		return categoryManager;
	}

	public EpisodeManager getEpisodeManager() {
		return episodeManager;
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

	public void update() {
		pluginManager.update();
	}

	public PluginManager getPluginManager() {
		return pluginManager;
	}

}