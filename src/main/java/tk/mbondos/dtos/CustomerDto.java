package tk.mbondos.dtos;

import tk.mbondos.domain.embeddable.Address;

public class CustomerDto {

    private String name;
    private Address address;
    private String nip;

    public CustomerDto() {
    }

    public CustomerDto(String name, Address address, String nip) {
        this.name = name;
        this.address = address;
        this.nip = nip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void validate() {

    }
}
