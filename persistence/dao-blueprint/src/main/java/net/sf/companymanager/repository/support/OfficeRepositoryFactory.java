package net.sf.companymanager.repository.support;

import javax.persistence.PersistenceContext;

import org.apache.aries.jpa.supplier.EmSupplier;

import net.sf.companymanager.domain.Employee;
import net.sf.companymanager.domain.support.Persistable;
import net.sf.companymanager.repository.EmployeeRepository;
import net.sf.companymanager.repository.impl.EmployeeRepositoryImpl;

interface GenericRepositoryFactory<R extends JPARepository<S>, S extends Persistable> {
    R getObject();
}

public class OfficeRepositoryFactory implements GenericRepositoryFactory<EmployeeRepository, Employee> {

    @PersistenceContext(unitName = "companymanager")
    protected EmSupplier em;

    @Override
    public EmployeeRepository getObject() {
        EmployeeRepositoryImpl repository = new EmployeeRepositoryImpl();
        if (em == null) {
            System.out.println("chungo null");
        } else {
            System.out.println("de momento bien!");
        }
        repository.setEm(em);
        return repository;
    }

}
