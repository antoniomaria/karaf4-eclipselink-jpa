package net.sf.companymanager.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import net.sf.companymanager.domain.support.AbstractPersistable;

@SuppressWarnings("serial")
@Entity
public class Office extends AbstractPersistable {

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @Embedded
    private Address address;

    private String name;

    public Office() {
        super();
    }

    public Office(String name) {
        this.name = name;
    }

    public Office(Builder builder) {
        this(builder.name);
        this.address = builder.address;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Office [getId()=" + getId() + ", getName()=" + getName() + ", address=" + address + "]";
    }

    public static class Builder {
        private String name;

        private Address address;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Office build() {
            return new Office(this);
        }
    }
}
