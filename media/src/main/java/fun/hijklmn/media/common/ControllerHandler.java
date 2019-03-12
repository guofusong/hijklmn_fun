package fun.hijklmn.media.common;

import fun.hijklmn.common.dto.MediaDTO;
import fun.hijklmn.common.vo.MediaVO;

public interface ControllerHandler {

	public void handler(MediaDTO mediaDTO, MediaVO mediaVO) throws Exception;

}
