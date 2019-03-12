package fun.hijklmn.common.utils;

import java.util.UUID;

public class RandomUtils {

	public static String generateUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}

}
