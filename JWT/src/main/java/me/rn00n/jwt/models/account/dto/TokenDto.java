package me.rn00n.jwt.models.account.dto;

import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class TokenDto {
    private String token;
}
