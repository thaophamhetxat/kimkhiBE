package cuahang.kimkhi_be.service.impl;

import cuahang.kimkhi_be.model.Role;
import cuahang.kimkhi_be.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);
}
