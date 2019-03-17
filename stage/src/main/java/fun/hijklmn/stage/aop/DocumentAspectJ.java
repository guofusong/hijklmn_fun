package fun.hijklmn.stage.aop;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import fun.hijklmn.stage.common.WebGetter;

@Aspect
@Component
public class DocumentAspectJ extends BaseAspectJ{

	@Pointcut("execution(** fun.hijklmn.stage.controller.DocumentController.detail(..))")
	public void detail() {
	}
	
	@Around("detail()")
	public Object checkDetail(ProceedingJoinPoint jp) {
		
		final Object[] objs = jp.getArgs();
		
		HttpServletRequest request = (HttpServletRequest) objs[0];
		
		try {
			
			String docId = WebGetter.getString("docId", request);
			
			if (StringUtils.isBlank(docId)) {
				return "/";
			}
			
			return jp.proceed(); 
			
		} catch (Throwable e) {
			logger.error("系统错误[{}]" , e.getMessage());
		}
		
		return "/";
		
	}
	
}
