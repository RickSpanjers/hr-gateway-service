package nl.hrmanagement.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;

import javax.servlet.http.HttpSession;

public class SessionSavingZuulFilter extends ZuulFilter {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpSession httpSession = requestContext.getRequest().getSession();
        Session session = sessionRepository.getSession(httpSession.getId());

        requestContext.addZuulRequestHeader("Cookie", "SESSION=" + session.getId());

        return null;
    }
}
