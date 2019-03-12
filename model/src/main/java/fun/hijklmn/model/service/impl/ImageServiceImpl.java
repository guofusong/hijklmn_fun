package fun.hijklmn.model.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fun.hijklmn.model.dto.ImageQueryDTO;
import fun.hijklmn.model.dto.QueryRespDTO;
import fun.hijklmn.model.mapper.ImageMapper;
import fun.hijklmn.model.pojo.Image;
import fun.hijklmn.model.service.IImageService;

@Service("imageService")
public class ImageServiceImpl implements IImageService {

	@Resource
	private ImageMapper imageMapper;

	@Override
	public Integer addImage(Image image) {
		return imageMapper.insertSelective(image);
	}

	@Override
	public Integer updateImageById(Image image) {
		return imageMapper.updateByPrimaryKeySelective(image);
	}

	@Override
	public Image getImageById(String imgId) {
		return imageMapper.selectByPrimaryKey(imgId);
	}

	@Override
	public List<Image> listImage(Image image) {
		return imageMapper.listImage(image);
	}

	@Override
	public QueryRespDTO<Image> listImagePage(ImageQueryDTO imageQueryDTO) {
		final QueryRespDTO<Image> queryRespDTO = new QueryRespDTO<>();
		queryRespDTO.setIndex(imageQueryDTO.getIndex());
		queryRespDTO.setSize(imageQueryDTO.getSize());
		Integer count = imageMapper.listImagePageCount(imageQueryDTO);
		queryRespDTO.setTotal(count);
		if (count > 0) {
			queryRespDTO.setItems(imageMapper.listImagePage(imageQueryDTO));
		}
		return queryRespDTO;
	}

}
