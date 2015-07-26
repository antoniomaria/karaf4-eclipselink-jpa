package net.sf.companymanager.repository.impl;

import javax.persistence.PersistenceContext;

import net.sf.companymanager.domain.Department;
import net.sf.companymanager.repository.DepartmentRepository;
import net.sf.companymanager.repository.support.AbstractJPARepository;

import org.apache.aries.jpa.supplier.EmSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DepartmentRepositoryImpl extends AbstractJPARepository<Department> implements DepartmentRepository {

    /**
     * Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(DepartmentRepositoryImpl.class);

    @PersistenceContext(unitName = "companymanager")
    protected EmSupplier em;

    public DepartmentRepositoryImpl() {
        super(Department.class);
        logger.debug("Instance created");
    }

    public void setEm(EmSupplier em) {
        this.em = em;
    }

    @Override
    protected EmSupplier getEm() {
        return this.em;
    }

}
