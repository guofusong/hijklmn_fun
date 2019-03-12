package fun.hijklmn.model.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fun.hijklmn.model.dto.DocumentQueryDTO;
import fun.hijklmn.model.dto.QueryRespDTO;
import fun.hijklmn.model.mapper.DocumentMapper;
import fun.hijklmn.model.pojo.Document;
import fun.hijklmn.model.service.IDocumentService;

@Service("documentService")
public class DocumentServiceImpl implements IDocumentService {

	@Resource
	private DocumentMapper documentMapper;

	@Override
	public Integer addDocument(Document document) {
		return documentMapper.insertSelective(document);
	}

	@Override
	public Integer updateDocumentById(Document document) {
		return documentMapper.updateByPrimaryKeySelective(document);
	}

	@Override
	public Document getDocumentById(String docId) {
		return documentMapper.selectByPrimaryKey(docId);
	}

	@Override
	public List<Document> listDocument(Document document) {
		return documentMapper.listDocument(document);
	}

	@Override
	public QueryRespDTO<Document> listDocumentPage(DocumentQueryDTO documentQueryDTO) {
		final QueryRespDTO<Document> queryRespDTO = new QueryRespDTO<>();
		queryRespDTO.setIndex(documentQueryDTO.getIndex());
		queryRespDTO.setSize(documentQueryDTO.getSize());
		Integer count = documentMapper.listDocumentPageCount(documentQueryDTO);
		queryRespDTO.setTotal(count);
		if (count > 0) {
			queryRespDTO.setItems(documentMapper.listDocumentPage(documentQueryDTO));
		}
		return queryRespDTO;
	}

}
