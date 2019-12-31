package cloud.microservices.spring.cloud.zuulproxy.filters;

import cloud.microservices.spring.cloud.zuulproxy.utils.UserContext;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseFilter extends ZuulFilter {

    private static final boolean SHOULD_FILTER = true;
    private static final int FILTER_ORDER = 1;

    @Autowired
    private FilterUtils filterUtils;


    @Override
    public String filterType() {
        return FilterUtils.POST_FILTER;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        System.out.println("Adding the correlation id to the outbound headers. " + filterUtils.getCorrelationId());
        ctx.getResponse().addHeader(UserContext.CORRELATION_ID, filterUtils.getCorrelationId());
        System.out.println("Completing outgoing request for "+ ctx.getRequest().getRequestURI());
        return null;

    }
}