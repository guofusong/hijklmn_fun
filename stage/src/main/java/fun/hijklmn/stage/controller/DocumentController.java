package fun.hijklmn.stage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fun.hijklmn.common.utils.JSONUtils;
import fun.hijklmn.model.dto.DocumentQueryDTO;
import fun.hijklmn.model.dto.QueryReqDTO;
import fun.hijklmn.model.dto.QueryRespDTO;
import fun.hijklmn.model.pojo.Document;
import fun.hijklmn.model.pojo.SysUser;
import fun.hijklmn.model.service.IDocumentService;
import fun.hijklmn.stage.common.ControllerHandler;
import fun.hijklmn.stage.common.ControllerProxy;
import fun.hijklmn.stage.common.ResultVO;
import fun.hijklmn.stage.common.WebGetter;

@Controller
public class DocumentController {

	@Autowired
	private IDocumentService documentService;

	@RequestMapping(value = "/document/view")
	public String view(HttpServletRequest request, HttpServletResponse response) {
		return ControllerProxy.assemble(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {
				return "document/view";
			}

		}, request, response, null);
	}

	@RequestMapping(value = "/document/queryData")
	public void queryData(HttpServletRequest request, HttpServletResponse response) {

		final DocumentQueryDTO documentQueryDTO = new DocumentQueryDTO();

		ControllerProxy.assembleAjax(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {

				documentQueryDTO.setIsValid(1);

				documentQueryDTO.setIsBlog((byte) 0);

				QueryRespDTO<Document> queryRespDTO = documentService.listDocumentPage(documentQueryDTO);

				resultVo.setGridData(queryRespDTO);

				return null;

			}

		}, request, response, documentQueryDTO);
	}

	@RequestMapping(value = "/document/detail")
	public String detail(HttpServletRequest request, HttpServletResponse response) {
		return ControllerProxy.assemble(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {

				String docId = WebGetter.getString("docId", request);

				Document document = documentService.getDocumentById(docId);

				request.setAttribute("doc", JSONUtils.toJsonStr(document));

				return "document/detail";
			}

		}, request, response, null);
	}

}
