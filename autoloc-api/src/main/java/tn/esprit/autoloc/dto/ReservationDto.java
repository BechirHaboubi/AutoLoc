package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotNull;
import tn.esprit.autoloc.domain.StatutReservation;

import java.time.LocalDate;

public record ReservationDto(
        Long idReservation,

        @NotNull
        LocalDate dateDebut,

        @NotNull
        LocalDate dateFin,

        @NotNull
        StatutReservation statut,

        Long idVehicule,
        Long idClient
) {}
