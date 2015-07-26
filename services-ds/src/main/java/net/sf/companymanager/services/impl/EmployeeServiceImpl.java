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
package net.sf.companymanager.services.impl;

import net.sf.companymanager.domain.Employee;
import net.sf.companymanager.repository.EmployeeRepository;
import net.sf.companymanager.services.EmployeeService;
import net.sf.companymanager.services.support.AbstractPersistenceService;

import org.everit.osgi.transaction.helper.api.TransactionHelper;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component
public class EmployeeServiceImpl extends AbstractPersistenceService<Employee> implements EmployeeService {

    private TransactionHelper transactionHelper;

    /**
     * Set transaction helper.
     *
     * @param transactionHelper
     *            helper
     */
    @Reference
    public final void setTransactionHelper(final TransactionHelper transactionHelper) {
        this.transactionHelper = transactionHelper;
    }

    @Override
    public Employee save(Employee entity) {
        Employee result = transactionHelper.required(() -> {
            Employee fetched = null;
            if (entity.isNew()) {
                fetched = repository.save(entity);
            } else {
                fetched = repository.findOne(entity.getId());
                fetched.setName(entity.getName());
                fetched.setFamilyName(entity.getFamilyName());

            }
            return fetched;
        });
        return result;
    }

    @Reference
    public void setEmployeeRepository(final EmployeeRepository repository) {
        super.repository = repository;
    }

}
