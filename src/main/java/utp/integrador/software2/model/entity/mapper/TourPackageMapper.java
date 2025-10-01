/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utp.integrador.software2.model.entity.mapper;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import utp.integrador.software2.model.entity.TourPackage;
import utp.integrador.software2.model.entity.TourPackageImage;
import utp.integrador.software2.model.entity.dto.TourPackageDTO;

@Component
public class TourPackageMapper {

    public TourPackageDTO toDTO(TourPackage tourPackage) {
        List<String> images = tourPackage.getImages() != null
                ? tourPackage.getImages().stream().map(TourPackageImage::getImageUrl).collect(Collectors.toList())
                : Collections.emptyList();

        return new TourPackageDTO(
                tourPackage.getId(),
                tourPackage.getTitle(),
                tourPackage.getDescription(),
                tourPackage.getItinerary(),
                tourPackage.getBasePrice(),
                tourPackage.getDurationDays(),
                tourPackage.getDurationNights(),
                tourPackage.getMainImageUrl(),
                tourPackage.getIsActive(),
                tourPackage.getRegion() != null ? tourPackage.getRegion().getName() : null,
                tourPackage.getCategory() != null ? tourPackage.getCategory().getName() : null,
                images
        );
    }

    public List<TourPackageDTO> toDTOList(List<TourPackage> tourPackages) {
        return tourPackages.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
