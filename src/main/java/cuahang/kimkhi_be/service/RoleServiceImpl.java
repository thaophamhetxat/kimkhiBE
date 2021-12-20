package cuahang.kimkhi_be.service;

import cuahang.kimkhi_be.model.Role;
import cuahang.kimkhi_be.model.RoleName;
import cuahang.kimkhi_be.repository.IRoleRepository;
import cuahang.kimkhi_be.service.impl.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
