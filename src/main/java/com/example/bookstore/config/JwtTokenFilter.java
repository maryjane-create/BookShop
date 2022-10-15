package com.example.bookstore.config;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenProvider tokenProvider;

    public JwtTokenFilter (JwtTokenProvider tokenProvider){
        this.tokenProvider=tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        try{
            if (token !=null && tokenProvider.validateToken(token)){
                SecurityContextHolder.getContext().setAuthentication(tokenProvider.getAuthentication(token));
            }
        }catch (RuntimeException e){
            try {
                SecurityContextHolder.clearContext();
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().println(new JSONObject().put("exception", "expired or invalid token" +e.getMessage()));
            }catch (IOException | JSONException exception){
                exception.printStackTrace();
            }
            return;
        }
        filterChain.doFilter(request, response);

    }
}
