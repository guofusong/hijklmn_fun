package fun.hijklmn.admin.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantsConf {

	private String fileReqUrl;

	private String imageRespUrl;

	private String soundRespUrl;

	private String videoRespUrl;

	private String ckUploadReqUrl;

	public String getFileReqUrl() {
		return fileReqUrl;
	}

	@Value("${fileReqUrl}")
	public void setFileReqUrl(String fileReqUrl) {
		this.fileReqUrl = fileReqUrl;
	}

	public String getImageRespUrl() {
		return imageRespUrl;
	}

	@Value("${imageRespUrl}")
	public void setImageRespUrl(String imageRespUrl) {
		this.imageRespUrl = imageRespUrl;
	}

	public String getSoundRespUrl() {
		return soundRespUrl;
	}

	@Value("${soundRespUrl}")
	public void setSoundRespUrl(String soundRespUrl) {
		this.soundRespUrl = soundRespUrl;
	}

	public String getVideoRespUrl() {
		return videoRespUrl;
	}

	@Value("${videoRespUrl}")
	public void setVideoRespUrl(String videoRespUrl) {
		this.videoRespUrl = videoRespUrl;
	}

	public String getCkUploadReqUrl() {
		return ckUploadReqUrl;
	}

	@Value("${ckUploadReqUrl}")
	public void setCkUploadReqUrl(String ckUploadReqUrl) {
		this.ckUploadReqUrl = ckUploadReqUrl;
	}

}
