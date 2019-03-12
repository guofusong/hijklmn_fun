package fun.hijklmn.model.service;

import java.util.List;

import fun.hijklmn.model.dto.ImageQueryDTO;
import fun.hijklmn.model.dto.QueryRespDTO;
import fun.hijklmn.model.pojo.Image;

public interface IImageService {

	Integer addImage(Image image);

	Integer updateImageById(Image image);

	Image getImageById(String imgId);

	List<Image> listImage(Image image);

	QueryRespDTO<Image> listImagePage(ImageQueryDTO imageQueryDTO);

}
