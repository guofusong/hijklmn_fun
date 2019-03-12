package fun.hijklmn.admin.common;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import fun.hijklmn.common.constants.RespEnum;

public class ResultVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -567863728279666006L;

	private Integer code = RespEnum.Success.code();

	private String reason = RespEnum.Success.cnDesc();

	private Object result;

	private Object gridData;

	public void setCustomReason(Integer code, String reason) {
		if (StringUtils.isNotBlank(reason)) {
			this.code = RespEnum.SysErr.code();
			this.reason = reason;
		}
		if (code != null) {
			this.code = code;
		}
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Object getGridData() {
		return gridData;
	}

	public void setGridData(Object gridData) {
		this.gridData = gridData;
	}

}
