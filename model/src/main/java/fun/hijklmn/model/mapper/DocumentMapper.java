package fun.hijklmn.model.mapper;

import java.util.List;

import fun.hijklmn.model.dto.DocumentQueryDTO;
import fun.hijklmn.model.pojo.Document;

public interface DocumentMapper extends CommonMapper<Document> {

	List<Document> listDocument(Document document);

	List<Document> listDocumentPage(DocumentQueryDTO documentQueryDTO);

	Integer listDocumentPageCount(DocumentQueryDTO documentQueryDTO);

}