package fun.hijklmn.media.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import fun.hijklmn.common.constants.RespEnum;
import fun.hijklmn.common.vo.MediaVO;
import fun.hijklmn.media.common.ResponseUtils;

public class ExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		MediaVO mediaVo = new MediaVO();

		if (ex instanceof MaxUploadSizeExceededException) {
			mediaVo.setCode(RespEnum.FileOverCapacity.code());
		}

		ResponseUtils.outData(response, mediaVo);

		return null;
	}

}
