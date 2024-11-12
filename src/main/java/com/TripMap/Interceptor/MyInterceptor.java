package com.TripMap.Interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

@Component
public class MyInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
    private final AtomicLong totalVisits = new AtomicLong(0);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long currentVisits = totalVisits.incrementAndGet();
        
        String clientIP = request.getRemoteAddr();
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        
        logger.info("访问信息 - IP: {}, 方法: {}, 路径: {}, 总访问次数: {}", 
                    clientIP, method, requestURI, currentVisits);
        
        return true;
    }

    public long getTotalVisits() {
        return totalVisits.get();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.debug("请求处理完成: {}", request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null) {
            logger.error("请求处理发生错误: {}", ex.getMessage());
        }
    }
}