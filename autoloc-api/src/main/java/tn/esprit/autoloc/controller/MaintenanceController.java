package tn.esprit.autoloc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.MaintenanceDto;
import tn.esprit.autoloc.mapper.MaintenanceMapper;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;
    private final MaintenanceMapper mapper;

    @GetMapping
    public List<MaintenanceDto> findAll() {
        return maintenanceService.findAll().stream().map(mapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public MaintenanceDto findById(@PathVariable Long id) {
        return mapper.toDto(maintenanceService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaintenanceDto create(@Valid @RequestBody MaintenanceDto dto) {
        return mapper.toDto(maintenanceService.create(mapper.toEntity(dto)));
    }

    @PutMapping("/{id}")
    public MaintenanceDto update(@PathVariable Long id, @Valid @RequestBody MaintenanceDto dto) {
        return mapper.toDto(maintenanceService.update(id, mapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        maintenanceService.deleteById(id);
    }
}
