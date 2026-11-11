package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.dto.EmployeDto;

@Component
public class EmployeMapper {

    public EmployeDto toDto(Employe e) {
        Long idAgence = e.getAgence() != null ? e.getAgence().getIdAgence() : null;
        return new EmployeDto(e.getIdEmploye(), e.getNom(), e.getPrenom(), e.getRole(), idAgence);
    }

    public Employe toEntity(EmployeDto dto) {
        Employe e = new Employe();
        e.setIdEmploye(dto.idEmploye());
        e.setNom(dto.nom());
        e.setPrenom(dto.prenom());
        e.setRole(dto.role());
        if (dto.idAgence() != null) {
            Agence a = new Agence();
            a.setIdAgence(dto.idAgence());
            e.setAgence(a);
        }
        return e;
    }
}
