package fun.hijklmn.media.aop;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import fun.hijklmn.common.constants.MediaEnum;
import fun.hijklmn.common.constants.RespEnum;
import fun.hijklmn.common.dto.MediaDTO;
import fun.hijklmn.common.vo.MediaVO;

@Aspect
@Component
public class MediaAspectJ {

	private final Logger logger = LoggerFactory.getLogger(MediaAspectJ.class);

	@Pointcut("execution(** fun.hijklmn.media.controller.MediaController.upload(..))")
	public void upload() {
	}

	@Around("upload()")
	public Object checkUpload(ProceedingJoinPoint jp) {

		final MediaVO mediaVO = new MediaVO();

		try {
			final Object[] objs = jp.getArgs();
			MediaDTO mediaDTO = (MediaDTO) objs[0];
			if (StringUtils.isBlank(mediaDTO.getMediaType()) || StringUtils.isBlank(mediaDTO.getSuffix())
					|| StringUtils.isBlank(mediaDTO.getContent())) {
				mediaVO.setCode(RespEnum.InvParam.code());
				return mediaVO;
			}
			if (!(mediaDTO.getMediaType().equals(MediaEnum.Image.type())
					|| mediaDTO.getMediaType().equals(MediaEnum.Sound.type())
					|| mediaDTO.getMediaType().equals(MediaEnum.Video.type()))) {
				mediaVO.setCode(RespEnum.InvParam.code());
				return mediaVO;
			}

			return jp.proceed();

		} catch (Throwable e) {
			logger.error("处理错误[{}]", e.getMessage());
			mediaVO.setCode(RespEnum.SysErr.code());
			return mediaVO;
		}

	}

}
