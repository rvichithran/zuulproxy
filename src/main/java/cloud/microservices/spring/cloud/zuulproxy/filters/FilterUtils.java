package cloud.microservices.spring.cloud.zuulproxy.filters;

import cloud.microservices.spring.cloud.zuulproxy.utils.UserContext;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

@Component
public class FilterUtils {

    public static final String PRE_FILTER = "pre";
    public static final String POST_FILTER = "post";

    public String getCorrelationId() {
        RequestContext ctx = RequestContext.getCurrentContext();
        if(ctx.getRequest().getHeader(UserContext.CORRELATION_ID) != null) {
            System.out.println("Request header [Correlation Id] : "+ctx.getRequest().getHeader(UserContext.CORRELATION_ID));
            return ctx.getRequest().getHeader(UserContext.CORRELATION_ID);
        } else {
            System.out.println("Zuul request header [Correlation Id] : "+ctx.getZuulRequestHeaders().get(UserContext.CORRELATION_ID));
            return ctx.getZuulRequestHeaders().get(UserContext.CORRELATION_ID);
        }
    }

    public void setCorrelationId(final String correlationId) {
        RequestContext ctx = RequestContext.getCurrentContext();
        System.out.println(" Setting correlation id : "+correlationId);
        ctx.addZuulRequestHeader(UserContext.CORRELATION_ID, correlationId);
    }

}
