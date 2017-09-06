package tk.mbondos.dtos;

public class CustomerDto {

    private String name;
    private String nip;

    public CustomerDto() {
    }

    public CustomerDto(String name, String nip) {
        this.name = name;
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

    public void validate() {

    }
}
