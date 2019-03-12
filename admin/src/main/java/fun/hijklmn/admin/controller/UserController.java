package fun.hijklmn.admin.controller;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fun.hijklmn.admin.common.ControllerHandler;
import fun.hijklmn.admin.common.ControllerProxy;
import fun.hijklmn.admin.common.ResultVO;
import fun.hijklmn.admin.common.WebConstants;
import fun.hijklmn.admin.common.WebGetter;
import fun.hijklmn.common.constants.RespEnum;
import fun.hijklmn.common.utils.EncryptionUtils;
import fun.hijklmn.model.dto.QueryReqDTO;
import fun.hijklmn.model.pojo.Menu;
import fun.hijklmn.model.pojo.SysUser;
import fun.hijklmn.model.service.IMenuService;
import fun.hijklmn.model.service.ISysUserService;

@Controller
public class UserController {

	@Autowired
	private ISysUserService sysUserService;

	@Autowired
	private IMenuService menuService;

	@RequestMapping(value = "/user/signin")
	public void signin(HttpServletRequest request, HttpServletResponse response) {
		ControllerProxy.assembleAjax(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) {

				String username = WebGetter.getString("username", request);
				String password = WebGetter.getString("password", request);

				SysUser user = new SysUser();
				user.setUserName(username);
				user.setPassword(EncryptionUtils.MD5(password, null));
				user.setIsValid(1);

				List<SysUser> sysUsers = sysUserService.listSysUser(user);

				if (sysUsers.size() < 1) {
					resultVo.setCustomReason(RespEnum.AccAndPwdNotMatch.code(), RespEnum.AccAndPwdNotMatch.cnDesc());
					return null;
				}

				sysUsers.get(0).setLastLoginTime(new Date());
				sysUserService.modifySysUserById(sysUsers.get(0));

				sysUsers.get(0).setPassword(null);
				request.getSession().setAttribute(WebConstants.USER_KEY, sysUsers.get(0));

				Menu menu = new Menu();
				menu.setIsValid(1);

				List<Menu> menus = menuService.listMenu(menu);
				request.getSession().setAttribute(WebConstants.MENU_KEY, menus);

				return null;

			}

		}, request, response, null);
	}

	@RequestMapping(value = "/user/signout")
	public String signout(HttpServletRequest request, HttpServletResponse response) {
		return ControllerProxy.assemble(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) {

				Enumeration<String> em = request.getSession().getAttributeNames();

				while (em.hasMoreElements()) {
					request.getSession().removeAttribute(em.nextElement().toString());
				}

				return "redirect:/";

			}

		}, request, response, null);
	}

}
