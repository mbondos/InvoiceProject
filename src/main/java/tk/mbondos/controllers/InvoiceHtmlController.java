package tk.mbondos.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tk.mbondos.domain.Invoice;
import tk.mbondos.dtos.InvoiceDto;
import tk.mbondos.services.InvoiceService;

import java.util.List;

@Controller
@RequestMapping("/invoices")
public class InvoiceHtmlController {
    private InvoiceService invoiceService;

    @Autowired
    public InvoiceHtmlController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getInvoices(Model model) {
        List<Invoice> invoices = invoiceService.listAllInvoices();
        model.addAttribute("invoices", invoices);
        model.addAttribute("title", "My Invoices");
        return "invoices/show";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddInvoiceForm(Model model) {
        model.addAttribute("title", "Add Invoice");
        model.addAttribute("invoiceDto", new InvoiceDto());
        return "invoices/add";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddInvoiceForm(@ModelAttribute InvoiceDto invoiceDto) {
        invoiceDto.validate();
        invoiceService.createInvoice(invoiceDto);
        return "redirect:";
    }

}
