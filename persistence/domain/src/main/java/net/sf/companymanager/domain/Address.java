package net.sf.companymanager.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String streetAddress1;
    private String streetAddress2;
    private String city;
    private String zip;
    private String state;
    private String country;

    public Address() {
        super();
    }

    public Address(String streetAddress1, String streetAddress2, String city, String zip, String state, String country) {
        super();
        this.streetAddress1 = streetAddress1;
        this.streetAddress2 = streetAddress2;
        this.city = city;
        this.zip = zip;
        this.state = state;
        this.country = country;
    }

    public Address(Builder builder) {
        super();
        this.streetAddress1 = builder.streetAddress1;
        this.streetAddress2 = builder.streetAddress2;
        this.city = builder.city;
        this.state = builder.state;
        this.country = builder.country;
        this.zip = builder.zip;
    }

    public String getStreetAddress1() {
        return streetAddress1;
    }

    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address [streetAddress1=" + streetAddress1 + ", streetAddress2=" + streetAddress2 + ", city=" + city
                + ", zip=" + zip + ", state=" + state + ", country=" + country + "]";
    }

    public static class Builder {
        private String streetAddress1;
        private String streetAddress2;
        private String city;
        private String zip;
        private String state;
        private String country;

        public Builder streetAddress1(String streetAddress1) {
            this.streetAddress1 = streetAddress1;
            return this;
        }

        public Builder streetAddress2(String streetAddress2) {
            this.streetAddress2 = streetAddress2;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder zip(String zip) {
            this.zip = zip;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }

}
