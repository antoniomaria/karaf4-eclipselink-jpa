/*******************************************************************************
 * Copyright (c) 2014 antoniomariasanchez at gmail.com. All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and is
 * available at http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors: antoniomaria - initial API and implementation
 ******************************************************************************/
package net.sf.companymanager.rest.impl;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import net.sf.companymanager.rest.OfficeResource;
import net.sf.companymanager.services.OfficeService;

// import org.osgi.service.component.annotations.Component;
// http://cxf.apache.org/dosgi-ds-demo-page.html
// https://kishanthan.wordpress.com/2014/03/29/using-annotation-with-osgi-declarative-services/
// http://wiki.osgi.org/wiki/Declarative_Services

// @Component(properties = "service.pid=org.apache.cxf.osgi", configurationPolicy = ConfigurationPolicy.require)

/**
 * Service available in http://localhost:8181/api/employee
 * 
 * @author antoniomaria
 *
 */
@Component(immediate = true, provide = OfficeResource.class)
public class OfficeResourceImpl implements OfficeResource {
    private OfficeService officeService;

    public OfficeResourceImpl() {
        System.out.println("OfficeResourceImpl activated");
    }

    @Override
    public String hello() {
        if (officeService == null) {
            System.out.println("officeService chungo");
        } else {
            System.out.println("officeService set ijuu!!!");
        }
        return "hola holitas";
    }

    @Reference
    public void setOfficeService(OfficeService officeService) {
        this.officeService = officeService;
    }

}
