package com.ppk.log4j_example.common.filter;

import com.ppk.log4j_example.common.utils.Hello4jUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.ppk.log4j_example.common.constans.Hello4jConstans.LOG_CORRELATIONID_KEY;
import static com.ppk.log4j_example.common.constans.Hello4jConstans.LOG_TRANS_KEY;

@Component
public class Hello4jInterceptor extends HandlerInterceptorAdapter {

    @Autowired private Hello4jUtils hello4jUtils;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        MDC.put(LOG_CORRELATIONID_KEY,this.hello4jUtils.generateCorrelationId());
        MDC.put(LOG_TRANS_KEY, httpServletRequest.getParameter("requestTrans"));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        MDC.remove(LOG_CORRELATIONID_KEY);
        MDC.clear();
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
