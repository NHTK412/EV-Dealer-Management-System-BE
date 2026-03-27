package com.example.evsalesmanagement.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoggerFilter implements Filter {

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(LoggerFilter.class);

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
        HttpServletRequest serverHttpRequest = (HttpServletRequest) arg0;
        // System.out.println("Check log" + " " + serverHttpRequest.getRequestURL());
        logger.info("Request URL: " + serverHttpRequest.getRequestURL());

        arg2.doFilter(arg0, arg1);
    }

}
