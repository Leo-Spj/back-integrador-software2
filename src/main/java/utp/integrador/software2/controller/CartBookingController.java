/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utp.integrador.software2.controller;

import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.integrador.software2.model.entity.User;
import utp.integrador.software2.model.entity.dto.AddCartItemRequest;
import utp.integrador.software2.model.entity.dto.BookingDTO;
import utp.integrador.software2.model.entity.dto.CartItemDTO;
import utp.integrador.software2.model.entity.mapper.CartBookingMapper;
import utp.integrador.software2.service.BookingService;
import utp.integrador.software2.service.CartService;
import utp.integrador.software2.service.UserService;

@RestController
@RequestMapping("/api/CartBooking")
public class CartBookingController {

    private final CartService cartService;
    private final BookingService bookingService;
    private final CartBookingMapper mapper;
    private final UserService userService;

    public CartBookingController(CartService cartService, BookingService bookingService,
            CartBookingMapper mapper, UserService userService) {
        this.cartService = cartService;
        this.bookingService = bookingService;
        this.mapper = mapper;
        this.userService = userService;
    }

    @GetMapping("/cart")
    public List<CartItemDTO> getCart(Authentication auth) {
        User user = userService.getProfile(auth.getName()).orElseThrow();
        return cartService.getCart(user).stream().map(mapper::toCartItemDTO).toList();
    }

    @PostMapping("/cart/items")
    public CartItemDTO addCartItem(Authentication auth, @RequestBody AddCartItemRequest request) {
        User user = userService.getProfile(auth.getName()).orElseThrow();
        return mapper.toCartItemDTO(cartService.addItem(user, request));
    }

    @DeleteMapping("/cart/items/{itemId}")
    public void removeCartItem(Authentication auth, @PathVariable Long itemId) {
        User user = userService.getProfile(auth.getName()).orElseThrow();
        cartService.removeItem(user, itemId);
    }

    @PostMapping("/bookings")
    public BookingDTO createBooking(Authentication auth) {
        User user = userService.getProfile(auth.getName()).orElseThrow();
        return mapper.toBookingDTO(bookingService.createBooking(user));
    }

    @GetMapping("/bookings/{bookingReference}")
    public BookingDTO getBooking(@PathVariable String bookingReference) {
        return mapper.toBookingDTO(bookingService.getBookingByReference(bookingReference));
    }

    @GetMapping("/user/bookings")
    public List<BookingDTO> getUserBookings(Authentication auth) {
        User user = userService.getProfile(auth.getName()).orElseThrow();
        return bookingService.getUserBookings(user).stream().map(mapper::toBookingDTO).toList();
    }
}
