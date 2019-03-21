package fun.hijklmn.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import fun.hijklmn.admin.conf.ConstantsConf;
import fun.hijklmn.model.service.IDocumentService;
import fun.hijklmn.model.service.IImageService;
import fun.hijklmn.model.service.IMenuService;
import fun.hijklmn.model.service.ISoundService;
import fun.hijklmn.model.service.ISysUserService;

public abstract class BaseController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected RestTemplate restTemplate;

	@Autowired
	protected ConstantsConf constantsConf; 
	
	@Autowired
	protected ISysUserService sysUserService;

	@Autowired
	protected IMenuService menuService;
	
	@Autowired
	protected ISoundService soundService;
	
	@Autowired
	protected IImageService imageService;
	
	@Autowired
	protected IDocumentService documentService;
	
}
