package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.dto.ContratDto;

@Component
public class ContratMapper {

    public ContratDto toDto(Contrat c) {
        Long idReservation = c.getReservation() != null ? c.getReservation().getIdReservation() : null;
        return new ContratDto(c.getIdContrat(), c.getDateSignature(), c.getMontantTotal(),
                c.isValide(), idReservation);
    }

    public Contrat toEntity(ContratDto dto) {
        Contrat c = new Contrat();
        c.setIdContrat(dto.idContrat());
        c.setDateSignature(dto.dateSignature());
        c.setMontantTotal(dto.montantTotal());
        c.setValide(dto.valide());
        if (dto.idReservation() != null) {
            Reservation r = new Reservation();
            r.setIdReservation(dto.idReservation());
            c.setReservation(r);
        }
        return c;
    }
}
