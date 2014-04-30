package com.dabi.habitv.downloader.cmdexecutor;

import java.util.Map;

import com.dabi.habitv.framework.FrameworkConf;
import com.dabi.habitv.framework.plugin.api.PluginDownloaderInterface;
import com.dabi.habitv.framework.plugin.api.dto.ProxyDTO;
import com.dabi.habitv.framework.plugin.exception.DownloadFailedException;
import com.dabi.habitv.framework.plugin.exception.ExecutorFailedException;
import com.dabi.habitv.framework.plugin.utils.CmdExecutor;
import com.dabi.habitv.framework.plugin.utils.CmdProgressionListener;

public final class CmdExecutorDownloaderManager implements PluginDownloaderInterface { // NO_UCD
	// (unused
	// code)

	@Override
	public String getName() {
		return CmdDownloaderConf.NAME;
	}

	@Override
	public void download(final String downloadInput, final String downloadDestination, final Map<String, String> parameters,
			final CmdProgressionListener listener, final Map<ProxyDTO.ProtocolEnum, ProxyDTO> proxies) throws DownloadFailedException {
		final String cmd = parameters.get("cmd");
		if (cmd == null) {
			throw new IllegalArgumentException("cmd parameters must be defined");
		}
		replaceIfContains(cmd, FrameworkConf.DOWNLOAD_INPUT, downloadInput);
		replaceIfContains(cmd, FrameworkConf.DOWNLOAD_DESTINATION, downloadDestination);

		try {
			new CmdExecutor(parameters.get(FrameworkConf.CMD_PROCESSOR), cmd, CmdDownloaderConf.MAX_HUNG_TIME, listener).execute();
		} catch (final ExecutorFailedException e) {
			throw new DownloadFailedException(e);
		}
	}

	private String replaceIfContains(final String cmd, final String paramPattern, final String param) {
		if (cmd.contains(param)) {
			return cmd.replaceFirst(paramPattern, param);
		} else {
			throw new IllegalArgumentException("cmd param must defined the param pattern " + paramPattern);
		}
	}

}
