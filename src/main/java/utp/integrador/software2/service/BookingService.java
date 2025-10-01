/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utp.integrador.software2.service;

import java.util.List;
import utp.integrador.software2.model.entity.Booking;
import utp.integrador.software2.model.entity.User;

public interface BookingService {
    public Booking createBooking(User user);
    public Booking getBookingByReference(String reference);
    public List<Booking> getUserBookings(User user);
}
