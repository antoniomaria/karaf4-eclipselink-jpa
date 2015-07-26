package net.sf.companymanager.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import net.sf.companymanager.domain.support.AbstractPersistable;

@Entity
public class Company extends AbstractPersistable {

    private static final long serialVersionUID = -6101148827058878211L;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = { CascadeType.ALL })
    private Set<Office> offices;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = { CascadeType.ALL })
    private Set<Department> departments;

    private String name;

    public Company() {
        super();
    }

    public Set<Office> getOffices() {
        if (offices == null) {
            offices = new java.util.LinkedHashSet<Office>();
        }
        return offices;
    }

    public void setOffices(Set<Office> offices) {
        this.offices = offices;
    }

    public Set<Department> getDepartments() {
        if (departments == null) {
            this.departments = new LinkedHashSet<Department>();
        }
        return departments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartments(Set<Department> departaments) {
        this.departments = departaments;
    }

    public void addOffice(Office office) {
        getOffices().add(office);
        office.setCompany(this);
    }

    public void addDepartment(Department department) {
        getDepartments().add(department);
        department.setCompany(this);
    }
}
