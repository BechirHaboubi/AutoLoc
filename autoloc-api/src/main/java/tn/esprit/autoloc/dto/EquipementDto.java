package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EquipementDto(
        Long idEquipement,

        @NotBlank @Size(max = 100)
        String libelle
) {}
