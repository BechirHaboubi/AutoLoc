package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.dto.PaiementDto;

@Component
public class PaiementMapper {

    public PaiementDto toDto(Paiement p) {
        Long idContrat = p.getContrat() != null ? p.getContrat().getIdContrat() : null;
        return new PaiementDto(p.getIdPaiement(), p.getMontant(), p.getDatePaiement(),
                p.getModePaiement(), idContrat);
    }
}
