package net.sf.companymanager.discovery;

import java.util.Dictionary;
import java.util.Hashtable;

import javax.servlet.Filter;

import org.apache.shiro.web.servlet.IniShiroFilter;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityManager implements BundleActivator {

    /**
      * Logger.
      */
    private static final Logger LOG = LoggerFactory.getLogger(SecurityManager.class);
    private ServiceRegistration restRegistration;
    private ServiceRegistration filterRegistration;

    @Override
    public void start(final BundleContext bundleContext) throws Exception {
        // Register a servlet filter (this could be done in another OSGi bundle,
        // too)
        final Dictionary<String, Object> filterProps = new Hashtable<String, Object>();
        filterProps.put("org.apache.cxf.httpservice.filter", Boolean.TRUE);
        // Pax-Web whiteboard (if deployed) will attempt to apply this filter to
        // servlets by name or URL, and will complain
        // if neither servletName or urlPatterns are specified. The felix http
        // service whiteboard may do something similar.
        filterProps.put("servletNames", "none");

        final IniShiroFilter shiro = new IniShiroFilter();
        final String karafEtc = System.getProperty("karaf.etc");
        final String configPath = "file:" + karafEtc + "/shiro.ini";
        shiro.setConfigPath(configPath);
        shiro.init();
        filterRegistration = bundleContext.registerService(Filter.class.getName(), shiro, filterProps);
        LOG.info("Shiro filter registered!!!");
    }

    @Override
    public void stop(final BundleContext bundleContext) throws Exception {
        // restRegistration.unregister();
        filterRegistration.unregister();
    }

}
