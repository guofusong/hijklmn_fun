package fun.hijklmn.common.constants;

public enum MediaEnum {

	/**
	 * 图片
	 */
	Image(1001, "Image"),

	/**
	 * 声音
	 */
	Sound(1002, "Sound"),

	/**
	 * 视频
	 */
	Video(1003, "Video");

	private Integer code;

	private String type;

	MediaEnum(Integer code, String type) {
		this.code = code;
		this.type = type;
	}

	public Integer code() {
		return this.code;
	}

	public String type() {
		return this.type;
	}

}
