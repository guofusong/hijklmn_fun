package fun.hijklmn.model.mapper;

import java.util.List;

import fun.hijklmn.model.pojo.SysUser;

public interface SysUserMapper extends CommonMapper<SysUser> {

	List<SysUser> listSysUser(SysUser sysUser);

}