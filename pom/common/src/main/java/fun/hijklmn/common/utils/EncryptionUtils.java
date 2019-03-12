package fun.hijklmn.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * tips : 加密工具<br>
 * description :
 *
 * @author : guosong
 * @date : 2018年10月27日
 * @time : 上午10:21:53
 * @project_name : common
 * @package_name : fun.hijklmn.common.utils
 *
 */
public class EncryptionUtils {

	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };

	private static final String MD5ALGORITHM = "MD5";

	private static final String SHA1ALGORITHM = "SHA-1";

	private static String generatorHEXText(byte[] b) {
		int len = b.length;
		StringBuilder buf = new StringBuilder(len * 2);
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(b[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[b[j] & 0x0f]);
		}
		return buf.toString();
	}

	public static String MD5(String content, String salt) {
		if (StringUtils.isBlank(content)) {
			return null;
		}
		if (StringUtils.isNotBlank(salt)) {
			content += salt;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(MD5ALGORITHM);
			messageDigest.update(content.getBytes());
			return generatorHEXText(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public static String SHA1(String content, String salt) {
		if (StringUtils.isBlank(content)) {
			return null;
		}
		if (StringUtils.isNotBlank(salt)) {
			content += salt;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(SHA1ALGORITHM);
			messageDigest.update(content.getBytes());
			return generatorHEXText(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(EncryptionUtils.MD5("123456", null));
	}

}
