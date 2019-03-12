package fun.hijklmn.admin.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import fun.hijklmn.admin.common.ResponseUtils;
import fun.hijklmn.admin.common.ResultVO;
import fun.hijklmn.admin.common.WebGetter;
import fun.hijklmn.common.constants.RespEnum;
import fun.hijklmn.common.utils.JSONUtils;
import fun.hijklmn.model.pojo.Sound;

@Aspect
@Component
public class SoundAspeatJ {

	private final Logger logger = LoggerFactory.getLogger(SoundAspeatJ.class);

	@Pointcut("execution(** fun.hijklmn.admin.controller.SoundController.save(..))")
	public void save() {
	}

	@Around("save()")
	public Object checkSave(ProceedingJoinPoint jp) {

		final ResultVO resultVo = new ResultVO();
		final Object[] objs = jp.getArgs();
		HttpServletRequest request = (HttpServletRequest) objs[0];
		HttpServletResponse response = (HttpServletResponse) objs[1];

		try {
			String params = WebGetter.getString("params", request);
			if (StringUtils.isBlank(params)) {
				resultVo.setCustomReason(RespEnum.InvParam.code(), RespEnum.InvParam.cnDesc());
				ResponseUtils.outData(response, resultVo);
				return null;
			}

			Sound sound = JSONUtils.toBean(params, Sound.class);
			if (sound == null || StringUtils.isBlank(sound.getSoundName()) || StringUtils.isBlank(sound.getAuthor())
					|| StringUtils.isBlank(sound.getSoundType()) || StringUtils.isBlank(sound.getSource())
					|| StringUtils.isBlank(sound.getSoundUrl())) {
				resultVo.setCustomReason(RespEnum.InvParam.code(), RespEnum.InvParam.cnDesc());
				ResponseUtils.outData(response, resultVo);
				return null;
			}

			return jp.proceed();

		} catch (Throwable e) {
			logger.error("处理错误[{}]", e.getMessage());
			resultVo.setCustomReason(RespEnum.SysErr.code(), RespEnum.SysErr.cnDesc());
			ResponseUtils.outData(response, resultVo);
		}
		return null;

	}

}
