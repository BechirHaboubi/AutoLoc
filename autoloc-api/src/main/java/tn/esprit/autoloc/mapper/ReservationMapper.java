package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.dto.ReservationDto;

@Component
public class ReservationMapper {

    public ReservationDto toDto(Reservation r) {
        Long idVehicule = r.getVehicule() != null ? r.getVehicule().getIdVehicule() : null;
        Long idClient   = r.getClient()   != null ? r.getClient().getIdClient()     : null;
        return new ReservationDto(r.getIdReservation(), r.getDateDebut(), r.getDateFin(),
                r.getStatut(), idVehicule, idClient);
    }

    public Reservation toEntity(ReservationDto dto) {
        Reservation r = new Reservation();
        r.setIdReservation(dto.idReservation());
        r.setDateDebut(dto.dateDebut());
        r.setDateFin(dto.dateFin());
        r.setStatut(dto.statut());
        if (dto.idVehicule() != null) {
            Vehicule v = new Vehicule();
            v.setIdVehicule(dto.idVehicule());
            r.setVehicule(v);
        }
        if (dto.idClient() != null) {
            Client c = new Client();
            c.setIdClient(dto.idClient());
            r.setClient(c);
        }
        return r;
    }
}
