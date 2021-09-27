package ru.shop.three_d_print.config;

import org.springframework.stereotype.Component;
import javax.servlet.*;
import java.io.IOException;

@Component
public class CharacterSetFilter implements Filter
{
    @Override
    public void doFilter
    (
        ServletRequest request,
        ServletResponse response,
        FilterChain next
    )
    throws IOException, ServletException
    {
//        System.out.println("Filter calling");
//        request.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html; charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        next.doFilter(request, response);
    }
}
