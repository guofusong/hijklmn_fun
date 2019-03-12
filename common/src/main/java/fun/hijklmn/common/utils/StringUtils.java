package fun.hijklmn.common.utils;

public class StringUtils {

	public static String[] split(String content, String regex) {
		return content.split(regex);
	}

	public static String getSuffix(String content, String regex) {
		String[] s = content.split(regex);
		return s[s.length - 1];
	}

	public static String getPerfix(String content, String regex) {
		String[] s = content.split(regex);
		return content.substring(content.length() - s[s.length - 1].length(), content.length());
	}

}
