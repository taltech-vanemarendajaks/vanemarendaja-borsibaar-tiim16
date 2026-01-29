package com.borsibaar.service;

import com.borsibaar.entity.Role;
import com.borsibaar.entity.User;
import com.borsibaar.repository.RoleRepository;
import com.borsibaar.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AccountService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public Role findRoleByName(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                String.format("Role '%s' not found", roleName)
                        )
                );
    }

    public List<User> findUsersByOrganizationAndRole(Long organizationId, Role adminRole) {
        return userRepository.findByOrganizationIdAndRole(organizationId,adminRole);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
