/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package utp.integrador.software2.model.entity.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookingItemDTO(Long tourPackageId, String tourPackageTitle, LocalDate travelDateStart, int numberOfTravelers, BigDecimal price) {}

