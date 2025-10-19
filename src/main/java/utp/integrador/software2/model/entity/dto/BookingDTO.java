/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package utp.integrador.software2.model.entity.dto;

import java.math.BigDecimal;
import java.util.List;

public record BookingDTO(String bookingReference, List<BookingItemDTO> items, BigDecimal totalPrice, String status) {}


