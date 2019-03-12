package fun.hijklmn.model.service;

import java.util.List;

import fun.hijklmn.model.dto.QueryRespDTO;
import fun.hijklmn.model.dto.SoundQueryDTO;
import fun.hijklmn.model.pojo.Sound;

public interface ISoundService {

	Integer addSound(Sound sound);

	Integer updateSoundById(Sound sound);

	Sound getSoundById(String souId);

	List<Sound> listSound(Sound sound);

	QueryRespDTO<Sound> listSoundPage(SoundQueryDTO soundQueryDTO);

}
