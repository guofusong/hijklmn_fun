package fun.hijklmn.admin.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import fun.hijklmn.admin.common.ResultVO;
import fun.hijklmn.admin.common.WebGetter;
import fun.hijklmn.common.constants.RespEnum;

@Aspect
@Component
public class UserAspeatJ extends BaseAspectJ{

	@Pointcut("execution(** fun.hijklmn.admin.controller.UserController.signin(..))")
	public void signin() {
	}

	@Around("signin()")
	public Object checkSignin(ProceedingJoinPoint jp) {

		final ResultVO resultVo = new ResultVO();
		final Object[] objs = jp.getArgs();
		HttpServletRequest request = (HttpServletRequest) objs[0];
		HttpServletResponse response = (HttpServletResponse) objs[1];

		try {
			String username = WebGetter.getString("username", request);
			String password = WebGetter.getString("password", request);
			if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
				resultVo.setCustomReason(RespEnum.InvParam.code(), RespEnum.InvParam.cnDesc());
				outData(response, resultVo);
				return null;
			}

			return jp.proceed();

		} catch (Throwable e) {
			logger.error("处理错误[{}]", e.getMessage());
			resultVo.setCustomReason(RespEnum.SysErr.code(), RespEnum.SysErr.cnDesc());
			outData(response, resultVo);
		}
		return null;

	}

}
