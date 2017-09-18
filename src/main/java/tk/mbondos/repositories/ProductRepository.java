package tk.mbondos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.mbondos.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
}
