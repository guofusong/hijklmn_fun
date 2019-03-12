package fun.hijklmn.common.utils;

import java.util.Base64;

/**
 * 
 * tips : 下面的代码用于练习<br>
 * description :
 *
 * @author : guosong
 * @date : 2018年10月31日
 * @time : 下午9:55:09
 * @project_name : common
 * @package_name : fun.hijklmn.common.utils
 *
 */
public class EncodeUtils {

	public static String encode(byte[] b) {
		return Base64.getEncoder().encodeToString(b);
	}

	public static byte[] decode(byte[] b) {
		return Base64.getDecoder().decode(b);
	}

	public static byte[] decode(String content) {
		return Base64.getDecoder().decode(content);
	}

}
