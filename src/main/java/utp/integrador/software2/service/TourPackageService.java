/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package utp.integrador.software2.service;

import java.util.List;
import java.util.Map;
import utp.integrador.software2.model.entity.TourPackage;
import utp.integrador.software2.model.entity.dto.TourPackageDTO;

public interface TourPackageService {
    public List<TourPackageDTO> getTours(Map<String, String> filters);
    public TourPackageDTO getTourById(Long id);
}
