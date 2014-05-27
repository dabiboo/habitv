package com.dabi.habitv;

import java.awt.SystemTray;
import java.io.File;

import org.fuin.utils4j.Utils4J;

import com.dabi.habitv.console.ConsoleLauncher;
import com.dabi.habitv.framework.plugin.utils.OSUtils;
import com.dabi.habitv.tray.HabiTvSplashScreen;

public class HabitvLauncher {

	public static void main(final String[] args) throws Exception {
		Utils4J.addToClasspath("file:///"+System.getProperty("java.home")+ File.separator+"lib"+File.separator+"jfxrt.jar");
		
		if (OSUtils.isWindows() && SystemTray.isSupported()
				&& (args == null || args.length == 0)) {
			// TrayLauncher.main(args);
			HabiTvSplashScreen.main(args);
		} else {
			ConsoleLauncher.main(args);
		}
	}

}
