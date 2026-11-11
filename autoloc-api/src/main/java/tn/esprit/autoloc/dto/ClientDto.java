package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ClientDto(
        Long idClient,

        @NotBlank @Size(max = 100)
        String nom,

        @NotBlank @Size(max = 100)
        String prenom,

        @NotBlank @Email @Size(max = 150)
        String email,

        @NotBlank @Size(max = 20)
        String telephone,

        @NotBlank @Size(max = 30)
        String numPermis,

        @NotNull
        LocalDate dateInscription
) {}
