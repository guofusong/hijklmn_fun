package fun.hijklmn.admin.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class WebGetter {

	public static String getString(String key, HttpServletRequest request, String defv) {
		String val = request.getParameter(key);
		if (StringUtils.isBlank(val)) {
			return defv;
		}
		return val;
	}

	public static String getString(String key, HttpServletRequest request) {
		String val = request.getParameter(key);
		return val;
	}

	public static Integer getInteger(String key, HttpServletRequest request, int defv) {
		String val = request.getParameter(key);
		if (StringUtils.isBlank(val)) {
			return defv;
		}
		return Integer.parseInt(val);
	}

	public static Integer getInteger(String key, HttpServletRequest request) {
		String val = request.getParameter(key);
		if (StringUtils.isBlank(val)) {
			return null;
		}
		return Integer.parseInt(val);
	}

	public static Integer getIntegerE(String key, HttpServletRequest request) throws Exception {
		try {
			return Integer.parseInt(request.getParameter(key));
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

}
