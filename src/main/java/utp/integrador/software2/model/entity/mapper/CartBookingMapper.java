/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utp.integrador.software2.model.entity.mapper;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Component;
import utp.integrador.software2.model.entity.Booking;
import utp.integrador.software2.model.entity.BookingItem;
import utp.integrador.software2.model.entity.CartItem;
import utp.integrador.software2.model.entity.dto.BookingDTO;
import utp.integrador.software2.model.entity.dto.BookingItemDTO;
import utp.integrador.software2.model.entity.dto.CartItemDTO;

@Component
public class CartBookingMapper {

    public CartItemDTO toCartItemDTO(CartItem item) {
        return new CartItemDTO(
            item.getId(),
            item.getTourPackage().getId(),
            item.getTourPackage().getTitle(),
            item.getTravelDateStart(),
            item.getTravelDateEnd(),
            item.getNumberOfTravelers(),
            item.getTourPackage().getBasePrice()
                .multiply(BigDecimal.valueOf(item.getNumberOfTravelers()))
        );
    }

    public List<CartItemDTO> toCartItemDTOList(List<CartItem> items) {
        return items.stream()
            .map(this::toCartItemDTO)
            .toList();
    }
    
    public BookingItemDTO toBookingItemDTO(BookingItem item) {
        return new BookingItemDTO(
            item.getTourPackage().getId(),
            item.getTourPackage().getTitle(),
            item.getTravelDateStart(),
            item.getTravelDateEnd(),
            item.getNumberOfTravelers(),
            item.getPrice()
        );
    }

    public BookingDTO toBookingDTO(Booking booking) {
        List<BookingItemDTO> itemsDTO = booking.getItems().stream()
            .map(this::toBookingItemDTO)
            .toList();

        BigDecimal totalPrice = itemsDTO.stream()
            .map(BookingItemDTO::price)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new BookingDTO(
            booking.getBookingReference(),
            itemsDTO,
            totalPrice,
            booking.getStatus()
        );
    }
}


