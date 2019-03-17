package fun.hijklmn.admin.aop;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import fun.hijklmn.admin.common.ResultVO;
import fun.hijklmn.common.constants.RespEnum;

@Aspect
@Component
public class UploadAspeatJ extends BaseAspectJ{

	@Pointcut("execution(** fun.hijklmn.admin.controller.UploadController.ckUploadImage(..))")
	public void ckUploadImage() {
	}

	@Pointcut("execution(** fun.hijklmn.admin.controller.UploadController.uploadImage(..))")
	public void uploadImage() {
	}

	@Pointcut("execution(** fun.hijklmn.admin.controller.UploadController.uploadSound(..))")
	public void uploadSound() {
	}

	@Pointcut("execution(** fun.hijklmn.admin.controller.UploadController.uploadVideo(..))")
	public void uploadVideo() {
	}

	@Around("uploadVideo()")
	public void checkUploadVideo(ProceedingJoinPoint jp) {
		final ResultVO resultVo = new ResultVO();
		final Object[] objs = jp.getArgs();
		MultipartFile file = (MultipartFile) objs[2];
		HttpServletResponse response = (HttpServletResponse) objs[1];
		try {

			if (file == null || file.isEmpty()) {
				resultVo.setCustomReason(RespEnum.InvParam.code(), RespEnum.InvParam.cnDesc());
				outData(response, resultVo);
				return;
			}

			String filename = file.getOriginalFilename();
			if (filename.matches(".*^[^(mp4)]")) {
				resultVo.setCustomReason(RespEnum.InvParam.code(), RespEnum.InvParam.cnDesc());
				outData(response, resultVo);
				return;
			}

			jp.proceed();

		} catch (Throwable e) {
			logger.error("处理错误[{}]", e.getMessage());
			resultVo.setCustomReason(RespEnum.SysErr.code(), RespEnum.SysErr.cnDesc());
			outData(response, resultVo);
			return;
		}
	}

	@Around("uploadSound()")
	public void checkUploadSound(ProceedingJoinPoint jp) {
		final ResultVO resultVo = new ResultVO();
		final Object[] objs = jp.getArgs();
		MultipartFile file = (MultipartFile) objs[2];
		HttpServletResponse response = (HttpServletResponse) objs[1];
		try {

			if (file == null || file.isEmpty()) {
				resultVo.setCustomReason(RespEnum.InvParam.code(), RespEnum.InvParam.cnDesc());
				outData(response, resultVo);
				return;
			}

			String filename = file.getOriginalFilename();
			if (filename.matches(".*^[^(mp3)]")) {
				resultVo.setCustomReason(RespEnum.InvParam.code(), RespEnum.InvParam.cnDesc());
				outData(response, resultVo);
				return;
			}

			jp.proceed();

		} catch (Throwable e) {
			logger.error("处理错误[{}]", e.getMessage());
			resultVo.setCustomReason(RespEnum.SysErr.code(), RespEnum.SysErr.cnDesc());
			outData(response, resultVo);
			return;
		}
	}

	@Around("uploadImage()")
	public void checkUploadImage(ProceedingJoinPoint jp) {
		final ResultVO resultVo = new ResultVO();
		final Object[] objs = jp.getArgs();
		MultipartFile file = (MultipartFile) objs[2];
		HttpServletResponse response = (HttpServletResponse) objs[1];
		try {

			if (file == null || file.isEmpty()) {
				resultVo.setCustomReason(RespEnum.InvParam.code(), RespEnum.InvParam.cnDesc());
				outData(response, resultVo);
				return;
			}

			String filename = file.getOriginalFilename();
			if (filename.matches(".*^[^(png|jpg|jpeg|bmp)]")) {
				resultVo.setCustomReason(RespEnum.InvParam.code(), RespEnum.InvParam.cnDesc());
				outData(response, resultVo);
				return;
			}

			jp.proceed();

		} catch (Throwable e) {
			logger.error("处理错误[{}]", e.getMessage());
			resultVo.setCustomReason(RespEnum.SysErr.code(), RespEnum.SysErr.cnDesc());
			outData(response, resultVo);
			return;
		}
	}

	@Around("ckUploadImage()")
	public Object checkCkUploadImage(ProceedingJoinPoint jp) {

		final Map<String, String> map = new HashMap<>();
		final Object[] objs = jp.getArgs();
		MultipartFile file = (MultipartFile) objs[2];
		try {

			if (file == null || file.isEmpty()) {
				map.put("uploaded", "false");
				return map;
			}

			String name = file.getOriginalFilename();

			if (name.matches(".*^[^(png|jpg|jpeg|bmp)]")) {
				map.put("uploaded", "false");
				return map;
			}

			return jp.proceed();

		} catch (Throwable e) {
			logger.error("处理错误[{}]", e.getMessage());
			map.put("uploaded", "false");
		}
		return map;

	}

}
