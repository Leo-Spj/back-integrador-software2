/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package utp.integrador.software2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.integrador.software2.model.entity.TourPackage;

@Repository
public interface TourPackageRepository extends JpaRepository<TourPackage, Long> {
    
}
