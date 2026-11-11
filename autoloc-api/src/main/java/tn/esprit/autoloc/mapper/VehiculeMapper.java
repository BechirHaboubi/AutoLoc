package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.dto.VehiculeDto;

@Component
public class VehiculeMapper {

    public VehiculeDto toDto(Vehicule v) {
        Long idAgence = v.getAgence() != null ? v.getAgence().getIdAgence() : null;
        return new VehiculeDto(v.getIdVehicule(), v.getImmatriculation(), v.getMarque(),
                v.getModele(), v.getCategorie(), v.getTarifJournalier(), v.getStatut(), idAgence);
    }

    public Vehicule toEntity(VehiculeDto dto) {
        Vehicule v = new Vehicule();
        v.setIdVehicule(dto.idVehicule());
        v.setImmatriculation(dto.immatriculation());
        v.setMarque(dto.marque());
        v.setModele(dto.modele());
        v.setCategorie(dto.categorie());
        v.setTarifJournalier(dto.tarifJournalier());
        v.setStatut(dto.statut());
        if (dto.idAgence() != null) {
            Agence a = new Agence();
            a.setIdAgence(dto.idAgence());
            v.setAgence(a);
        }
        return v;
    }
}
