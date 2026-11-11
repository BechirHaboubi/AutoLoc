package tn.esprit.autoloc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.EquipementDto;
import tn.esprit.autoloc.mapper.EquipementMapper;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@RestController
@RequestMapping("/equipements")
@RequiredArgsConstructor
public class EquipementController {

    private final IEquipementService equipementService;
    private final EquipementMapper mapper;

    @GetMapping
    public List<EquipementDto> findAll() {
        return equipementService.findAll().stream().map(mapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public EquipementDto findById(@PathVariable Long id) {
        return mapper.toDto(equipementService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquipementDto create(@Valid @RequestBody EquipementDto dto) {
        return mapper.toDto(equipementService.create(mapper.toEntity(dto)));
    }

    @PutMapping("/{id}")
    public EquipementDto update(@PathVariable Long id, @Valid @RequestBody EquipementDto dto) {
        return mapper.toDto(equipementService.update(id, mapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        equipementService.deleteById(id);
    }
}
