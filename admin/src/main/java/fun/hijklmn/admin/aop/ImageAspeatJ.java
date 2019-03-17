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
import fun.hijklmn.common.utils.JSONUtils;
import fun.hijklmn.model.pojo.Image;

@Aspect
@Component
public class ImageAspeatJ extends BaseAspectJ{

	@Pointcut("execution(** fun.hijklmn.admin.controller.ImageController.save(..))")
	public void save() {
	}

	@Pointcut("execution(** fun.hijklmn.admin.controller.ImageController.delete(..))")
	public void delete() {
	}
	
	@Around("delete()")
	public Object checkDelete(ProceedingJoinPoint jp) {
		
		final ResultVO resultVo = new ResultVO();
		final Object[] objs = jp.getArgs();
		HttpServletRequest request = (HttpServletRequest) objs[0];
		HttpServletResponse response = (HttpServletResponse) objs[1];
		try {
			
			String imgId = WebGetter.getString("imgId", request);
			
			logger.info("------>删除图片[{}]" , imgId);
			
			if (StringUtils.isBlank(imgId)) {
				resultVo.setCustomReason(RespEnum.InvParam.code(), RespEnum.InvParam.cnDesc());
				outData(response, resultVo);
				return null;
			}
			
			return jp.proceed();
			
		}catch(Throwable e) {
			logger.error("处理错误[{}]" , e.getMessage());
			resultVo.setCustomReason(RespEnum.SysErr.code(), RespEnum.SysErr.cnDesc());
			outData(response, resultVo);
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
			
			logger.info("------>保存图片[{}]" , params);
			
			if (StringUtils.isBlank(params)) {
				resultVo.setCustomReason(RespEnum.InvParam.code(), RespEnum.InvParam.cnDesc());
				outData(response, resultVo);
				return null;
			}

			Image image = JSONUtils.toBean(params, Image.class);
			if (image == null || StringUtils.isBlank(image.getAuthor()) || StringUtils.isBlank(image.getImageTag()) || StringUtils.isBlank(image.getImageType()) || StringUtils.isBlank(image.getImageUrl()) || StringUtils.isBlank(image.getSource()) || StringUtils.isBlank(image.getSubTitle()) || StringUtils.isBlank(image.getTitle())) {
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
