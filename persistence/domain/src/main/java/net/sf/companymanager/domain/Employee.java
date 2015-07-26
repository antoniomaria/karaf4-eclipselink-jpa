package net.sf.companymanager.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.sf.companymanager.domain.support.AbstractPersistable;

@SuppressWarnings("serial")
@Entity
public class Employee extends AbstractPersistable {

    private String socialSecurityNumber;

    private String givenNames;

    private String familyName;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date firstDateOfContract;

    @ManyToMany(mappedBy = "employees")
    private Set<Department> departments;

    private String occupation;

    public Employee() {
        super();
    }

    public Employee(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee(Builder builder) {
        this(builder.givenNames + " " + builder.familyName);
        socialSecurityNumber = builder.socialSecurityNumber;
        givenNames = builder.givenNames;
        familyName = builder.familyName;
        firstDateOfContract = builder.firstDateOfContract;
        occupation = builder.occupation;
    }

    public Set<Department> getDepartments() {
        if (this.departments == null) {
            this.departments = new LinkedHashSet<Department>();
        }
        return departments;
    }

    public void setDepartments(Set<Department> deparments) {
        this.departments = deparments;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getGivenNames() {
        return givenNames;
    }

    public void setGivenNames(String givenNames) {
        this.givenNames = givenNames;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Date getFirstDateOfContract() {
        return firstDateOfContract;
    }

    public void setFirstDateOfContract(Date firstDateOfContract) {
        this.firstDateOfContract = firstDateOfContract;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public static Builder with() {
        return new Builder();
    }

    public static class Builder {

        private String socialSecurityNumber;

        private String givenNames;

        private String familyName;

        private Date firstDateOfContract;

        private String occupation;

        public Builder socialSecurityNumber(String socialSecurityNumber) {
            this.socialSecurityNumber = socialSecurityNumber;
            return this;
        }

        public Builder givenNames(String givenNames) {
            this.givenNames = givenNames;
            return this;
        }

        public Builder familyName(String familyName) {
            this.familyName = familyName;
            return this;
        }

        public Builder firstDateOfContract(String firstDateOfContract) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                this.firstDateOfContract = sdf.parse(firstDateOfContract);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid data format: yyyy-MM-ddd", e);
            }
            return this;
        }

        public Builder occupation(String occupation) {
            this.occupation = occupation;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }

    @Override
    public String toString() {
        return String.format("Employee [socialSecurityNumber=%s, occupation=%s, getName()=%s]", socialSecurityNumber,
                occupation, getName());
    }

}
