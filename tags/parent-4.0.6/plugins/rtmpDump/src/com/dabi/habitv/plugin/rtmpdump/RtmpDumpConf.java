package com.dabi.habitv.plugin.rtmpdump;

public final class RtmpDumpConf {

	private RtmpDumpConf() {

	}

	public static final String NAME = "rtmpdump";

	public static final String DUMP_CMD = " --resume -r \"#VIDEO_URL#\" -o \"#FILE_DEST#\"";

	public static final long MAX_HUNG_TIME = 300000L;

	public static final long HUNG_PROCESS_TIME = 30000L;

}
