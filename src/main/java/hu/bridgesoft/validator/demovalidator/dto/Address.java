package hu.bridgesoft.validator.demovalidator.dto;

import java.util.Objects;

public class Address {
    int zipCode;
    String county;
    String country;
    String settlement;
    String street;

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    @Override
    public String toString() {
        return "Address{" +
                "zipCode=" + zipCode +
                ", county='" + county + '\'' +
                ", country='" + country + '\'' +
                ", settlement='" + settlement + '\'' +
                ", street='" + street + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return zipCode == address.zipCode &&
                Objects.equals(county, address.county) &&
                Objects.equals(country, address.country) &&
                Objects.equals(settlement, address.settlement) &&
                Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, county, country, settlement, street);
    }
}
