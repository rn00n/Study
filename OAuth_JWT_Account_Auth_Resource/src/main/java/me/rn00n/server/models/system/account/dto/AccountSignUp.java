package me.rn00n.server.models.system.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class AccountSignUp {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String phoneNo;

    @NotBlank
    private String email;

    private String countryNo;

    private String recommender;

}
