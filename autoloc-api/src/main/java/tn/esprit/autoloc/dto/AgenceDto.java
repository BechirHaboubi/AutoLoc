package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AgenceDto(
        Long idAgence,

        @NotBlank @Size(max = 100)
        String nom,

        @NotBlank @Size(max = 100)
        String ville,

        @NotBlank @Size(max = 255)
        String adresse,

        @NotBlank @Size(max = 20)
        String telephone
) {}
