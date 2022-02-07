package io.github.zdeneklach.vbap.controller;

import io.github.zdeneklach.vbap.entity.Customer;
import io.github.zdeneklach.vbap.entity.GameRoom;
import io.github.zdeneklach.vbap.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationsController {
    @Autowired
    ReservationService reservationService;


    @GetMapping("/customers/{gameRoomId}")
    private ResponseEntity<List<Customer>> listAllReservedCustomerForGameRoom(@PathVariable Long gameRoomId) {
        List<Customer> allReservedCustomerInGameRoom = reservationService.getAllReservedCustomerInGameRoom(gameRoomId);
        return ResponseEntity.status(HttpStatus.OK).body(allReservedCustomerInGameRoom);
    }

    @GetMapping("/gamerooms/{customerId}")
    private ResponseEntity<List<GameRoom>> listAllGameRoomsForCustomer(@PathVariable Long customerId) {
        List<GameRoom> allReservedGameRoomsForCustomer = reservationService.getAllReservedGameRoomsForCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(allReservedGameRoomsForCustomer);

    }

    @DeleteMapping("/customer/{customerId}/gameroom/{gameroomId}")
    private ResponseEntity<String> deleteReservationByIds(@PathVariable Long customerId, @PathVariable Long gameroomId) {
        try {
            String responseMessage = reservationService.removeCustomerReservationInGameRoom(gameroomId, customerId);
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong arguments, please check if reservation exists first.");
        }
    }

    @PostMapping("/customer/{customerId}/gameroom/{gameroomId}")
    private ResponseEntity<String> createNewReservation(@PathVariable Long customerId, @PathVariable Long gameroomId) {
        try {
            String responseMessage = reservationService.createNewReservation(customerId, gameroomId);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Reservation NOT saved. Please check if entered IDs are valid.");
        }
    }

    @PutMapping("/customer/{gameRoomId}/{customerOldId}/{customerNewId}")
    private ResponseEntity<String> editCustomerInReservation(@PathVariable Long gameRoomId, @PathVariable Long customerOldId, @PathVariable Long customerNewId) {
        String responseMessage = reservationService.editCustomerForReservation(gameRoomId, customerOldId, customerNewId);
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);


    }

    @PutMapping("/gameroom/{customerId}/{gameRoomOldId}/{gameroomNewId}")
    private ResponseEntity<String> editGameRoomInReservation(@PathVariable Long customerId, @PathVariable Long gameRoomOldId, @PathVariable Long gameroomNewId) {
        String responseMessage = reservationService.editGameRoomForReservation(customerId, gameRoomOldId, gameroomNewId);
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }
}
