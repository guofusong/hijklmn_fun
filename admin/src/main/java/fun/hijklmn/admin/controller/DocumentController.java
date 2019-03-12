package fun.hijklmn.admin.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fun.hijklmn.admin.common.ControllerHandler;
import fun.hijklmn.admin.common.ControllerProxy;
import fun.hijklmn.admin.common.ResultVO;
import fun.hijklmn.admin.common.WebGetter;
import fun.hijklmn.admin.conf.ConstantsConf;
import fun.hijklmn.common.constants.RespEnum;
import fun.hijklmn.common.utils.IDGenerateUtils;
import fun.hijklmn.common.utils.JSONUtils;
import fun.hijklmn.model.dto.QueryReqDTO;
import fun.hijklmn.model.pojo.Document;
import fun.hijklmn.model.pojo.SysUser;
import fun.hijklmn.model.service.IDocumentService;

@Controller
public class DocumentController {

	@Autowired
	private IDocumentService documentService;

	@Autowired
	private ConstantsConf constantsConf;

	@RequestMapping(value = "/document/check")
	public void check(HttpServletRequest request, HttpServletResponse response) {
		ControllerProxy.assembleAjax(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {

				String title = WebGetter.getString("title", request);
				String subTitle = WebGetter.getString("subTitle", request);

				Document document = new Document();
				document.setTitle(title);
				document.setSubTitle(subTitle);
				document.setIsValid(1);

				List<Document> documents = documentService.listDocument(document);

				resultVo.setResult(documents.size() > 0 ? 1 : 0);

				return null;

			}

		}, request, response, null);
	}

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
	public void queryPageData(HttpServletRequest request, HttpServletResponse response) {

		ControllerProxy.assembleAjax(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {

				Document document = new Document();

				document.setIsValid(1);

				List<Document> documents = documentService.listDocument(document);

				resultVo.setResult(documents);

				return null;

			}

		}, request, response, null);

	}

	@RequestMapping(value = "/document/detail")
	public String detail(HttpServletRequest request, HttpServletResponse response) {
		return ControllerProxy.assemble(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) {

				String docId = WebGetter.getString("docId", request);

				Document document = documentService.getDocumentById(docId);

				request.setAttribute("doc", JSONUtils.toJsonStr(document));

				request.setAttribute("ckUploadReqUrl", constantsConf.getCkUploadReqUrl());

				return "document/detail";

			}

		}, request, response, null);
	}

	@RequestMapping(value = "/document/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		ControllerProxy.assembleAjax(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {

				String docId = WebGetter.getString("docId", request);

				Document document = documentService.getDocumentById(docId);

				if (document != null) {
					document.setIsValid(0);
					Integer count = documentService.updateDocumentById(document);
					if (count == null || count < 1) {
						resultVo.setCustomReason(RespEnum.Faild.code(), RespEnum.Faild.cnDesc());
					}
				}

				return null;
			}
		}, request, response, null);
	}

	@RequestMapping(value = "/document/save")
	public void save(HttpServletRequest request, HttpServletResponse response) {
		ControllerProxy.assembleAjax(new ControllerHandler() {

			@Override
			public String handler(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
					ResultVO resultVo, QueryReqDTO queryReqDTO) throws Exception {

				String params = WebGetter.getString("params", request);

				Document document = JSONUtils.toBean(params, Document.class);

				int count = 0;

				if (StringUtils.isBlank(document.getDocId())) {
					document.setIsValid(1);
					document.setDocId(IDGenerateUtils.generateDocumentId());
					document.setOnlineTime(new Date());
					count = documentService.addDocument(document);
				} else {
					count = documentService.updateDocumentById(document);
				}

				if (count == 0) {
					resultVo.setCustomReason(RespEnum.Faild.code(), RespEnum.Faild.cnDesc());
				}

				return null;

			}
		}, request, response, null);
	}

}
