package com.dabi.habitv.exporter.curl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dabi.habitv.framework.plugin.utils.CmdExecutor;
import com.dabi.habitv.framework.plugin.utils.CmdProgressionListener;

public class CurlCmdExecutor extends CmdExecutor {

	private static final Pattern FIRST_PATTERN = Pattern.compile("\\s*(\\d*\\.*\\d*)\\s+.*$");
	
	private static final Pattern SECOND_PATTERN = Pattern.compile("^\\s*(\\d+).*");
	
	public CurlCmdExecutor(final String cmdProcessor, final String cmd, final CmdProgressionListener listener) {
		super(cmdProcessor, cmd, CurlConf.MAX_HUNG_TIME, listener);
	}

	@Override
	protected String handleProgression(final String line) {
		// % Total % Received % Xferd Average Speed Time Time Time Current
		// 3 17.6M 3 561k 0 0 705k 0 0:00:25 --:--:-- 0:00:25 799k
		Matcher matcher = FIRST_PATTERN.matcher(line);
		// lancement de la recherche de toutes les occurrences
		boolean hasMatched = matcher.find();
		// si recherche fructueuse
		String ret = null;
		if (hasMatched) {
			ret = matcher.group(matcher.groupCount());
		} else {
			matcher = SECOND_PATTERN.matcher(line);
			// lancement de la recherche de toutes les occurrences
			hasMatched = matcher.find();
			if (hasMatched) {
				ret = matcher.group(matcher.groupCount());
			}
		}
		return ret;
	}

	@Override
	protected boolean isSuccess(final String fullOutput) {
		return getLastOutputLine().matches("\\s*(100)\\s+.*$");
	}

}