package fun.hijklmn.admin.aop;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fun.hijklmn.admin.common.ResponseUtils;
import fun.hijklmn.admin.common.ResultVO;

public abstract class BaseAspectJ {

	protected static final Logger logger = LoggerFactory.getLogger(BaseAspectJ.class);
	
	protected void outData(HttpServletResponse response, ResultVO resultVo) {
		ResponseUtils.outData(response, resultVo);
	}
	
}
