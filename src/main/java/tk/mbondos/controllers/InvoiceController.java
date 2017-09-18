package tk.mbondos.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.mbondos.domain.Invoice;
import tk.mbondos.services.InvoiceService;

import java.util.List;

@RestController
@RequestMapping(value = "api/invoices", produces = MediaType.APPLICATION_JSON_VALUE)
public class InvoiceController {
    private InvoiceService invoiceService;


    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

   @RequestMapping(method = RequestMethod.GET)
    public List<Invoice> getInvoices() {
        return invoiceService.listAllInvoices();
    }
}
