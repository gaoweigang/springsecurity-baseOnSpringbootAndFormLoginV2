package com.gwg.user.web.authority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gwg.user.web.configuration.AuthUser;
import lombok.Data;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Data
public class RequestContext {
    private static final Log LOG = LogFactory.getLog(RequestContext.class);

    private static final ThreadLocal<RequestContext> REQUEST_CONTEXT =  new ThreadLocal<>();

    public synchronized static RequestContext getOrCreate() {
        RequestContext instance = REQUEST_CONTEXT.get();
        if (null == instance) {
            instance = new RequestContext();
            REQUEST_CONTEXT.set(instance);
        }
        return instance;
    }

    private HttpServletRequest request;
    private HttpServletResponse response;
    protected AuthUser authUser;


}
