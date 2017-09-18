package tk.mbondos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tk.mbondos.domain.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    List<Customer> findByNameIgnoreCaseContaining(@Param("name") String name);

}
