package fun.hijklmn.admin.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fun.hijklmn.admin.common.ControllerHandler;
import fun.hijklmn.admin.common.ControllerProxy;
import fun.hijklmn.admin.common.ResultVO;
import fun.hijklmn.admin.common.WebGetter;
import fun.hijklmn.admin.conf.ConstantsConf;
import fun.hijklmn.common.constants.RespEnum;
import fun.hijklmn.common.utils.IDGenerateUtils;
import fun.hijklmn.common.utils.JSONUtils;
import fun.hijklmn.model.dto.QueryReqDTO;
import fun.hijklmn.model.pojo.Image;
import fun.hijklmn.model.pojo.SysUser;
import fun.hijklmn.model.service.IImageService;

@Controller
public class ImageController {

	@Autowired
	private IImageService imageService;

	@Autowired
	private ConstantsConf constantsConf;

	@RequestMapping(value = "/image/detail")
	public String detail(HttpServletRequest request, HttpServletResponse response) {

		return ControllerProxy.assemble(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {

				String imgId = WebGetter.getString("imgId", request);

				Image image = imageService.getImageById(imgId);

				request.setAttribute("image", JSONUtils.toJsonStr(image));

				request.setAttribute("imageUrl", constantsConf.getImageRespUrl());

				request.setAttribute("ckUploadReqUrl", constantsConf.getCkUploadReqUrl());

				return "image/detail";

			}
		}, request, response, null);

	}

	@RequestMapping(value = "/image/view")
	public String view(HttpServletRequest request, HttpServletResponse response) {

		return ControllerProxy.assemble(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {

				request.setAttribute("imageUrl", constantsConf.getImageRespUrl());

				return "image/view";

			}

		}, request, response, null);

	}

	@RequestMapping(value = "/image/queryData")
	public void queryPageData(HttpServletRequest request, HttpServletResponse response) {

		ControllerProxy.assembleAjax(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {

				Image image = new Image();

				image.setIsValid((byte) 1);

				List<Image> images = imageService.listImage(image);

				resultVo.setResult(images);

				return null;

			}

		}, request, response, null);

	}

	@RequestMapping(value = "/image/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		ControllerProxy.assembleAjax(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {

				String imgId = WebGetter.getString("imgId", request);

				Image image = imageService.getImageById(imgId);

				if (image != null) {
					image.setIsValid((byte) 0);
					Integer count = imageService.updateImageById(image);
					if (count == null || count < 1) {
						resultVo.setCustomReason(RespEnum.Faild.code(), RespEnum.Faild.cnDesc());
					}
				}

				return null;
			}
		}, request, response, null);
	}

	@RequestMapping(value = "/image/save")
	public void save(HttpServletRequest request, HttpServletResponse response) {
		ControllerProxy.assembleAjax(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {

				String params = WebGetter.getString("params", request);

				Image image = JSONUtils.toBean(params, Image.class);

				int count = 0;

				if (StringUtils.isBlank(image.getImgId())) {
					image.setIsValid((byte) 1);
					image.setImgId(IDGenerateUtils.generateDocumentId());
					image.setOnlineTime(new Date());
					count = imageService.addImage(image);
				} else {
					count = imageService.updateImageById(image);
				}

				if (count == 0) {
					resultVo.setCustomReason(RespEnum.Faild.code(), RespEnum.Faild.cnDesc());
				}

				return null;

			}
		}, request, response, null);
	}

}
