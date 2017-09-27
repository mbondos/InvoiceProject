package tk.mbondos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.mbondos.domain.Organization;
import tk.mbondos.dtos.OrganizationDto;
import tk.mbondos.services.OrganizationService;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {
    private OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addOrganization(OrganizationDto organizationDto) {
        organizationDto.validate();
        organizationService.createOrganization(organizationDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Organization> getOrganizations() {
        return organizationService.listAllOrganizations();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{customerId}")
    public Organization getOrganization(@PathVariable Long customerId) {
        return organizationService.findById(customerId);
    }
}
