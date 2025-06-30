package com.eventsequor.interceptors.interceptos;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component("loadingTimeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod controller = ((HandlerMethod) handler);
        logger.info("LoadingTimeInterceptor: preHandle, login... " + controller.getMethod().getName());
        long start = System.currentTimeMillis();
        request.setAttribute("start", start);

        Map<String, String> json = new HashMap<>();
        json.put("error", "you don't have access to this resource");
        json.put("date", new Date().toString());

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(json);
        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().write(jsonString);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("End time: " + (System.currentTimeMillis() - (long) request.getAttribute("start")));
        logger.info("LoadingTimeInterceptor: postHandle logout ... " + ((HandlerMethod) handler).getMethod().getName());
    }
}
