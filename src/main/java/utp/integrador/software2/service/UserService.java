/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utp.integrador.software2.service;

import java.util.List;
import utp.integrador.software2.model.entity.dto.UserCreateDTO;
import utp.integrador.software2.model.entity.dto.UserDTO;

/**
 *
 * @author Joacs
 */
public interface UserService {
    public List<UserDTO> getAllUsers();
    public UserDTO getUserById(Long id);
    public UserDTO createUser(UserCreateDTO dto);
    public void deleteUser(Long id);
}
