package fun.hijklmn.model.mapper;

import java.util.List;

import fun.hijklmn.model.dto.ImageQueryDTO;
import fun.hijklmn.model.pojo.Image;

public interface ImageMapper extends CommonMapper<Image> {

	List<Image> listImage(Image image);

	List<Image> listImagePage(ImageQueryDTO imageQueryDTO);

	Integer listImagePageCount(ImageQueryDTO imageQueryDTO);

}