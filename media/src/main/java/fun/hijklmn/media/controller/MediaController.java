package fun.hijklmn.media.controller;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.hijklmn.common.constants.RespEnum;
import fun.hijklmn.common.dto.MediaDTO;
import fun.hijklmn.common.utils.EncodeUtils;
import fun.hijklmn.common.utils.FileUtils;
import fun.hijklmn.common.utils.IDGenerateUtils;
import fun.hijklmn.common.vo.MediaVO;
import fun.hijklmn.media.common.ControllerHandler;
import fun.hijklmn.media.common.ControllerProxy;
import fun.hijklmn.media.conf.MediaConf;

@RestController
public class MediaController {

	@Autowired
	private MediaConf mediaConf;

	@RequestMapping("/media/upload")
	public MediaVO upload(@RequestBody MediaDTO mediaDTO) {
		return ControllerProxy.assemble(new ControllerHandler() {
			@Override
			public void handler(MediaDTO mediaDTO, MediaVO mediaVO) throws IOException {

				String filename = IDGenerateUtils.generateMediaId() + "." + mediaDTO.getSuffix();
				byte[] b = EncodeUtils.decode(mediaDTO.getContent());

				mediaVO.setName(filename);
				mediaVO.setSuffix(mediaDTO.getSuffix());

				String local = null;

				switch (mediaDTO.getMediaType()) {
				case "Image":
					local = mediaConf.getImageLocal();
					break;
				case "Sound":
					local = mediaConf.getSoundLocal();
					break;
				case "Video":
					local = mediaConf.getVideoLocal();
					break;
				default:
					mediaVO.setCode(RespEnum.InvParam.code());
					break;
				}

				FileUtils.write(local, filename, ByteBuffer.wrap(b));
			}
		}, mediaDTO);
	}

}
