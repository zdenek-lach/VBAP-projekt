package io.github.zdeneklach.vbap.service;

import io.github.zdeneklach.vbap.entity.Customer;
import io.github.zdeneklach.vbap.entity.GameRoom;
import io.github.zdeneklach.vbap.exception.DbException;
import io.github.zdeneklach.vbap.repositories.CustomerRepository;
import io.github.zdeneklach.vbap.repositories.GameRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final GameRoomRepository gameRoomRepository;
    private final CustomerRepository customerRepository;

    public ReservationService(GameRoomRepository gameRoomRepository, CustomerRepository customerRepository) {
        this.gameRoomRepository = gameRoomRepository;
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllReservedCustomerInGameRoom(Long gameRoomId) {
        GameRoom gameRoom = gameRoomRepository.findById(gameRoomId).orElseThrow(() -> new IllegalArgumentException("Gameroom with id '" + gameRoomId + "' was not found"));
        return gameRoom.getReservedCustomers();
    }

    public List<GameRoom> getAllReservedGameRoomsForCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer with id '" + customerId + "' was not found."));
        return customer.getReservedGameRooms();
    }

    public String removeCustomerReservationInGameRoom(Long gameRoomId, Long customerId) {
        GameRoom gameRoom = gameRoomRepository.findById(gameRoomId).orElseThrow(() -> new IllegalArgumentException("GameRoom with id '" + gameRoomId + "' was not found."));
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer with id '" + customerId + "' was not found."));
        if (customer.getReservedGameRooms().contains(gameRoom)) {
            gameRoom.removeCustomer(customerId);
            customer.removeGameRoom(gameRoomId);
            gameRoomRepository.save(gameRoom);
            customerRepository.save(customer);
            return "Customer with ID '" + customerId + "' has been removed from gameroom ID '" + gameRoomId + "'.";
        } else
            return "Customer with ID '" + customerId + "' does not have reservation for gameroom ID '" + gameRoomId + "'.";
    }

    public String createNewReservation(Long customerId, Long gameRoomId) {
        GameRoom gameRoom = gameRoomRepository.findById(gameRoomId).orElseThrow(() -> new IllegalArgumentException("GameRoom with id '" + gameRoomId + "' was not found."));
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer with id '" + customerId + "' was not found."));

        if (!customer.getReservedGameRooms().contains(gameRoom))
            try {
                gameRoom.assignCustomer(customer);
                customer.addGameRoom(gameRoom);
                gameRoomRepository.save(gameRoom);
                customerRepository.save(customer);
                return "Reservation for customer ID '" + customerId + "' was successfully created in gameroom ID '" + gameRoomId + "'.";
            } catch (Exception e) {
                throw new DbException("Editing failed due to ", e);
            }
        else return "Customer with id '" + customerId + "' already has a reservation in gameroom ID '" + gameRoomId + "'.";
    }

    public String editCustomerForReservation(Long gameRoomId, Long customerOldId, Long customerNewId) {
        GameRoom gameRoom = gameRoomRepository.findById(gameRoomId).orElseThrow(() -> new IllegalArgumentException("GameRoom with id '" + gameRoomId + "' was not found."));
        Customer oldCustomer = customerRepository.findById(customerOldId).orElseThrow(() -> new IllegalArgumentException("Customer with id '" + customerOldId + "' was not found."));
        Customer newCustomer = customerRepository.findById(customerNewId).orElseThrow(() -> new IllegalArgumentException("Customer with id '" + customerNewId + "' was not found."));

        if (!newCustomer.getReservedGameRooms().contains(gameRoom)) {
            gameRoom.removeCustomer(oldCustomer.getId());
            gameRoom.assignCustomer(newCustomer);
            oldCustomer.removeGameRoom(gameRoomId);
            newCustomer.addGameRoom(gameRoom);
            try {
                gameRoomRepository.save(gameRoom);
                return "Reservation in gameroom ID '" + gameRoomId + "' was changed from customer ID '" + customerOldId + "' to Customer ID '" + customerNewId + "'.";
            } catch (Exception e) {
                throw new DbException("Editing failed due to ", e);
            }
        } else
            return "Gameroom already has reservation for customer with ID '" + customerNewId + "'\n Reservation for customer has not been modified.";
    }


    public String editGameRoomForReservation(Long customerId, Long gameRoomOldId, Long gameroomNewId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer with id '" + customerId + "' was not found."));
        GameRoom oldGameRoom = gameRoomRepository.findById(gameRoomOldId).orElseThrow(() -> new IllegalArgumentException("GameRoom with id '" + gameRoomOldId + "' was not found."));
        GameRoom newGameRoom = gameRoomRepository.findById(gameroomNewId).orElseThrow(() -> new IllegalArgumentException("GameRoom with id '" + gameroomNewId + "' was not found."));

        if (!newGameRoom.getReservedCustomers().contains(customer)) {
            customer.removeGameRoom(oldGameRoom.getGameRoomId());
            customer.addGameRoom(newGameRoom);
            oldGameRoom.removeCustomer(customerId);
            newGameRoom.assignCustomer(customer);
            try {
                customerRepository.save(customer);
                gameRoomRepository.save(oldGameRoom);
                gameRoomRepository.save(newGameRoom);
                return "Reservation for customer ID '" + customerId + "' has been changed from gameroom ID '" + gameRoomOldId + "' to gameroom ID '" + gameroomNewId + "'.";
            } catch (Exception e) {
                throw new DbException("Editing failed due to ", e);
            }

        } else
            return "Customer already has reservation for gameroom with ID '" + gameroomNewId + "'\n Reservation for gameroom has not been modified.";
    }
}
