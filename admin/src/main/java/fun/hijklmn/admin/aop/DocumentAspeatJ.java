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
import fun.hijklmn.model.pojo.Document;

@Aspect
@Component
public class DocumentAspeatJ {

	private static final Logger logger = LoggerFactory.getLogger(DocumentAspeatJ.class);

	@Pointcut("execution(** fun.hijklmn.admin.controller.DocumentController.save(..))")
	public void save() {
	}
	
	@Pointcut("execution(** fun.hijklmn.admin.controller.DocumentController.check(..))")
	public void check() {
	}
	
	@Pointcut("execution(**  fun.hijklmn.admin.controller.DocumentController.delete(..))")
	public void delete() {
		
	}
	
	@Around("delete()")
	public Object checkDelete(ProceedingJoinPoint jp) {
		
		final ResultVO resultVo = new ResultVO();
		final Object[] objs = jp.getArgs();
		HttpServletRequest request = (HttpServletRequest) objs[0];
		HttpServletResponse response = (HttpServletResponse) objs[1];
		
		try {
			
			String docId = WebGetter.getString("docId", request);
			
			logger.info("------>删除文档[{}]" , docId);
			
			if (StringUtils.isBlank(docId)) {
				resultVo.setCustomReason(RespEnum.InvParam.code(), RespEnum.InvParam.cnDesc());
				ResponseUtils.outData(response, resultVo);
				return null;
			}
			
			return jp.proceed();
			
			
		} catch (Throwable e) {
			logger.error("系统错误[{}]" , e.getMessage());
			resultVo.setCustomReason(RespEnum.SysErr.code(), RespEnum.SysErr.cnDesc());
			ResponseUtils.outData(response, resultVo);
		}
		
		return null;

	}
	
	@Around("check()")
	public Object checkCheck(ProceedingJoinPoint jp) {
		
		final ResultVO resultVo = new ResultVO();
		final Object[] objs = jp.getArgs();
		HttpServletRequest request = (HttpServletRequest) objs[0];
		HttpServletResponse response = (HttpServletResponse) objs[1];
		
		try {
			
			String title = WebGetter.getString("title", request);
			String subTitle = WebGetter.getString("subTitle", request);
			
			logger.info("------>检查文档是否重复[title:"+title+"; subTitle:"+subTitle+";]");
			
			if (StringUtils.isBlank(title) || StringUtils.isBlank(subTitle)) {
				resultVo.setCustomReason(RespEnum.InvParam.code(), RespEnum.InvParam.cnDesc());
				ResponseUtils.outData(response, resultVo);
				return null;
			}
			
			return jp.proceed();
			
		} catch (Throwable e) {
			logger.error("系统错误[{}]" , e.getMessage());
			resultVo.setCustomReason(RespEnum.SysErr.code(), RespEnum.SysErr.cnDesc());
			ResponseUtils.outData(response, resultVo);
		}
		
		return null;
	}
	

	@Around("save()")
	public Object checkSave(ProceedingJoinPoint jp) {

		final ResultVO resultVo = new ResultVO();
		final Object[] objs = jp.getArgs();
		HttpServletRequest request = (HttpServletRequest) objs[0];
		HttpServletResponse response = (HttpServletResponse) objs[1];

		try {
			
			String params = WebGetter.getString("params", request);
			
			logger.info("------>保存文档[{}]" , params);
			
			if (StringUtils.isBlank(params)) {
				resultVo.setCustomReason(RespEnum.InvParam.code(), RespEnum.InvParam.cnDesc());
				ResponseUtils.outData(response, resultVo);
				return null;
			}

			Document document = JSONUtils.toBean(params, Document.class);
			if (document == null || StringUtils.isBlank(document.getTitle()) || StringUtils.isBlank(document.getSubTitle()) || StringUtils.isBlank(document.getAuthor()) || StringUtils.isBlank(document.getSource()) || StringUtils.isBlank(document.getDocumentType())) {
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
