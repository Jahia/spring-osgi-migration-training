package fr.sample.jahia.training.components.jaxrs;

import fr.sample.jahia.training.services.HelloService;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Custom servlet
 *
 * @author tleclere
 */
@Component(service = Servlet.class, property = "alias=/servlet")
public class SampleServlet extends HttpServlet {
    private static final long serialVersionUID = 8080894228388723111L;

    private Bundle bundle;

    private HelloService helloService;

    @Activate
    public void onActivate(BundleContext bundleContext) {
        bundle = bundleContext.getBundle();
    }

    @Reference
    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(request);
        wrapper.setAttribute("config", helloService.getMessage("toto"));
        wrapper.getRequestDispatcher("/modules/" + bundle.getSymbolicName() + "/custom.jsp").include(request, response);
    }
}
