package tk.mbondos.dtos;
import tk.mbondos.annotation.ExistingOrNewCustomer;
import tk.mbondos.domain.embeddable.Address;

@ExistingOrNewCustomer
public class CustomerDto {

    private Long id;
    private String name;
    private Address address;
    private String nip;



    public CustomerDto() {
    }

    public CustomerDto(Long id, String name, Address address, String nip) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.nip = nip;
    }

    public CustomerDto(String name, Address address, String nip) {
        this.name = name;
        this.address = address;
        this.nip = nip;
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

    public boolean validate() {
        return true;
    }
}
