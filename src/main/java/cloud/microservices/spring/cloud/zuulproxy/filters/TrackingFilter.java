package cloud.microservices.spring.cloud.zuulproxy.filters;

import com.netflix.zuul.ZuulFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TrackingFilter extends ZuulFilter {

    private static final boolean SHOULD_FILTER = true;
    private static final int FILTER_ORDER = 1;

    @Autowired
    private FilterUtils filterUtils;

    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER;
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
    public Object run() {
        if(filterUtils.getCorrelationId() == null) {
            filterUtils.setCorrelationId(generateCorrelationId());
        }
        return null;
    }

    private String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
