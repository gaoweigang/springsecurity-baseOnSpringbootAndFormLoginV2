package com.gwg.user.web.configuration;


import com.gwg.user.web.authority.RequestContext;
import com.gwg.user.web.common.Constant;
import com.gwg.user.web.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器,为什么需要这个拦截器？将用户信息放到与处理当前请求相关线程的ThreadLocal中，因为处理每个请求的线程可能不同
 */
public class GenericRequestInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(GenericRequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuthUser authUser= SessionUtil.getSessionAttribute(Constant.USER_SESSION, request);
        RequestContext ctxt = RequestContext.getOrCreate();
        ctxt.setAuthUser(authUser);
        ctxt.setResponse(response);
        ctxt.setRequest(request);
        return true;
    }
}
