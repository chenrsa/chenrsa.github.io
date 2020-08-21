package com.example.demo.filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author chenrunzheng
 * @since 2020/8/17 11:35
 */
@Component
public class filter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        if (httpServletRequest.getServletPath().contains("/springtest")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else {
            ((HttpServletResponse)servletResponse).sendError(HttpStatus.UNAUTHORIZED.value());
        }
    }
}
