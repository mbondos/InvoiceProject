package tk.mbondos.utils;

import org.springframework.stereotype.Component;

@Component
public class IdWrapper {
    private String id;

    public IdWrapper() {
    }

    public IdWrapper(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
