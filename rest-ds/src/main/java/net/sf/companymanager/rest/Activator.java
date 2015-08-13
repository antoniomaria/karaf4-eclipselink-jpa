package net.sf.companymanager.rest;

import java.util.Dictionary;
import java.util.Hashtable;

import net.sf.companymanager.rest.impl.EmployeeResourceImpl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
// https://github.com/apache/cxf-dosgi/tree/master/samples/greeter_rest
public class Activator implements BundleActivator {

    private ServiceRegistration registration;

    public void start(BundleContext bc) throws Exception {
        Dictionary<String, Object> props = getProperties("/employee");
        registration = bc.registerService(EmployeeResource.class.getName(), new EmployeeResourceImpl(), props);
    }

    private Dictionary<String, Object> getProperties(String address) {
        Dictionary<String, Object> props = new Hashtable<String, Object>();

        props.put("service.exported.interfaces", "*");
        props.put("service.exported.configs", "org.apache.cxf.rs");
        props.put("service.exported.intents", "HTTP");
        props.put("org.apache.cxf.rs.httpservice.context", address);
        //props.put("org.apache.cxf.rs.address", "/employee");
        return props;
    }

    public void stop(BundleContext bc) throws Exception {
        registration.unregister();
    }
}