/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utp.integrador.software2.model.entity.mapper;

import org.springframework.stereotype.Component;
import utp.integrador.software2.model.entity.User;
import utp.integrador.software2.model.entity.dto.UserResponse;
import utp.integrador.software2.model.entity.dto.UserUpdate;

@Component
public class UserMapper {

    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.isPremium()
        );
    }

    public UserUpdate toUserUpdate(User user) {
        return new UserUpdate(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone()
        );
    }
}
