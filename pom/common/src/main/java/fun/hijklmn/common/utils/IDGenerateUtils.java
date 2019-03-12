package fun.hijklmn.common.utils;

public class IDGenerateUtils {

	public static String generateSysUserId() {
		return RandomUtils.generateUUID().substring(0, 8) + DateUtils.curDateStr("yyMMddHHmmss");
	}

	public static String generateDocumentId() {
		return RandomUtils.generateUUID().substring(0, 20) + DateUtils.curDateStr("yyMMddHHmmss");
	}

	public static String generateSoundId() {
		return RandomUtils.generateUUID().substring(0, 20) + DateUtils.curDateStr("yyMMddHHmmss");
	}

	public static String generateMenuId() {
		return RandomUtils.generateUUID().substring(0, 10) + DateUtils.curDateStr("yyMMddHHmm");
	}

	public static String generateMediaId() {
		return RandomUtils.generateUUID().substring(0, 18) + DateUtils.curDateStr("yyyyMMddHHmmss");
	}

	public static void main(String[] args) {
		System.out.println(IDGenerateUtils.generateMenuId());
	}

}
