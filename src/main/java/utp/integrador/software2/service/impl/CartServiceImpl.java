/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utp.integrador.software2.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import utp.integrador.software2.model.entity.CartItem;
import utp.integrador.software2.model.entity.TourPackage;
import utp.integrador.software2.model.entity.User;
import utp.integrador.software2.model.entity.dto.AddCartItemRequest;
import utp.integrador.software2.repository.CartItemRepository;
import utp.integrador.software2.repository.TourPackageRepository;
import utp.integrador.software2.service.CartService;

@Service
public class CartServiceImpl implements CartService{
    private final CartItemRepository cartItemRepository;
    private final TourPackageRepository tourPackageRepository;

    public CartServiceImpl(CartItemRepository cartItemRepository, TourPackageRepository tourPackageRepository) {
        this.cartItemRepository = cartItemRepository;
        this.tourPackageRepository = tourPackageRepository;
    }

    public List<CartItem> getCart(User user) {
        return cartItemRepository.findByUser(user);
    }

    public CartItem addItem(User user, AddCartItemRequest request) {
        TourPackage tour = tourPackageRepository.findById(request.tourPackageId())
                .orElseThrow(() -> new EntityNotFoundException("Tour no encontrado"));
        CartItem item = new CartItem();
        item.setUser(user);
        item.setTourPackage(tour);
        item.setTravelDateStart(request.travelDateStart());
        item.setNumberOfTravelers(request.numberOfTravelers());
        item.setCreatedAt(LocalDateTime.now());
        return cartItemRepository.save(item);
    }

    public void removeItem(User user, Long itemId) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Cart item no encontrado"));
        if (!item.getUser().getId().equals(user.getId())) {
            throw new SecurityException("No se puede remover el item de otro user's");
        }
        cartItemRepository.delete(item);
    }
}
