package fun.hijklmn.model.mapper;

import java.util.List;

import fun.hijklmn.model.dto.SoundQueryDTO;
import fun.hijklmn.model.pojo.Sound;

public interface SoundMapper extends CommonMapper<Sound> {

	List<Sound> listSound(Sound sound);

	List<Sound> listSoundPage(SoundQueryDTO soundQueryDTO);

	Integer listSoundPageCount(SoundQueryDTO soundQueryDTO);

}