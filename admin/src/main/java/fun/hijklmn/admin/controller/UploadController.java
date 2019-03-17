package fun.hijklmn.admin.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fun.hijklmn.admin.common.ControllerHandler;
import fun.hijklmn.admin.common.ControllerProxy;
import fun.hijklmn.admin.common.ResultVO;
import fun.hijklmn.common.constants.MediaEnum;
import fun.hijklmn.common.constants.RespEnum;
import fun.hijklmn.common.dto.MediaDTO;
import fun.hijklmn.common.utils.EncodeUtils;
import fun.hijklmn.common.utils.StringUtils;
import fun.hijklmn.common.vo.MediaVO;
import fun.hijklmn.model.dto.QueryReqDTO;
import fun.hijklmn.model.pojo.SysUser;

@RestController
public class UploadController extends BaseController{

	@RequestMapping(value = "/image/ck/upload")
	public Map<String, String> ckUploadImage(HttpServletRequest request, HttpServletResponse response,
			@RequestPart("upload") MultipartFile file) {
		final Map<String, String> map = new HashMap<>();
		map.put("uploaded", "true");
		try {
			String filename = file.getOriginalFilename();

			MediaDTO mediaDTO = new MediaDTO();
			mediaDTO.setSuffix(StringUtils.getSuffix(filename, "\\."));
			mediaDTO.setMediaType(MediaEnum.Image.type());
			mediaDTO.setContent(EncodeUtils.encode(file.getBytes()));

			URI uri = new URI(constantsConf.getFileReqUrl());

			ResponseEntity<MediaVO> responseEntity = restTemplate.postForEntity(uri, mediaDTO, MediaVO.class);
			if (responseEntity.getStatusCode() != HttpStatus.OK) {
				map.put("uploaded", "false");
				return map;
			}

			MediaVO mediaVO = responseEntity.getBody();
			if (mediaVO.getCode() != RespEnum.Success.code().intValue()) {
				map.put("uploaded", "false");
				return map;
			}

			map.put("url", constantsConf.getImageRespUrl() + "/" + mediaVO.getName());
		} catch (Exception e) {
			logger.error("上传图片失败[{}]", e.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/image/upload")
	public void uploadImage(HttpServletRequest request, HttpServletResponse response,
			@RequestPart("upload") MultipartFile file) {

		ControllerProxy.assembleAjax(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {

				String filename = file.getOriginalFilename();

				MediaDTO mediaDTO = new MediaDTO();
				mediaDTO.setSuffix(StringUtils.getSuffix(filename, "\\."));
				mediaDTO.setMediaType(MediaEnum.Image.type());
				mediaDTO.setContent(EncodeUtils.encode(file.getBytes()));

				URI uri = new URI(constantsConf.getFileReqUrl());

				ResponseEntity<MediaVO> responseEntity = restTemplate.postForEntity(uri, mediaDTO, MediaVO.class);
				if (responseEntity.getStatusCode() != HttpStatus.OK) {
					resultVo.setCustomReason(RespEnum.FileUploadFaild.code(), RespEnum.FileUploadFaild.cnDesc());
					return null;
				}

				MediaVO mediaVO = responseEntity.getBody();
				if (mediaVO.getCode() != RespEnum.Success.code().intValue()) {
					resultVo.setCustomReason(RespEnum.FileUploadFaild.code(), RespEnum.FileUploadFaild.cnDesc());
					return null;
				}

				resultVo.setReason(RespEnum.Success.cnDesc());
				resultVo.setResult(mediaVO.getName());

				return null;

			}

		}, request, response, null);
	}

	@RequestMapping(value = "/sound/upload")
	public void uploadSound(HttpServletRequest request, HttpServletResponse response,
			@RequestPart("upload") MultipartFile file) {
		ControllerProxy.assembleAjax(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {

				String filename = file.getOriginalFilename();

				MediaDTO mediaDTO = new MediaDTO();
				mediaDTO.setSuffix(StringUtils.getSuffix(filename, "\\."));
				mediaDTO.setMediaType(MediaEnum.Sound.type());
				mediaDTO.setContent(EncodeUtils.encode(file.getBytes()));

				URI uri = new URI(constantsConf.getFileReqUrl());

				ResponseEntity<MediaVO> responseEntity = restTemplate.postForEntity(uri, mediaDTO, MediaVO.class);
				if (responseEntity.getStatusCode() != HttpStatus.OK) {
					resultVo.setCustomReason(RespEnum.FileUploadFaild.code(), RespEnum.FileUploadFaild.cnDesc());
					return null;
				}

				MediaVO mediaVO = responseEntity.getBody();
				if (mediaVO.getCode() != RespEnum.Success.code().intValue()) {
					resultVo.setCustomReason(RespEnum.FileUploadFaild.code(), RespEnum.FileUploadFaild.cnDesc());
					return null;
				}

				resultVo.setReason(RespEnum.Success.cnDesc());
				resultVo.setResult(mediaVO.getName());

				return null;
			}

		}, request, response, null);
	}

	@RequestMapping(value = "/video/upload")
	public void uploadVideo(HttpServletRequest request, HttpServletResponse response,
			@RequestPart("upload") MultipartFile file) {
		ControllerProxy.assembleAjax(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {

				String filename = file.getOriginalFilename();

				MediaDTO mediaDTO = new MediaDTO();
				mediaDTO.setSuffix(StringUtils.getSuffix(filename, "\\."));
				mediaDTO.setMediaType(MediaEnum.Video.type());
				mediaDTO.setContent(EncodeUtils.encode(file.getBytes()));

				URI uri = new URI(constantsConf.getFileReqUrl());

				ResponseEntity<MediaVO> responseEntity = restTemplate.postForEntity(uri, mediaDTO, MediaVO.class);
				if (responseEntity.getStatusCode() != HttpStatus.OK) {
					resultVo.setCustomReason(RespEnum.FileUploadFaild.code(), RespEnum.FileUploadFaild.cnDesc());
					return null;
				}

				MediaVO mediaVO = responseEntity.getBody();
				if (mediaVO.getCode() != RespEnum.Success.code().intValue()) {
					resultVo.setCustomReason(RespEnum.FileUploadFaild.code(), RespEnum.FileUploadFaild.cnDesc());
					return null;
				}

				resultVo.setReason(RespEnum.Success.cnDesc());
				resultVo.setResult(mediaVO.getName());

				return null;
			}

		}, request, response, null);
	}

}
