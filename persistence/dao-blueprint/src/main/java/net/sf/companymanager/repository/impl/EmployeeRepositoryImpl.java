package net.sf.companymanager.repository.impl;

import javax.persistence.PersistenceContext;

import net.sf.companymanager.domain.Employee;
import net.sf.companymanager.repository.EmployeeRepository;
import net.sf.companymanager.repository.support.AbstractJPARepository;

import org.apache.aries.jpa.supplier.EmSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeRepositoryImpl extends AbstractJPARepository<Employee> implements EmployeeRepository {

    /**
     * Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(EmployeeRepositoryImpl.class);

    @PersistenceContext(unitName = "companymanager")
    protected EmSupplier em;

    public EmployeeRepositoryImpl() {
        super(Employee.class);
        logger.debug("EmployeeRepositoryImpl instance created");
    }

    public void setEm(EmSupplier em) {
        this.em = em;
    }

    @Override
    protected EmSupplier getEm() {
        return this.em;
    }
}
