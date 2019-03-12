package fun.hijklmn.media.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fun.hijklmn.common.utils.JSONUtils;
import fun.hijklmn.common.vo.MediaVO;

public class ResponseUtils {

	private static final Logger logger = LoggerFactory.getLogger(ResponseUtils.class);

	public static void outData(HttpServletResponse response, MediaVO mediaVO) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		String json = JSONUtils.toJsonStr(mediaVO);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append(json);
		} catch (IOException e) {
			logger.error("返回json错误！[{}]", json);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
