package fun.hijklmn.model.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fun.hijklmn.model.mapper.SysUserMapper;
import fun.hijklmn.model.pojo.SysUser;
import fun.hijklmn.model.service.ISysUserService;

@Service("sysUserService")
public class SysUserServiceImpl implements ISysUserService {

	@Resource
	private SysUserMapper sysUserMapper;

	@Override
	public List<SysUser> listSysUser(SysUser sysUser) {
		return sysUserMapper.listSysUser(sysUser);
	}

	@Override
	public Integer modifySysUserById(SysUser sysUser) {
		return sysUserMapper.updateByPrimaryKeySelective(sysUser);
	}

}
