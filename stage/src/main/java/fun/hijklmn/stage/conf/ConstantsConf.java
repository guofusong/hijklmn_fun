package fun.hijklmn.stage.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantsConf {

	private String imageRespUrl;

	private String soundRespUrl;

	private String videoRespUrl;

	public String getImageRespUrl() {
		return imageRespUrl;
	}

	public String getSoundRespUrl() {
		return soundRespUrl;
	}

	public String getVideoRespUrl() {
		return videoRespUrl;
	}

	@Value("${imageRespUrl}")
	public void setImageRespUrl(String imageRespUrl) {
		this.imageRespUrl = imageRespUrl;
	}

	@Value("${soundRespUrl}")
	public void setSoundRespUrl(String soundRespUrl) {
		this.soundRespUrl = soundRespUrl;
	}

	@Value("${videoRespUrl}")
	public void setVideoRespUrl(String videoRespUrl) {
		this.videoRespUrl = videoRespUrl;
	}

}
