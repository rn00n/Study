package me.rn00n.server.models.system.auth.service;

import me.rn00n.server.models.system.auth.domain.Auth;
import me.rn00n.server.models.system.auth.dto.AuthDto;

public interface AuthService {
    Auth saveAuth(AuthDto authDto);
}
