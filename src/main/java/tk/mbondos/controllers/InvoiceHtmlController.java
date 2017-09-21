package tk.mbondos.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tk.mbondos.domain.Customer;
import tk.mbondos.domain.Invoice;
import tk.mbondos.domain.InvoiceLines;
import tk.mbondos.domain.Product;
import tk.mbondos.dtos.CustomerDto;
import tk.mbondos.dtos.InvoiceDto;
import tk.mbondos.dtos.InvoiceLinesDto;
import tk.mbondos.services.CustomerService;
import tk.mbondos.services.InvoiceService;
import tk.mbondos.utils.IdWrapper;
import tk.mbondos.utils.InvoiceLinesWrapper;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/invoices")
public class InvoiceHtmlController {
    InvoiceService invoiceService;
    CustomerService customerService;

    @Autowired
    public InvoiceHtmlController(InvoiceService invoiceService, CustomerService customerService) {
        this.invoiceService = invoiceService;
        this.customerService = customerService;
    }




/*
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getInvoices(Model model) {
        List<Invoice> invoices = invoiceService.listAllInvoices();
        model.addAttribute("invoices", invoices);
        model.addAttribute("title", "My Invoices");
        return "invoices/show";
    }
*/

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddInvoiceForm(Model model) {
        model.addAttribute("title", "Add Invoice");
        model.addAttribute("invoice", new InvoiceDto());
        model.addAttribute("customer", new CustomerDto());
        InvoiceLinesWrapper linesWrapper = new InvoiceLinesWrapper();
        //linesWrapper.getLinesList().add(0, new InvoiceLines(1337,new Product()));
        model.addAttribute("lines", linesWrapper);
        return "invoice";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddInvoiceForm(@ModelAttribute CustomerDto customer, @ModelAttribute InvoiceDto invoice, @ModelAttribute InvoiceLinesWrapper lines) {
        invoice.validate();
        customer.validate();
        System.out.println(lines.getLinesList().get(0).getProduct().getName());

        invoiceService.createInvoice(invoice, customer, lines.getLinesList());

        return "redirect:add";
    }

}
