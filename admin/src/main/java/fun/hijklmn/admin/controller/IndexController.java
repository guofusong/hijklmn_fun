package fun.hijklmn.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fun.hijklmn.admin.common.ControllerHandler;
import fun.hijklmn.admin.common.ControllerProxy;
import fun.hijklmn.admin.common.ResultVO;
import fun.hijklmn.model.dto.QueryReqDTO;
import fun.hijklmn.model.pojo.SysUser;

@Controller
public class IndexController {

	@RequestMapping(value = "/")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return ControllerProxy.assemble(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) {
				if (sysUser == null) {
					return "login";
				}
				return "redirect:/home";

			}
		}, request, response, null);
	}

	@RequestMapping(value = "/notAllow")
	public String notAllow(HttpServletRequest request, HttpServletResponse response) {
		return ControllerProxy.assemble(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) {

				return "notAllow";

			}

		}, request, response, null);
	}

	@RequestMapping(value = "/home")
	public String home(HttpServletRequest request, HttpServletResponse response) {
		return ControllerProxy.assemble(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) {
				return "home/home";
			}

		}, request, response, null);
	}

}
