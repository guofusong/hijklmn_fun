package fun.hijklmn.admin.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import fun.hijklmn.admin.common.ResponseUtils;
import fun.hijklmn.admin.common.ResultVO;
import fun.hijklmn.admin.common.WebConstants;
import fun.hijklmn.common.constants.RespEnum;
import fun.hijklmn.common.utils.JSONUtils;
import fun.hijklmn.model.pojo.SysUser;

@Order(0)
@WebFilter(filterName = "authFilter", urlPatterns = "*")
public class AuthFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

	@Override
	public void destroy() {
		logger.info("authFilter init . . . ");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;

		String requestUrl = req.getRequestURI();

		if (requestUrl.equals("/") || requestUrl.contains("/common") || requestUrl.contains("/module")
				|| requestUrl.contains("/notAllow") || requestUrl.contains("/user/signin")
				|| requestUrl.contains("/user/signout") || requestUrl.contains("/favicon.ico")) {
			chain.doFilter(request, response);
			return;
		}

		SysUser sysUser = (SysUser) req.getSession().getAttribute(WebConstants.USER_KEY);

		final ResultVO resultVo = new ResultVO();

		if (sysUser == null) {
			if (ResponseUtils.isAjax(req)) {
				resultVo.setCustomReason(RespEnum.SesExpr.code(), RespEnum.SesExpr.cnDesc());
				outData(rep, resultVo);
				return;
			} else {
				rep.sendRedirect("/");
				return;
			}
		} else {
			String permissions = sysUser.getPermissions();
			if (StringUtils.isBlank(permissions) || !permissions.contains(requestUrl)) {
				if (ResponseUtils.isAjax(req)) {
					resultVo.setCustomReason(RespEnum.PerDeni.code(), RespEnum.PerDeni.cnDesc());
					outData(rep, resultVo);
					return;
				} else {
					rep.sendRedirect("/notAllow");
					return;
				}
			}
		}

		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		logger.info("authFilter init . . . ");
	}

	private static void outData(HttpServletResponse response, ResultVO resultVo) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		String json = JSONUtils.toJsonStr(resultVo);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append(json);
		} catch (IOException e) {
			logger.error("返回json错误！[{}]", json);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
