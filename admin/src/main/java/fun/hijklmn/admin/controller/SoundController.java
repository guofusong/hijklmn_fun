package fun.hijklmn.admin.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fun.hijklmn.admin.common.ControllerHandler;
import fun.hijklmn.admin.common.ControllerProxy;
import fun.hijklmn.admin.common.ResultVO;
import fun.hijklmn.admin.common.WebGetter;
import fun.hijklmn.common.constants.RespEnum;
import fun.hijklmn.common.utils.IDGenerateUtils;
import fun.hijklmn.common.utils.JSONUtils;
import fun.hijklmn.model.dto.QueryReqDTO;
import fun.hijklmn.model.pojo.Sound;
import fun.hijklmn.model.pojo.SysUser;

@Controller
public class SoundController extends BaseController{

	@RequestMapping(value = "/sound/detail")
	public String detail(HttpServletRequest request, HttpServletResponse response) {

		return ControllerProxy.assemble(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {

				String souId = WebGetter.getString("souId", request);

				Sound sound = soundService.getSoundById(souId);

				request.setAttribute("sound", JSONUtils.toJsonStr(sound));

				request.setAttribute("imageUrl", constantsConf.getImageRespUrl());

				request.setAttribute("soundUrl", constantsConf.getSoundRespUrl());

				request.setAttribute("ckUploadReqUrl", constantsConf.getCkUploadReqUrl());

				return "sound/detail";
			}

		}, request, response, null);

	}

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
	public void queryPageData(HttpServletRequest request, HttpServletResponse response) {

		ControllerProxy.assembleAjax(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {

				Sound sound = new Sound();

				sound.setIsValid((byte) 1);

				List<Sound> sounds = soundService.listSound(sound);

				resultVo.setResult(sounds);

				return null;

			}

		}, request, response, null);

	}

	@RequestMapping(value = "/sound/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		ControllerProxy.assembleAjax(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {

				String souId = WebGetter.getString("souId", request);

				Sound sound = soundService.getSoundById(souId);

				if (sound != null) {
					sound.setIsValid((byte) 0);
					Integer count = soundService.updateSoundById(sound);
					if (count == null || count < 1) {
						resultVo.setCustomReason(RespEnum.Faild.code(), RespEnum.Faild.cnDesc());
					}
				}

				return null;
			}
		}, request, response, null);
	}

	@RequestMapping(value = "/sound/save")
	public void save(HttpServletRequest request, HttpServletResponse response) {
		ControllerProxy.assembleAjax(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {

				String params = WebGetter.getString("params", request);

				Sound sound = JSONUtils.toBean(params, Sound.class);

				int count = 0;

				if (StringUtils.isBlank(sound.getSouId())) {
					sound.setIsValid((byte) 1);
					sound.setSouId(IDGenerateUtils.generateSoundId());
					sound.setOnlineTime(new Date());
					count = soundService.addSound(sound);
				} else {
					count = soundService.updateSoundById(sound);
				}

				if (count == 0) {
					resultVo.setCustomReason(RespEnum.Faild.code(), RespEnum.Faild.cnDesc());
				}

				return null;

			}
		}, request, response, null);
	}

}
