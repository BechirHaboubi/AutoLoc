package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.dto.EquipementDto;

@Component
public class EquipementMapper {

    public EquipementDto toDto(Equipement e) {
        return new EquipementDto(e.getIdEquipement(), e.getLibelle());
    }

    public Equipement toEntity(EquipementDto dto) {
        Equipement e = new Equipement();
        e.setIdEquipement(dto.idEquipement());
        e.setLibelle(dto.libelle());
        return e;
    }
}
