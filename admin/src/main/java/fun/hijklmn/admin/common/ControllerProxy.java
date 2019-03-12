package fun.hijklmn.admin.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fun.hijklmn.common.constants.RespEnum;
import fun.hijklmn.model.dto.QueryReqDTO;
import fun.hijklmn.model.pojo.SysUser;

public class ControllerProxy {

	private static final Logger logger = LoggerFactory.getLogger(ControllerProxy.class);

	public static String assemble(ControllerHandler controllerHandler, HttpServletRequest request,
			HttpServletResponse response, QueryReqDTO queryReqDTO) {

		try {
			final SysUser sysUser = (SysUser) request.getSession().getAttribute(WebConstants.USER_KEY);
			if (queryReqDTO != null) {
				queryReqDTO.setIndex(WebGetter.getInteger("index", request));
				queryReqDTO.setSize(WebGetter.getInteger("size", request));
				queryReqDTO.setSort(WebGetter.getString("sort", request));
			}
			return controllerHandler.handler(request, response, sysUser, null, queryReqDTO);
		} catch (Exception e) {
			logger.error("处理错误[{}]", e.getMessage());
		}

		return null;

	}

	public static void assembleAjax(ControllerHandler controllerHandler, HttpServletRequest request,
			HttpServletResponse response, QueryReqDTO queryReqDTO) {

		final ResultVO resultVo = new ResultVO();
		try {
			final SysUser sysUser = (SysUser) request.getSession().getAttribute(WebConstants.USER_KEY);
			if (queryReqDTO != null) {
				queryReqDTO.setIndex(WebGetter.getInteger("index", request));
				queryReqDTO.setSize(WebGetter.getInteger("size", request));
				queryReqDTO.setSort(WebGetter.getString("sort", request));
			}
			controllerHandler.handler(request, response, sysUser, resultVo, queryReqDTO);
		} catch (Exception e) {
			logger.error("处理错误[{}]", e.getMessage());
			resultVo.setCode(RespEnum.SysErr.code());
			resultVo.setReason(RespEnum.SysErr.cnDesc());
		}
		ResponseUtils.outData(response, resultVo);
	}

}
