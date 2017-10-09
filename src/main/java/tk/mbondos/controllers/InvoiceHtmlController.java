package tk.mbondos.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tk.mbondos.domain.Customer;
import tk.mbondos.domain.Invoice;
import tk.mbondos.domain.InvoiceLines;
import tk.mbondos.domain.Product;
import tk.mbondos.domain.embeddable.Address;
import tk.mbondos.dtos.CustomerDto;
import tk.mbondos.dtos.InvoiceDto;
import tk.mbondos.dtos.InvoiceLinesDto;
import tk.mbondos.dtos.OrganizationDto;
import tk.mbondos.services.CustomerService;
import tk.mbondos.services.InvoiceService;
import tk.mbondos.utils.IdWrapper;
import tk.mbondos.utils.InvoiceLinesWrapper;
import tk.mbondos.utils.PdfGeneratorUtil;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/invoices")
public class InvoiceHtmlController {
    private InvoiceService invoiceService;
    private PdfGeneratorUtil pdfGeneratorUtil;


    @Autowired
    public InvoiceHtmlController(InvoiceService invoiceService, PdfGeneratorUtil pdfGeneratorUtil) {
        this.invoiceService = invoiceService;
        this.pdfGeneratorUtil = pdfGeneratorUtil;
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
        model.addAttribute("lines", linesWrapper);

        return "invoice";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddInvoiceForm(Model model,
                                        @Valid @ModelAttribute("customer") CustomerDto customer,
                                        BindingResult resultCustomer,
                                        @Valid @ModelAttribute("lines") InvoiceLinesWrapper lines,
                                        BindingResult resultLines,
                                        @Valid @ModelAttribute("invoice") InvoiceDto invoice,
                                        BindingResult resultInvoice
    ) {


        if (resultInvoice.hasErrors() || resultCustomer.hasErrors() || resultLines.hasErrors()) {
            System.out.println("error asd"); //TODO logger

            return "invoice";
        }

        invoice.validate();
        customer.validate();


        invoiceService.createInvoice(invoice, customer, lines.getLinesList());
        return "redirect:add";
    }

    @RequestMapping(value = "pdf", method = RequestMethod.GET, produces = "application/pdf")
    public @ResponseBody
    FileSystemResource generatePdf(HttpServletResponse response) {
        Map<String, Object> data = new HashMap<String, Object>();
        File file = null;
        data.put("invoice", invoiceService.findById(1L));
        try {
            pdfGeneratorUtil.clearDirectory();
            String download = pdfGeneratorUtil.createPdf("pdftemplate", data);
            file = new File(download);
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
            response.setHeader("Content-Length", String.valueOf(file.length()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new FileSystemResource(file);

    }

}
