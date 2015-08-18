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

//import org.osgi.service.component.annotations.Component;
// http://cxf.apache.org/dosgi-ds-demo-page.html
// https://kishanthan.wordpress.com/2014/03/29/using-annotation-with-osgi-declarative-services/
// http://wiki.osgi.org/wiki/Declarative_Services

//@Component(properties = "service.pid=org.apache.cxf.osgi", configurationPolicy = ConfigurationPolicy.require)

/**
 * Service available in http://localhost:8181/api/employee 
 * @author antoniomaria
 *
 */
@Component(provide = EmployeeResource.class, properties = { "service.exported.interfaces=*", "service.exported.configs=org.apache.cxf.rs",
        "org.apache.cxf.rs.httpservice.context=/api", "org.apache.cxf.rs.address=/", "service.exported.intents=HTTP" })
public class EmployeeResourceImpl implements EmployeeResource {

    public EmployeeResourceImpl() {
        System.out.println("EmployeeResourceImpl activated");
    }

    @Override
    public String hello() {
        return "hola holitas";
    }
}
