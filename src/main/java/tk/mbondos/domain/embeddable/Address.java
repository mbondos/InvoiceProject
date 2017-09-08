package tk.mbondos.domain.embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embeddable;

@Embeddable
public class Address {


    @JsonProperty(value = "street_address")
    private String streetAddress;

    @JsonProperty(value = "city")
    private String city;

    @JsonProperty(value = "zipCode")
    private String zipCode;

    public Address() {
    }

    public Address(String streetAddress, String city, String zipCode) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zipCode + '\'' +
                '}';
    }
}