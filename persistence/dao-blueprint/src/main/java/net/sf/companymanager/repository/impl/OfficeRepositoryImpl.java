package net.sf.companymanager.repository.impl;

import javax.persistence.PersistenceContext;

import org.apache.aries.jpa.supplier.EmSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.companymanager.domain.Office;
import net.sf.companymanager.repository.OfficeRepository;
import net.sf.companymanager.repository.support.AbstractJPARepository;

public class OfficeRepositoryImpl extends AbstractJPARepository<Office>implements OfficeRepository {

    /**
     * Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(OfficeRepositoryImpl.class);

    @PersistenceContext(unitName = "companymanager")
    protected EmSupplier em;

    public OfficeRepositoryImpl() {
        super(Office.class);
        logger.debug("OfficeRepositoryImpl instance created");
    }

    public void setEm(EmSupplier em) {
        this.em = em;
    }

    @Override
    protected EmSupplier getEm() {
        return this.em;
    }
}
