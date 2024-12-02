package com.hk.sampleproject.interceptor;

import com.hk.sampleproject.context.CommandContext;
import com.hk.sampleproject.context.RequestContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

@Slf4j
public class CommandContextInterceptor implements HandlerInterceptor {
    private static final String USER_ID_HEADER = "X-USER-ID";
    private static final String USER_IP_HEADER = "X-FORWARDED-FOR";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userId = request.getHeader(USER_ID_HEADER);
        String userIp = Optional.ofNullable(request.getHeader(USER_IP_HEADER)).orElse(request.getRemoteAddr());

        CommandContext context = CommandContext.of(userId, userIp);
        RequestContextHolder.setContext(context);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        RequestContextHolder.clearContext();
    }
}
