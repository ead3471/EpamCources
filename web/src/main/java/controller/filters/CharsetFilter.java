package controller.filters;

import javax.servlet.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Freemind on 2016-10-22.
 */
public class CharsetFilter implements Filter {
    private String encoding;



    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, UnsupportedEncodingException {

        request.setCharacterEncoding(encoding);

        response.setCharacterEncoding(encoding);



        chain.doFilter(request, response);

    }

    public void init(FilterConfig fConfig) throws ServletException {

        encoding = fConfig.getInitParameter("characterEncoding");



    }
}
