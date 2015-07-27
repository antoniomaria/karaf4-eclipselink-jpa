package net.sf.companymanager.repository.support;

import javax.persistence.PersistenceContext;

import net.sf.companymanager.domain.Office;
import net.sf.companymanager.domain.support.Persistable;
import net.sf.companymanager.repository.OfficeRepository;
import net.sf.companymanager.repository.impl.OfficeRepositoryImpl;

import org.apache.aries.jpa.supplier.EmSupplier;

interface GenericRepositoryFactory<R extends JPARepository<S>, S extends Persistable> {
    R getObject();
}

public class OfficeRepositoryFactory implements GenericRepositoryFactory<OfficeRepository, Office> {

    @PersistenceContext(unitName = "companymanager")
    protected EmSupplier em;

    @Override
    public OfficeRepository getObject() {
        OfficeRepositoryImpl repository = new OfficeRepositoryImpl();
        if (em == null) {
            System.out.println("chungo null");
        } else {
            System.out.println("de momento bien!");
        }
        repository.setEm(em);
        return repository;
    }

}
