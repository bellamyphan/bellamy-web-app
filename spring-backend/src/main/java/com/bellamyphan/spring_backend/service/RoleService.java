package com.bellamyphan.spring_backend.service;

import com.bellamyphan.spring_backend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
}
