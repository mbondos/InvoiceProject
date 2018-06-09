package tk.mbondos.controllers;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public InvoiceHtmlController(InvoiceService invoiceService, PdfGeneratorUtil pdfGeneratorUtil) {
        this.invoiceService = invoiceService;
        this.pdfGeneratorUtil = pdfGeneratorUtil;
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getInvoices(Model model) {
        List<Invoice> invoices = invoiceService.listAllInvoices();
        model.addAttribute("invoices", invoices);
        model.addAttribute("title", "My Invoices");
        return "invoice/show";
    }


    //DELETE invoice
    @RequestMapping(value = "{invoiceId}/delete", method = RequestMethod.GET)
    public String deleteInvoice(@PathVariable Long invoiceId) {
        invoiceService.deleteInvoice(invoiceId);

        return "redirect:/invoices";
    }

    //GET add page
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddInvoiceForm(Model model) {
        model.addAttribute("invoice", new InvoiceDto());
        model.addAttribute("nextInvoiceNumber", invoiceService.getNextInvoiceNumber());
        model.addAttribute("customer", new CustomerDto());
        InvoiceLinesWrapper linesWrapper = new InvoiceLinesWrapper();
        model.addAttribute("lines", linesWrapper);

        return "invoice/add";
    }

    //POST add page
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddInvoiceForm(@Valid @ModelAttribute("customer") CustomerDto customer,
                                        BindingResult resultCustomer,
                                        @Valid @ModelAttribute("lines") InvoiceLinesWrapper lines,
                                        BindingResult resultLines,
                                        @Valid @ModelAttribute("invoice") InvoiceDto invoice,
                                        BindingResult resultInvoice
    ) {


        if (resultInvoice.hasErrors() || resultCustomer.hasErrors() || resultLines.hasErrors()) {
            log.info("Invoice add form has errors");

            return "invoice/add";
        }


        customer.validate();


        invoiceService.createInvoice(invoice, customer, lines.getLinesList());
        return "redirect:/invoices";
    }

    //GET edit page
    @RequestMapping(value = "{invoiceId}/edit", method = RequestMethod.GET)
    public String displayEditInvoiceForm(Model model, @PathVariable Long invoiceId) {
        InvoiceDto invoice = modelMapper.map(invoiceService.findById(invoiceId), InvoiceDto.class);
        model.addAttribute("invoice", invoice);
        InvoiceLinesWrapper linesWrapper = new InvoiceLinesWrapper(invoice.getInvoiceLines());
        model.addAttribute("lines", linesWrapper);

        return "invoice/edit";
    }

    //POST edit page
    @RequestMapping(value = "{invoiceId}/edit", method = RequestMethod.POST)
    public String processEditInvoiceForm(@PathVariable Long invoiceId,
                                         @Valid @ModelAttribute("lines") InvoiceLinesWrapper lines,
                                         BindingResult resultLines,
                                         @Valid @ModelAttribute("invoice") InvoiceDto invoice,
                                         BindingResult resultInvoice
    ) {


        if (resultInvoice.hasErrors() || resultLines.hasErrors()) {
            log.info("Invoice edit form has errors");

            return "invoice/{invoiceId}/edit";
        }

        invoiceService.updateInvoice(invoice, lines.getLinesList());
        return "redirect:/invoices";
    }

    @RequestMapping(value = "{invoiceId}/pdf", method = RequestMethod.GET, produces = "application/pdf")
    public @ResponseBody
    FileSystemResource generatePdf(@PathVariable Long invoiceId, HttpServletResponse response) {
        Map<String, Object> data = new HashMap<String, Object>();
        File file = null;
        data.put("invoice", invoiceService.findById(invoiceId));
        try {
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
