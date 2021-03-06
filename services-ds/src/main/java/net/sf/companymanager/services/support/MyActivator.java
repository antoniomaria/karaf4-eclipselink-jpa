/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIESOR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package net.sf.companymanager.services.support;

import net.sf.companymanager.domain.Employee;
import net.sf.companymanager.domain.Office;
import net.sf.companymanager.services.EmployeeService;
import net.sf.companymanager.services.OfficeService;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true)
public class MyActivator {

    /**
     * Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(MyActivator.class);

    private EmployeeService employeeService;

    private OfficeService officeService;

    @Reference
    public void setEmployeeService(final EmployeeService invitationService) {
        this.employeeService = invitationService;
    }

    @Reference
    public void setOfficeService(OfficeService officeService) {
        this.officeService = officeService;
    }

    @Activate
    public void addEmployee() {
        logger.info("Adding Employee ...");
        Employee employee = Employee.with().givenNames("Antonio Maria").familyName("Sanchez").build();
        Employee saved = employeeService.save(employee);
        logger.info("Employee created with Id = {}", saved.getId());
        logger.info("Adding a office");
        Office office = new Office.Builder().name("My office").build();
        office = officeService.save(office);
        logger.info("Office created with id={}", office.getId());
    }
}
