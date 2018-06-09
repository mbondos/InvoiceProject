package tk.mbondos.unit.dto;

import org.junit.Test;
import org.modelmapper.ModelMapper;
import tk.mbondos.domain.Customer;
import tk.mbondos.domain.embeddable.Address;
import tk.mbondos.dtos.CustomerDto;

import static org.junit.Assert.assertEquals;

public class CustomerDtoTest {
    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertCustomerDtoToCustomerEntity_thenCorrect() {
        CustomerDto customerDto = new CustomerDto("CustomerName",
                new Address("Street", "City", "20-258"),
                "nip");


        Customer customer = modelMapper.map(customerDto, Customer.class);
        assertEquals(customerDto.getName(), customer.getName());
        assertEquals(customerDto.getAddress().getCity(), customer.getAddress().getCity());
        assertEquals(customerDto.getAddress().getStreetAddress(), customer.getAddress().getStreetAddress());
        assertEquals(customerDto.getAddress().getZipCode(), customer.getAddress().getZipCode());
        assertEquals(customerDto.getNip(), customer.getNip());

    }
}
