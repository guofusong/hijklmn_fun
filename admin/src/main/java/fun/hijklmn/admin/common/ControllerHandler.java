package fun.hijklmn.admin.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fun.hijklmn.model.dto.QueryReqDTO;
import fun.hijklmn.model.pojo.SysUser;

public interface ControllerHandler {

	public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser, ResultVO resultVo,
			QueryReqDTO queryReqDTO) throws Exception;

}
