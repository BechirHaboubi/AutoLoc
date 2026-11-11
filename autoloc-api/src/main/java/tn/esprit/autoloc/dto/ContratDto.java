package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContratDto(
        Long idContrat,

        @NotNull
        LocalDate dateSignature,

        @NotNull @DecimalMin("0.00")
        BigDecimal montantTotal,

        boolean valide,

        Long idReservation
) {}
