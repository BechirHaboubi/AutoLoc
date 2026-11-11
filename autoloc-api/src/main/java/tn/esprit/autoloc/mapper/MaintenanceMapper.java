package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.dto.MaintenanceDto;

@Component
public class MaintenanceMapper {

    public MaintenanceDto toDto(Maintenance m) {
        Long idVehicule = m.getVehicule() != null ? m.getVehicule().getIdVehicule() : null;
        return new MaintenanceDto(m.getIdMaintenance(), m.getDateDebut(), m.getDateFin(),
                m.getDescription(), idVehicule);
    }

    public Maintenance toEntity(MaintenanceDto dto) {
        Maintenance m = new Maintenance();
        m.setIdMaintenance(dto.idMaintenance());
        m.setDateDebut(dto.dateDebut());
        m.setDateFin(dto.dateFin());
        m.setDescription(dto.description());
        if (dto.idVehicule() != null) {
            Vehicule v = new Vehicule();
            v.setIdVehicule(dto.idVehicule());
            m.setVehicule(v);
        }
        return m;
    }
}
