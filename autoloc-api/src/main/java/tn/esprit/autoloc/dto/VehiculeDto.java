package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;

import java.math.BigDecimal;

public record VehiculeDto(
        Long idVehicule,

        @NotBlank @Size(max = 20)
        String immatriculation,

        @NotBlank @Size(max = 50)
        String marque,

        @NotBlank @Size(max = 50)
        String modele,

        @NotNull
        CategorieVehicule categorie,

        @NotNull @DecimalMin("0.00")
        BigDecimal tarifJournalier,

        @NotNull
        StatutVehicule statut,

        Long idAgence
) {}
