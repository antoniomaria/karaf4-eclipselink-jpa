package net.sf.companymanager.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import net.sf.companymanager.domain.support.AbstractPersistable;

@SuppressWarnings("serial")
@Entity
public class Department extends AbstractPersistable {

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany
    @JoinTable(name = "dept_emp", joinColumns = { @JoinColumn(name = "dept_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "emp_id", referencedColumnName = "id") })
    private Set<Employee> employees;

    public Department() {
        super();
    }

    public Department(String name) {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        if (employees == null) {
            this.employees = new LinkedHashSet<Employee>();
        }
        return employees;
    }

    public void setEmployees(Set<Employee> workers) {
        this.employees = workers;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void addEmployee(Employee employee) {
        getEmployees().add(employee);
        employee.getDepartments().add(this);
    }

}
