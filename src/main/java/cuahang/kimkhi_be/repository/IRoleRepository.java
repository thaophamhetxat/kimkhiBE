package cuahang.kimkhi_be.repository;

import cuahang.kimkhi_be.model.Role;
import cuahang.kimkhi_be.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}