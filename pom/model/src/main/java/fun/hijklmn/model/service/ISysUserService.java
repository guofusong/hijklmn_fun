package fun.hijklmn.model.service;

import java.util.List;

import fun.hijklmn.model.pojo.SysUser;

public interface ISysUserService {

	List<SysUser> listSysUser(SysUser sysUser);

	Integer modifySysUserById(SysUser sysUser);

}
