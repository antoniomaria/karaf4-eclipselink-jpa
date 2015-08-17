/*******************************************************************************
 * Copyright (c) 2014 antoniomariasanchez at gmail.com.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     antoniomaria - initial API and implementation
 ******************************************************************************/
package net.sf.companymanager.rest.impl;

import net.sf.companymanager.rest.EmployeeResource;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.ConfigurationPolicy;

//import org.osgi.service.component.annotations.Component;
// http://cxf.apache.org/dosgi-ds-demo-page.html
// https://kishanthan.wordpress.com/2014/03/29/using-annotation-with-osgi-declarative-services/
// http://wiki.osgi.org/wiki/Declarative_Services
//@Component(service = EmployeeResource.class, properties = { "org.apache.cxf.ws.address=http://localhost:9090/adder" })
//@Component(provide = EmployeeResource.class, properties = { "org.apache.cxf.ws.address=http://localhost:8181/employee",
//      "service.exported.interfaces=*", "service.exported.configs=org.apache.cxf.ws" })
//  @Component(properties="service.pid=
//@Component(properties = "service.pid=org.apache.karaf.webconsole")
//@Component(properties = "service.pid=org.apache.cxf.osgi", configurationPolicy = ConfigurationPolicy.require)
@Component(configurationPolicy = ConfigurationPolicy.require)
public class EmployeeResourceImpl implements EmployeeResource {

    public EmployeeResourceImpl() {
        System.out.println("EmployeeResourceImpl activated");
    }

    @Override
    public String hello() {
        return "hola holitas";
    }
}
