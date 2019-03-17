package fun.hijklmn.stage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fun.hijklmn.common.utils.JSONUtils;
import fun.hijklmn.model.dto.QueryReqDTO;
import fun.hijklmn.model.dto.QueryRespDTO;
import fun.hijklmn.model.dto.SoundQueryDTO;
import fun.hijklmn.model.pojo.Sound;
import fun.hijklmn.model.pojo.SysUser;
import fun.hijklmn.stage.common.ControllerHandler;
import fun.hijklmn.stage.common.ControllerProxy;
import fun.hijklmn.stage.common.ResultVO;
import fun.hijklmn.stage.common.WebGetter;

@Controller
public class SoundController extends BaseController{

	@RequestMapping(value = "/sound/view")
	public String view(HttpServletRequest request, HttpServletResponse response) {
		return ControllerProxy.assemble(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {
				return "sound/view";
			}

		}, request, response, null);
	}

	@RequestMapping(value = "/sound/queryData")
	public void queryData(HttpServletRequest request, HttpServletResponse response) {

		final SoundQueryDTO soundQueryDTO = new SoundQueryDTO();

		ControllerProxy.assembleAjax(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {

				soundQueryDTO.setIsValid((byte) 1);

				QueryRespDTO<Sound> queryRespDTO = soundService.listSoundPage(soundQueryDTO);

				resultVo.setGridData(queryRespDTO);

				Map<String, String> map = new HashMap<String, String>();

				map.put("imageUrl", constantsConf.getImageRespUrl());

				map.put("soundUrl", constantsConf.getSoundRespUrl());

				resultVo.setResult(map);

				return null;

			}

		}, request, response, soundQueryDTO);
	}
	
	@RequestMapping(value = "/sound/detail")
	public String detail(HttpServletRequest request , HttpServletResponse response) {
		
		return ControllerProxy.assemble(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {

				String souId = WebGetter.getString("souId", request);
				
				Sound sound = soundService.getSoundById(souId);
				
				request.setAttribute("sound", JSONUtils.toJsonStr(sound));
				
				request.setAttribute("imageUrl", constantsConf.getImageRespUrl());
				
				request.setAttribute("soundUrl", constantsConf.getSoundRespUrl());
				
				return "sound/detail";
			}
			
		}, request, response, null);
		
	}

}
