package fun.hijklmn.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import fun.hijklmn.admin.common.ResponseUtils;
import fun.hijklmn.admin.common.ResultVO;
import fun.hijklmn.common.constants.RespEnum;

public class ExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		final ResultVO resultVo = new ResultVO();

		if (ex instanceof MaxUploadSizeExceededException) {
			resultVo.setCustomReason(RespEnum.FileOverCapacity.code(), RespEnum.FileOverCapacity.cnDesc());
		}

		if (ResponseUtils.isAjax(request)) {
			ResponseUtils.outData(response, resultVo);
		}

		return null;
	}

}
