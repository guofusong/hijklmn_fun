package fun.hijklmn.stage.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

@Order(1)
@WebFilter(filterName = "encodeFilter", urlPatterns = "*")
public class EncodeFilter implements Filter {

	private static final String CHARSET = "utf-8";

	private final Logger logger = LoggerFactory.getLogger(EncodeFilter.class);

	@Override
	public void destroy() {
		logger.info("encodeFilter destroy . . . ");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(CHARSET);
		response.setCharacterEncoding(CHARSET);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		logger.info("encodeFilter init . . . ");
	}

}
