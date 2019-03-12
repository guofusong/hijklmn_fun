package fun.hijklmn.model.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fun.hijklmn.model.dto.QueryRespDTO;
import fun.hijklmn.model.dto.SoundQueryDTO;
import fun.hijklmn.model.mapper.SoundMapper;
import fun.hijklmn.model.pojo.Sound;
import fun.hijklmn.model.service.ISoundService;

@Service("soundService")
public class SoundServiceImpl implements ISoundService {

	@Resource
	private SoundMapper soundMapper;

	@Override
	public Integer addSound(Sound sound) {
		return soundMapper.insertSelective(sound);
	}

	@Override
	public Integer updateSoundById(Sound sound) {
		return soundMapper.updateByPrimaryKeySelective(sound);
	}

	@Override
	public Sound getSoundById(String souId) {
		return soundMapper.selectByPrimaryKey(souId);
	}

	@Override
	public List<Sound> listSound(Sound sound) {
		return soundMapper.listSound(sound);
	}

	@Override
	public QueryRespDTO<Sound> listSoundPage(SoundQueryDTO soundQueryDTO) {
		final QueryRespDTO<Sound> queryRespDTO = new QueryRespDTO<>();
		queryRespDTO.setIndex(soundQueryDTO.getIndex());
		queryRespDTO.setSize(soundQueryDTO.getSize());
		Integer count = soundMapper.listSoundPageCount(soundQueryDTO);
		queryRespDTO.setTotal(count);
		if (count > 0) {
			queryRespDTO.setItems(soundMapper.listSoundPage(soundQueryDTO));
		}
		return queryRespDTO;
	}

}
