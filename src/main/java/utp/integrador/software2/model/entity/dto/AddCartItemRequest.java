/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package utp.integrador.software2.model.entity.dto;

import java.time.LocalDate;

public record AddCartItemRequest(Long tourPackageId, LocalDate travelDateStart, LocalDate travelDateEnd, int numberOfTravelers) {}

