package com.example.bookstore.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class ActuatorFilter implements Filter {

    private  static Logger log = LoggerFactory.getLogger(ActuatorFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        log.info("ActuatorFilter - doFilter");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
