package fun.hijklmn.stage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import fun.hijklmn.model.service.IDocumentService;
import fun.hijklmn.model.service.IImageService;
import fun.hijklmn.model.service.ISoundService;
import fun.hijklmn.stage.conf.ConstantsConf;

public abstract class BaseController {

	protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@Autowired
	protected ConstantsConf constantsConf;

	@Autowired
	protected ISoundService soundService;
	
	@Autowired
	protected IDocumentService documentService;
	
	@Autowired
	protected IImageService imageService;
	
}
