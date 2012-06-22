package com.dabi.habitv.provider.m6;

public final class M6Conf {

	private M6Conf() {

	}

	public static final String NAME = "m6";
	public static final String ENCRYPTION = "DES";
	public static final String CATALOG_URL = "http://www.m6replay.fr/catalogue/catalogueWeb3.xml";
	public static final String SECRET_KEY = "ElFsg.Ot";
	public static final String PACKAGE_NAME = "com.dabi.habitv.provider.m6.entities";
	public static final String DUMP_CMD_OLD = "-r \"rtmpe://groupemsix.fcod.llnwd.net/a2883/d1/#VIDEO_URL#\" --port 1935 --swfVfy \"http://l3.player.m6.fr/swf/ReplayPlayerV2Hds.swf\" --swfAge 0 -o \"#FILE_DEST#\"";
	public static final String DUMP_CMD = "-l 2 -n m6replayfs.fplive.net -a \"m6replaytoken/streaming?#TOKEN#\" -y \"#VIDEO_URL#?#TOKEN#\" --port 1935 --swfVfy \"http://l3.player.m6.fr/swf/ReplayPlayerV2Hds.swf\" --swfAge 0 -o  \"#FILE_DEST#\"";
	public static final String RTMDUMP = "rtmpdump";
	public static final String EXTENSION = "mp4";
	public static final String TOKEN_URL = "http://www.m6replay.fr/tokenrtmp_2-8-10-2.php?cdn=lv3&videoUrl=";

}
