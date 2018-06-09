package tk.mbondos.unit.dto;

import org.junit.Test;
import org.modelmapper.ModelMapper;
import tk.mbondos.domain.*;
import tk.mbondos.domain.embeddable.Address;
import tk.mbondos.dtos.*;
import tk.mbondos.enums.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InvoiceDtoTest {
    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertInvoiceDtoToInvoiceEntity_thenCorrect() {
        //1
        InvoiceDto invoiceDto = new InvoiceDto("01/01/2018", LocalDate.now(), LocalDate.now(),
                new CustomerDto("John Smith",
                        new Address("Street", "City", "zip"),
                        "nip" ),
                new OrganizationDto("Organization",
                        new Address("Street", "City", "zip"),
                        "723-103-99-203", "phone", "mail", "website", "bank nubmer"),
                PaymentType.TRANSFER,
                null);

        Invoice invoice = modelMapper.map(invoiceDto, Invoice.class);
        assertEquals(invoiceDto.getInvoiceNumber(), invoice.getInvoiceNumber());
        assertEquals(invoiceDto.getServiceDate(), invoice.getServiceDate());
        assertEquals(invoiceDto.getIssueDate(), invoice.getIssueDate());
        assertEquals(invoiceDto.getPaymentType(), invoice.getPaymentType());
        assertEquals(invoiceDto.getCustomer().getNip(), invoice.getCustomer().getNip());
        assertEquals(invoiceDto.getOrganization().getNip(), invoice.getOrganization().getNip());


        //2
        List<InvoiceLinesDto> invoiceLines = new ArrayList<>();
        invoiceLines.add(new InvoiceLinesDto(1, new ProductDto("Product", BigDecimal.TEN, BigDecimal.TEN, 23), BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE));
        invoiceLines.add(new InvoiceLinesDto(2, new ProductDto("Product1", BigDecimal.TEN, BigDecimal.TEN, 23), BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE));

        InvoiceDto invoiceDto1 = new InvoiceDto("02/01/2018", LocalDate.now().plusDays(1), LocalDate.now().plusDays(1),
                new CustomerDto("John Smith",
                        new Address("Street", "City", "zip"),
                        "nip" ),
                new OrganizationDto("Organization",
                        new Address("Street", "City", "zip"),
                        "723-103-99-203", "phone", "mail", "website", "bank nubmer"),
                PaymentType.TRANSFER,
                 invoiceLines);

        Invoice invoice1 = modelMapper.map(invoiceDto1, Invoice.class);

        assertEquals(invoiceDto1.getInvoiceNumber(), invoice1.getInvoiceNumber());
        assertEquals(invoiceDto1.getServiceDate(), invoice1.getServiceDate());
        assertEquals(invoiceDto1.getIssueDate(), invoice1.getIssueDate());
        assertEquals(invoiceDto1.getPaymentType(), invoice1.getPaymentType());
        assertEquals(invoiceDto1.getCustomer().getNip(), invoice1.getCustomer().getNip());
        assertEquals(invoiceDto1.getOrganization().getNip(), invoice1.getOrganization().getNip());
        assertEquals(invoiceDto1.getInvoiceLines().get(0).getQuantity(), invoice1.getInvoiceLines().get(0).getQuantity());
        assertEquals(invoiceDto1.getInvoiceLines().get(0).getProduct().getName(), invoice1.getInvoiceLines().get(0).getProduct().getName());

    }

}
