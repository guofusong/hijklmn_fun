package fun.hijklmn.common.vo;

import java.io.Serializable;

public class MediaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6941014954571184388L;

	private Integer code;

	private String suffix;

	private String name;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
