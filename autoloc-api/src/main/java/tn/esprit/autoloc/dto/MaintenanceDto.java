package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record MaintenanceDto(
        Long idMaintenance,

        @NotNull
        LocalDate dateDebut,

        LocalDate dateFin,

        @NotBlank @Size(max = 500)
        String description,

        Long idVehicule
) {}
