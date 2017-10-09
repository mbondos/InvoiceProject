package tk.mbondos.utils;

import org.springframework.stereotype.Component;
import tk.mbondos.domain.InvoiceLines;
import tk.mbondos.dtos.InvoiceLinesDto;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;



public class InvoiceLinesWrapper {
    @Valid
    private List<InvoiceLinesDto> linesList = new ArrayList<>(100);

    public InvoiceLinesWrapper() {
    }

    public InvoiceLinesWrapper(List<InvoiceLinesDto> linesList) {
        this.linesList = linesList;
    }

    public List<InvoiceLinesDto> getLinesList() {
        return linesList;
    }

    public void setLinesList(List<InvoiceLinesDto> linesList) {
        this.linesList = linesList;
    }
}
