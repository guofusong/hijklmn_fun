package fun.hijklmn.media.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MediaMvcConf implements WebMvcConfigurer {

	private final String WILDCARD = "**";

	private final String PREFIX = "file:";

	@Autowired
	private MediaConf mediaConf;

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(mediaConf.getImageUrl() + WILDCARD)
				.addResourceLocations(PREFIX + mediaConf.getImageLocal());
		registry.addResourceHandler(mediaConf.getSoundUrl() + WILDCARD)
				.addResourceLocations(PREFIX + mediaConf.getSoundLocal());
		registry.addResourceHandler(mediaConf.getVideoUrl() + WILDCARD)
				.addResourceLocations(PREFIX + mediaConf.getVideoLocal());
	}

}
