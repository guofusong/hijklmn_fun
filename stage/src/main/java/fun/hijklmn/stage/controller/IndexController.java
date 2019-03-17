package fun.hijklmn.stage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fun.hijklmn.model.dto.QueryReqDTO;
import fun.hijklmn.model.pojo.SysUser;
import fun.hijklmn.stage.common.ControllerHandler;
import fun.hijklmn.stage.common.ControllerProxy;
import fun.hijklmn.stage.common.ResultVO;

@Controller
public class IndexController extends BaseController{

	@RequestMapping(value = "/")
	public String index(HttpServletRequest request, HttpServletResponse response) {

		return ControllerProxy.assemble(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {
				return "home/home";
			}

		}, request, response, null);

	}

	@RequestMapping(value = "/me")
	public String me(HttpServletRequest request, HttpServletResponse response) {

		return ControllerProxy.assemble(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {
				return "me/me";
			}

		}, request, response, null);

	}

}
