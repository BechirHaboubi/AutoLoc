package tn.esprit.autoloc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.PaiementDto;
import tn.esprit.autoloc.mapper.PaiementMapper;
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;

@RestController
@RequestMapping("/paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final IPaiementService paiementService;
    private final PaiementMapper mapper;

    @GetMapping
    public List<PaiementDto> findAll() {
        return paiementService.findAll().stream().map(mapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public PaiementDto findById(@PathVariable Long id) {
        return mapper.toDto(paiementService.findById(id));
    }
}
