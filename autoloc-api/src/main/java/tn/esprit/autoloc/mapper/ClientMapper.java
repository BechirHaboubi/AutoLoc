package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.dto.ClientDto;

@Component
public class ClientMapper {

    public ClientDto toDto(Client c) {
        return new ClientDto(c.getIdClient(), c.getNom(), c.getPrenom(), c.getEmail(),
                c.getTelephone(), c.getNumPermis(), c.getDateInscription());
    }

    public Client toEntity(ClientDto dto) {
        Client c = new Client();
        c.setIdClient(dto.idClient());
        c.setNom(dto.nom());
        c.setPrenom(dto.prenom());
        c.setEmail(dto.email());
        c.setTelephone(dto.telephone());
        c.setNumPermis(dto.numPermis());
        c.setDateInscription(dto.dateInscription());
        return c;
    }
}
