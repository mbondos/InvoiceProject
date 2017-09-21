package tk.mbondos.utils;

import org.springframework.stereotype.Component;

@Component
public class IdWrapper {
    private Long id;

    public IdWrapper() {
    }

    public IdWrapper(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
