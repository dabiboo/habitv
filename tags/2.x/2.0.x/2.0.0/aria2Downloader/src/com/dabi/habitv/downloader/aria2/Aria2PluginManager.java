package com.dabi.habitv.downloader.aria2;

import java.util.Map;

import com.dabi.habitv.framework.plugin.api.CmdProgressionListener;
import com.dabi.habitv.framework.plugin.api.PluginDownloaderInterface;
import com.dabi.habitv.framework.plugin.exception.DownloadFailedException;
import com.dabi.habitv.framework.plugin.exception.ExecutorFailedException;
import com.dabi.habitv.framework.plugin.utils.FrameworkConf;

public class Aria2PluginManager implements PluginDownloaderInterface {

	@Override
	public String getName() {
		return Aria2Conf.NAME;
	}

	@Override
	public void setClassLoader(final ClassLoader classLoader) {
		// no need
	}

	@Override
	public void download(final String downloadInput, final String downloadDestination, final Map<String, String> parameters,
			final CmdProgressionListener listener) throws DownloadFailedException {

		String binParam = parameters.get(FrameworkConf.PARAMETER_BIN_PATH);
		if (binParam == null) {
			throw new IllegalArgumentException("bin path parameters must be defined");
		}
		String cmd = binParam + " ";
		String cmdParam = parameters.get(FrameworkConf.PARAMETER_ARGS);
		if (cmdParam == null) {
			cmd += Aria2Conf.CMD;
		} else {
			cmd += cmdParam;
		}
		cmd = cmd.replaceFirst(FrameworkConf.DOWNLOAD_INPUT, downloadInput);

		int lastSlashIndex = downloadDestination.lastIndexOf("/");
		String fileName = downloadDestination.substring(lastSlashIndex + 1, downloadDestination.length());
		String dirDest = downloadDestination.substring(0, lastSlashIndex);

		cmd = cmd.replaceFirst(Aria2Conf.FILE_NAME, fileName);
		cmd = cmd.replaceFirst(Aria2Conf.DIR_DEST, dirDest);
		try {
			(new Aria2CmdExecutor(cmd, listener)).execute();
		} catch (ExecutorFailedException e) {
			throw new DownloadFailedException(e);
		}
	}

}