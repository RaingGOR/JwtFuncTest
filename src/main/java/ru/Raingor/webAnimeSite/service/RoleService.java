package ru.Raingor.webAnimeSite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.Raingor.webAnimeSite.models.Role;
import ru.Raingor.webAnimeSite.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUsersRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
