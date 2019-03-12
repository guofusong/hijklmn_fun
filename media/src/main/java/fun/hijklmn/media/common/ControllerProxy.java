package fun.hijklmn.media.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fun.hijklmn.common.constants.RespEnum;
import fun.hijklmn.common.dto.MediaDTO;
import fun.hijklmn.common.vo.MediaVO;

public class ControllerProxy {

	private static final Logger logger = LoggerFactory.getLogger(ControllerProxy.class);

	public static MediaVO assemble(ControllerHandler controllerHandler, MediaDTO mediaDTO) {
		final MediaVO mediaVO = new MediaVO();
		mediaVO.setCode(RespEnum.Success.code());
		try {
			controllerHandler.handler(mediaDTO, mediaVO);
		} catch (Exception e) {
			logger.error("图片服务器处理异常[{}]", e.getMessage());
			mediaVO.setCode(RespEnum.SysErr.code());
		}
		return mediaVO;
	}

}
