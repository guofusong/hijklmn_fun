package fun.hijklmn.stage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fun.hijklmn.common.utils.JSONUtils;
import fun.hijklmn.model.dto.ImageQueryDTO;
import fun.hijklmn.model.dto.QueryReqDTO;
import fun.hijklmn.model.dto.QueryRespDTO;
import fun.hijklmn.model.pojo.Image;
import fun.hijklmn.model.pojo.SysUser;
import fun.hijklmn.stage.common.ControllerHandler;
import fun.hijklmn.stage.common.ControllerProxy;
import fun.hijklmn.stage.common.ResultVO;
import fun.hijklmn.stage.common.WebGetter;

@Controller
public class ImageController extends BaseController{

	@RequestMapping(value = "/image/view")
	public String view(HttpServletRequest request , HttpServletResponse response) {
		
		return ControllerProxy.assemble(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {
				return "image/view";
			}
			
		}, request, response, null);
		
	}
	
	@RequestMapping(value = "/image/queryData")
	public void queryData(HttpServletRequest request , HttpServletResponse response) {
		
		final ImageQueryDTO imageQueryDTO = new ImageQueryDTO();
		
		ControllerProxy.assembleAjax(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {
				
				imageQueryDTO.setIsValid((byte)1);
				
				QueryRespDTO<Image> queryRespDTO = imageService.listImagePage(imageQueryDTO);
				
				resultVo.setGridData(queryRespDTO);
				
				Map<String, String> map = new HashMap<String, String>();
				
				map.put("imageUrl", constantsConf.getImageRespUrl());
				
				resultVo.setResult(map);
				
				return null;
			}
			
		}, request, response, imageQueryDTO);
		
	}
	
	@RequestMapping(value = "/image/detail")
	public String detail(HttpServletRequest request , HttpServletResponse response) {
		
		return ControllerProxy.assemble(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {
				
				String imgId = WebGetter.getString("imgId", request);
				
				Image image = imageService.getImageById(imgId);
				
				request.setAttribute("image", JSONUtils.toJsonStr(image));
				
				request.setAttribute("imageUrl", constantsConf.getImageRespUrl());
				
				return "image/detail";
			}
		
		}, request, response, null);
		
	}
	
}
