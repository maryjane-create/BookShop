package com.example.bookstore.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
@Order
public class BookFilter2  implements Filter {

    private  static Logger log = LoggerFactory.getLogger(BookFilter2.class);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("BookFilter2 -doFilter");
        chain.doFilter(request, response);
    }
}
