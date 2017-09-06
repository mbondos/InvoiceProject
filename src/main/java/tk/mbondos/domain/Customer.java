package tk.mbondos.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String nip;

    public Customer() {
    }

    public Customer(String name, String nip) {
        this.name = name;
        this.nip = nip;
    }
}
