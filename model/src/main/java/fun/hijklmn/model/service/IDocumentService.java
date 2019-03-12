package fun.hijklmn.model.service;

import java.util.List;

import fun.hijklmn.model.dto.DocumentQueryDTO;
import fun.hijklmn.model.dto.QueryRespDTO;
import fun.hijklmn.model.pojo.Document;

public interface IDocumentService {

	Integer addDocument(Document document);

	Integer updateDocumentById(Document document);

	Document getDocumentById(String docId);

	List<Document> listDocument(Document document);

	QueryRespDTO<Document> listDocumentPage(DocumentQueryDTO documentQueryDTO);

}
