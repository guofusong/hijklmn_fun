package fun.hijklmn.common.utils;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JSONUtils {

	public static <T> T toBean(String params, Class<T> c) {
		return JSONObject.parseObject(params, c);
	}

	public static <T> List<T> toList(String params, Class<T> c) {
		return JSONArray.parseArray(params, c);
	}

	public static String toJsonStr(Object object) {
		return JSONObject.toJSONString(object);
	}

}
