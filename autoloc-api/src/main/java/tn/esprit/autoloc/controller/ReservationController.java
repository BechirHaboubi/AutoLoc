package tn.esprit.autoloc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.ReservationDto;
import tn.esprit.autoloc.mapper.ReservationMapper;
import tn.esprit.autoloc.service.IReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService reservationService;
    private final ReservationMapper mapper;

    @GetMapping
    public List<ReservationDto> findAll() {
        return reservationService.findAll().stream().map(mapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ReservationDto findById(@PathVariable Long id) {
        return mapper.toDto(reservationService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationDto create(@Valid @RequestBody ReservationDto dto) {
        return mapper.toDto(reservationService.create(mapper.toEntity(dto)));
    }

    @PutMapping("/{id}")
    public ReservationDto update(@PathVariable Long id, @Valid @RequestBody ReservationDto dto) {
        return mapper.toDto(reservationService.update(id, mapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        reservationService.deleteById(id);
    }
}
