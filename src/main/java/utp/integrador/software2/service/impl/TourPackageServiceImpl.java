/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utp.integrador.software2.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import utp.integrador.software2.model.entity.TourPackage;
import utp.integrador.software2.model.entity.dto.TourPackageDTO;
import utp.integrador.software2.model.entity.mapper.TourPackageMapper;
import utp.integrador.software2.repository.TourPackageRepository;
import utp.integrador.software2.service.TourPackageService;

@Service
public class TourPackageServiceImpl implements TourPackageService{
    private final TourPackageRepository tourPackageRepository;
    private final TourPackageMapper mapper;

    public TourPackageServiceImpl(TourPackageRepository tourPackageRepository, TourPackageMapper mapper) {
        this.tourPackageRepository = tourPackageRepository;
        this.mapper = mapper;
    }

    public List<TourPackageDTO> getTours(Map<String, String> filters) {
        // Aquí puedes aplicar filtros dinámicos más adelante
        List<TourPackage> tours = tourPackageRepository.findAll();
        return mapper.toDTOList(tours);
    }

    public TourPackageDTO getTourById(Long id) {
        TourPackage tour = tourPackageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tour no encontrado"));
        return mapper.toDTO(tour);
    }
}
