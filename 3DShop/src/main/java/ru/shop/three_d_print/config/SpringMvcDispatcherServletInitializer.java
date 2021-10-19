package ru.shop.three_d_print.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

public class SpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
    @Override
    protected Class<?>[] getRootConfigClasses()
    {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses()
    {
        return new Class[] {SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings()
    {
        return new String[] {"/"};
    }

    @WebFilter(urlPatterns = { "*.html" })
    public class CharacterSetFilter implements Filter
    {
        @Override
        public void doFilter
        (
                ServletRequest request,
                ServletResponse response,
                FilterChain chain
        ) throws IOException, ServletException
        {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            chain.doFilter(request, response);
        }
    }
}


