package tn.esprit.autoloc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.ContratDto;
import tn.esprit.autoloc.mapper.ContratMapper;
import tn.esprit.autoloc.service.IContratService;

import java.util.List;

@RestController
@RequestMapping("/contrats")
@RequiredArgsConstructor
public class ContratController {

    private final IContratService contratService;
    private final ContratMapper mapper;

    @GetMapping
    public List<ContratDto> findAll() {
        return contratService.findAll().stream().map(mapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ContratDto findById(@PathVariable Long id) {
        return mapper.toDto(contratService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContratDto create(@Valid @RequestBody ContratDto dto) {
        return mapper.toDto(contratService.create(mapper.toEntity(dto)));
    }

    @PutMapping("/{id}")
    public ContratDto update(@PathVariable Long id, @Valid @RequestBody ContratDto dto) {
        return mapper.toDto(contratService.update(id, mapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        contratService.deleteById(id);
    }
}
