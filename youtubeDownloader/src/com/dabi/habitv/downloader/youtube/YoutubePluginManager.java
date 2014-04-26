package com.dabi.habitv.downloader.youtube;

import java.util.Map;

import com.dabi.habitv.framework.FrameworkConf;
import com.dabi.habitv.framework.plugin.api.downloader.PluginDownloaderInterface;
import com.dabi.habitv.framework.plugin.api.dto.ProxyDTO;
import com.dabi.habitv.framework.plugin.exception.DownloadFailedException;
import com.dabi.habitv.framework.plugin.exception.ExecutorFailedException;
import com.dabi.habitv.framework.plugin.utils.CmdProgressionListener;
import com.dabi.habitv.framework.plugin.utils.OSUtils;

public class YoutubePluginManager implements PluginDownloaderInterface {

	@Override
	public String getName() {
		return YoutubeConf.NAME;
	}

	@Override
	public void setClassLoader(final ClassLoader classLoader) {
		// no need
	}

	@Override
	public void download(final String downloadInput, final String downloadDestination, final Map<String, String> parameters,
			final CmdProgressionListener listener, final Map<ProxyDTO.ProtocolEnum, ProxyDTO> proxies) throws DownloadFailedException {
		String binParam = parameters.get(FrameworkConf.PARAMETER_BIN_PATH);
		if (binParam == null) {
			if (OSUtils.isWindows()){
				binParam = YoutubeConf.DEFAULT_WINDOWS_BIN_PATH;
			} else {
				binParam = YoutubeConf.DEFAULT_LINUX_BIN_PATH;
			}
		}
		String cmd = binParam + " ";
		final String cmdParam = parameters.get(FrameworkConf.PARAMETER_ARGS);
		if (cmdParam == null) {
			cmd += YoutubeConf.DUMP_CMD;
		} else {
			cmd += cmdParam;
		}
		cmd = cmd.replaceFirst(FrameworkConf.DOWNLOAD_INPUT, downloadInput);
		cmd = cmd.replaceFirst(FrameworkConf.DOWNLOAD_DESTINATION, downloadDestination);
		//		if (proxyDTO!=null){
		//			TODO youtube-dl supports downloading videos through a proxy, by setting the http_proxy environment variable to the proxy URL, as in http://proxy_machine_name:port/.
		//		}

		try {
			(new YoutubeDLCmdExecutor(parameters.get(FrameworkConf.CMD_PROCESSOR), cmd, listener)).execute();
		} catch (final ExecutorFailedException e) {
			throw new DownloadFailedException(e);
		}
	}

}
