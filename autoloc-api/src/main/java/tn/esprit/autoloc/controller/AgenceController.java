package tn.esprit.autoloc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.AgenceDto;
import tn.esprit.autoloc.mapper.AgenceMapper;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/agences")
@RequiredArgsConstructor
public class AgenceController {

    private final IAgenceService agenceService;
    private final AgenceMapper mapper;

    @GetMapping
    public List<AgenceDto> findAll() {
        return agenceService.findAll().stream().map(mapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public AgenceDto findById(@PathVariable Long id) {
        return mapper.toDto(agenceService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgenceDto create(@Valid @RequestBody AgenceDto dto) {
        return mapper.toDto(agenceService.create(mapper.toEntity(dto)));
    }

    @PutMapping("/{id}")
    public AgenceDto update(@PathVariable Long id, @Valid @RequestBody AgenceDto dto) {
        return mapper.toDto(agenceService.update(id, mapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        agenceService.deleteById(id);
    }
}
