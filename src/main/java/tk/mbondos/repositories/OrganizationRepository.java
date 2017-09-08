package tk.mbondos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tk.mbondos.domain.Organization;

@RepositoryRestResource(collectionResourceRel = "organization", path = "organizations")
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
