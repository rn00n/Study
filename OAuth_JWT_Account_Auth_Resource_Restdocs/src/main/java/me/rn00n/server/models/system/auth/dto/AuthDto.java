package me.rn00n.server.models.system.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class AuthDto {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

}
