/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utp.integrador.software2.controller;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utp.integrador.software2.model.entity.dto.TourPackageDTO;
import utp.integrador.software2.service.TourPackageService;

@RestController
@RequestMapping("/api/tours")
public class TourPackageController {

    private final TourPackageService tourPackageService;

    public TourPackageController(TourPackageService tourPackageService) {
        this.tourPackageService = tourPackageService;
    }

    @GetMapping
    public List<TourPackageDTO> getTours(@RequestParam Map<String, String> filters) {
        return tourPackageService.getTours(filters);
    }

    @GetMapping("/{id}")
    public TourPackageDTO getTour(@PathVariable Long id) {
        return tourPackageService.getTourById(id);
    }
}

