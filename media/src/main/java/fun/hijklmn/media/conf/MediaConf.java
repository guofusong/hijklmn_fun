package fun.hijklmn.media.conf;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import fun.hijklmn.common.utils.DirUtils;

@Component
public class MediaConf {

	private final Logger logger = LoggerFactory.getLogger(MediaConf.class);

	private String imageLocal;

	private String soundLocal;

	private String videoLocal;

	private String imageUrl;

	private String soundUrl;

	private String videoUrl;

	public void init() {
		boolean createDir;
		File local = new File(imageLocal);
		if (!local.exists() || local.isFile()) {
			createDir = DirUtils.createAbsoluteDir(imageLocal);
			if (!createDir) {
				logger.info("路径创建失败！[imageLocalPath:" + imageLocal + "]");
				System.exit(0);
			}
		}
		local = new File(soundLocal);
		if (!local.exists() || local.isFile()) {
			createDir = DirUtils.createAbsoluteDir(soundLocal);
			if (!createDir) {
				logger.info("路径创建失败！[soundLocalPath:" + soundLocal + "]");
				System.exit(0);
			}
		}
		local = new File(videoLocal);
		if (!local.exists() || local.isFile()) {
			createDir = DirUtils.createAbsoluteDir(videoLocal);
			if (!createDir) {
				logger.info("路径创建失败！[videoLocalPath:" + videoLocal + "]");
				System.exit(0);
			}
		}
		if (!imageLocal.endsWith("\\")) {
			imageLocal += "\\";
		}
		if (!soundLocal.endsWith("\\")) {
			soundLocal += "\\";
		}
		if (!videoLocal.endsWith("\\")) {
			videoLocal += "\\";
		}
		logger.info("媒体存储路径检查成功！");
	}

	public String getImageLocal() {
		return imageLocal;
	}

	@Value("${imageLocal}")
	public void setImageLocal(String imageLocal) {
		this.imageLocal = imageLocal;
	}

	public String getSoundLocal() {
		return soundLocal;
	}

	@Value("${soundLocal}")
	public void setSoundLocal(String soundLocal) {
		this.soundLocal = soundLocal;
	}

	public String getVideoLocal() {
		return videoLocal;
	}

	@Value("${videoLocal}")
	public void setVideoLocal(String videoLocal) {
		this.videoLocal = videoLocal;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	@Value("${imageUrl}")
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSoundUrl() {
		return soundUrl;
	}

	@Value("${soundUrl}")
	public void setSoundUrl(String soundUrl) {
		this.soundUrl = soundUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	@Value("${videoUrl}")
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

}
