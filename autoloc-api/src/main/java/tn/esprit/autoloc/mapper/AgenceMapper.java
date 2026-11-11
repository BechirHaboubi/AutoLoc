package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.dto.AgenceDto;

@Component
public class AgenceMapper {

    public AgenceDto toDto(Agence a) {
        return new AgenceDto(a.getIdAgence(), a.getNom(), a.getVille(), a.getAdresse(), a.getTelephone());
    }

    public Agence toEntity(AgenceDto dto) {
        Agence a = new Agence();
        a.setIdAgence(dto.idAgence());
        a.setNom(dto.nom());
        a.setVille(dto.ville());
        a.setAdresse(dto.adresse());
        a.setTelephone(dto.telephone());
        return a;
    }
}
