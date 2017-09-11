package tk.mbondos.domain;

import tk.mbondos.domain.embeddable.Address;

import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "streetAddress", column = @Column(name = "street_address", length = 50)),
            @AttributeOverride(name = "zipcode", column = @Column(length = 10)),
            @AttributeOverride(name = "city", column = @Column())
    })
    private Address address;

    @Column(name = "nip")
    private String nip;


    public Customer() {
    }

    public Customer(String name, Address address, String nip) {
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
}
