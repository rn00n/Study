package me.rn00n.server.models.system.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class ResourceDto {

    @NotBlank
    private Integer id;

    @NotBlank
    private String url;

    @NotBlank
    private String method;

    private String description;

}
