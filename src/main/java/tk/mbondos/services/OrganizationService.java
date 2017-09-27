package tk.mbondos.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mbondos.domain.Organization;
import tk.mbondos.dtos.OrganizationDto;
import tk.mbondos.factories.OrganizationFactory;
import tk.mbondos.repositories.OrganizationRepository;

import java.util.List;

@Service
public class OrganizationService {
    private OrganizationRepository organizationRepository;
    private OrganizationFactory organizationFactory;


    public OrganizationService(OrganizationRepository organizationRepository, OrganizationFactory organizationFactory) {
        this.organizationRepository = organizationRepository;
        this.organizationFactory = organizationFactory;
    }

    @Transactional
    public void createOrganization(OrganizationDto organizationDto) {
        Organization organization = organizationFactory.create(organizationDto);
        organizationRepository.save(organization);
    }

    @Transactional
    public Organization findById(Long id) {
        return organizationRepository.findOne(id);
    }

    @Transactional
    public List<Organization> listAllOrganizations() {
        return organizationRepository.findAll();
    }
}
