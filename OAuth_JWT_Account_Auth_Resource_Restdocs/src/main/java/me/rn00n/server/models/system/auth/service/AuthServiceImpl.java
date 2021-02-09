package me.rn00n.server.models.system.auth.service;

import me.rn00n.server.models.base.BaseService;
import me.rn00n.server.models.system.auth.domain.Auth;
import me.rn00n.server.models.system.auth.dto.AuthDto;
import me.rn00n.server.models.system.auth.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl extends BaseService<Auth, String> implements AuthService {

    @Autowired
    AuthRepository repository;

    @Override
    public Auth saveAuth(AuthDto authDto) {
        Auth auth = modelMapper.map(authDto, Auth.class);
        return repository.save(auth);
    }
}
