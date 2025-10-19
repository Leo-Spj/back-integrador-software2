/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package utp.integrador.software2.model.entity.dto;

import java.math.BigDecimal;
import java.util.List;

public record TourPackageDTO(Long id, String title, String description, String itinerary, BigDecimal basePrice, int durationDays, int durationNights, 
        String mainImageUrl, Boolean isActive, String regionName, String categoryName, List<String> imageUrls) {}
