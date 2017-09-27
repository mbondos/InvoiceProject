package tk.mbondos.dtos;

import tk.mbondos.domain.embeddable.Address;

public class OrganizationDto {

    private Long id;

    private String name;

    private Address address;

    private String nip;

    private String phone;

    private String email;

    private String website;

    private String bankAccountNumber;

    public OrganizationDto() {
    }

    public OrganizationDto(String name, Address address, String nip, String phone, String email, String website, String bankAccountNumber) {
        this.name = name;
        this.address = address;
        this.nip = nip;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.bankAccountNumber = bankAccountNumber;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public void validate() {
        //TODO validate organization dto
    }
}
