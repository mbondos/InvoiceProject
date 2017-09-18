package tk.mbondos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import tk.mbondos.domain.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
