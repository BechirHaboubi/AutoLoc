package tn.esprit.autoloc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.EmployeDto;
import tn.esprit.autoloc.mapper.EmployeMapper;
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@RestController
@RequestMapping("/employes")
@RequiredArgsConstructor
public class EmployeController {

    private final IEmployeService employeService;
    private final EmployeMapper mapper;

    @GetMapping
    public List<EmployeDto> findAll() {
        return employeService.findAll().stream().map(mapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public EmployeDto findById(@PathVariable Long id) {
        return mapper.toDto(employeService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeDto create(@Valid @RequestBody EmployeDto dto) {
        return mapper.toDto(employeService.create(mapper.toEntity(dto)));
    }

    @PutMapping("/{id}")
    public EmployeDto update(@PathVariable Long id, @Valid @RequestBody EmployeDto dto) {
        return mapper.toDto(employeService.update(id, mapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        employeService.deleteById(id);
    }
}
