/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utp.integrador.software2.service.impl;

import jakarta.persistence.EntityNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import utp.integrador.software2.exception.CartEmptyException;
import utp.integrador.software2.model.entity.Booking;
import utp.integrador.software2.model.entity.BookingItem;
import utp.integrador.software2.model.entity.CartItem;
import utp.integrador.software2.model.entity.User;
import utp.integrador.software2.repository.BookingRepository;
import utp.integrador.software2.repository.CartItemRepository;
import utp.integrador.software2.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final CartItemRepository cartItemRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, CartItemRepository cartItemRepository) {
        this.bookingRepository = bookingRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public Booking createBooking(User user) {
        List<CartItem> cart = cartItemRepository.findByUser(user);
        if (cart.isEmpty()) {
            throw new CartEmptyException("El carrito está vacío, no se puede crear la reserva");
        }

        BigDecimal totalPrice = BigDecimal.ZERO;
        List<BookingItem> bookingItems = new ArrayList<>();

        for (CartItem cartItem : cart) {
            BigDecimal itemPrice = cartItem.getTourPackage().getBasePrice()
                    .multiply(BigDecimal.valueOf(cartItem.getNumberOfTravelers()));

            BookingItem bookingItem = BookingItem.builder()
                    .tourPackage(cartItem.getTourPackage())
                    .travelDateStart(cartItem.getTravelDateStart())
                    .numberOfTravelers(cartItem.getNumberOfTravelers())
                    .price(itemPrice)
                    .build();

            bookingItems.add(bookingItem);
            totalPrice = totalPrice.add(itemPrice);
        }

        Booking booking = Booking.builder()
                .user(user)
                .status("PENDING")
                .bookingReference(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now())
                .items(bookingItems)
                .totalPrice(totalPrice)
                .build();

        // Asignar la reserva a cada BookingItem
        booking.getItems().forEach(item -> item.setBooking(booking));

        Booking saved = bookingRepository.save(booking);

        cartItemRepository.deleteAll(cart);

        return saved;
    }

    public Booking getBookingByReference(String reference) {
        return bookingRepository.findByBookingReference(reference)
                .orElseThrow(() -> new EntityNotFoundException("Booking no encontrado"));
    }

    public List<Booking> getUserBookings(User user) {
        return bookingRepository.findByUser(user);
    }
}
