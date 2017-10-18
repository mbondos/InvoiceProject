package tk.mbondos.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mbondos.domain.Organization;
import tk.mbondos.dtos.OrganizationDto;
import tk.mbondos.repositories.OrganizationRepository;

import java.util.List;

@Service
public class OrganizationService {
    private OrganizationRepository organizationRepository;
    private ModelMapper modelMapper;

    public OrganizationService(OrganizationRepository organizationRepository, ModelMapper modelMapper) {
        this.organizationRepository = organizationRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void createOrganization(OrganizationDto organizationDto) {
        Organization organization = modelMapper.map(organizationDto, Organization.class);
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
