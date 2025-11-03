package com.example.evsalesmanagement.filter;

import java.io.IOException;
import java.net.http.HttpRequest;

import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class LoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
        HttpServletRequest serverHttpRequest = (HttpServletRequest) arg0;
        System.out.println("Check log" + " " + serverHttpRequest.getRequestURL());

        arg2.doFilter(arg0, arg1);
    }

}
