package fun.hijklmn.common.constants;

public enum RespEnum {

	/**
	 * 成功
	 */
	Success(1000, "成功", "Success"),

	/**
	 * 失败
	 */
	Faild(-999, "失败", "Faild"),

	/**
	 * 系统错误
	 */
	SysErr(-1000, "系统错误", "System Error"),

	/**
	 * 会话过期
	 */
	SesExpr(-1001, "会话过期", "Session Expire"),

	/**
	 * 权限受限
	 */
	PerDeni(-1002, "权限受限", "Permission Denied"),

	/**
	 * 无效参数
	 */
	InvParam(-1003, "无效参数", "Invalid Parameter"),

	/**
	 * 无效请求
	 */
	InvReq(-1004, "无效请求", "Invalid Request"),

	/**
	 * 用户名和密码不匹配
	 */
	AccAndPwdNotMatch(-1005, "用户名和密码不匹配", "Account and password not match"),

	/**
	 * 文件上传失败
	 */
	FileUploadFaild(-1006, "文件上传失败", "File Upload Faild"),

	/**
	 * 文件大小超过限制
	 */
	FileOverCapacity(-1007, "文件大小超过限制", "File Over Capacity");

	private Integer code;

	private String cnDesc;

	private String enDesc;

	RespEnum(Integer code, String cnDesc, String enDesc) {
		this.code = code;
		this.cnDesc = cnDesc;
		this.enDesc = enDesc;
	}

	public void code(Integer code) {
		this.code = code;
	}

	public Integer code() {
		return this.code;
	}

	public void cnDesc(String cnDesc) {
		this.cnDesc = cnDesc;
	}

	public String cnDesc() {
		return this.cnDesc;
	}

	public void enDesc(String enDesc) {
		this.enDesc = enDesc;
	}

	public String enDesc() {
		return this.enDesc;
	}

}
