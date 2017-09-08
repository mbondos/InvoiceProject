package tk.mbondos.factories;

import org.springframework.stereotype.Component;
import tk.mbondos.domain.Organization;
import tk.mbondos.dtos.OrganizationDto;

@Component
public class OrganizationFactory {
    public OrganizationFactory() {
    }

    public Organization create(OrganizationDto organizationDto) {
        return new Organization(
                organizationDto.getName(),
                organizationDto.getAddress(),
                organizationDto.getNip(),
                organizationDto.getPhone(),
                organizationDto.getEmail(),
                organizationDto.getWebsite(),
                organizationDto.getBankAccountNumber());
    }
}
