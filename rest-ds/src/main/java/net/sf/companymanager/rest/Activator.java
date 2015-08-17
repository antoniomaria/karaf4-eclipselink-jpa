package net.sf.companymanager.rest;

import java.util.Dictionary;
import java.util.Hashtable;

import net.sf.companymanager.rest.impl.EmployeeResourceImpl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
// https://github.com/apache/cxf-dosgi/tree/master/samples/greeter_rest
// http://git.eclipse.org/c/ecf/org.eclipse.ecf.git/tree/examples/bundles

public class Activator implements BundleActivator {

    private ServiceRegistration registration;

    @Override
    public void start(final BundleContext bc) throws Exception {
        final Dictionary<String, Object> props = getProperties("/api");
        registration = bc.registerService(EmployeeResource.class.getName(), new EmployeeResourceImpl(), props);
    }

    private Dictionary<String, Object> getProperties(final String address) {
        final Dictionary<String, Object> props = new Hashtable<String, Object>();

        props.put("service.exported.interfaces", "*");
        props.put("service.exported.configs", "org.apache.cxf.rs");
        props.put("service.exported.intents", "HTTP");
        props.put("org.apache.cxf.rs.httpservice.context", address);
        // props.put("org.apache.cxf.rs.address", "/employee");
        return props;
    }

    @Override
    public void stop(final BundleContext bc) throws Exception {
        registration.unregister();
    }
}