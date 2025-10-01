/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utp.integrador.software2.service;

import java.util.List;
import utp.integrador.software2.model.entity.CartItem;
import utp.integrador.software2.model.entity.User;
import utp.integrador.software2.model.entity.dto.AddCartItemRequest;

public interface CartService {
    public List<CartItem> getCart(User user);
    public CartItem addItem(User user, AddCartItemRequest request);
    public void removeItem(User user, Long itemId);
}
