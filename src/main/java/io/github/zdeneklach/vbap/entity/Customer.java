package io.github.zdeneklach.vbap.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id", nullable = false)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "surname")
    private String surname;
    @Column(name = "phone_number")
    private String phoneNumber;

    @JsonIgnore
    @ManyToMany(
            mappedBy = "reservedCustomers"
    )
    private List<GameRoom> reservedGameRooms;

    public Customer(String firstName, String surname, String phoneNumber) {
        this.firstName = firstName;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.reservedGameRooms = new ArrayList<>();
    }

    public void addGameRoom(GameRoom gameRoom) {
        reservedGameRooms.add(gameRoom);
    }

    public void removeGameRoom(Long gameRoomId) {
        reservedGameRooms.removeIf(gameRoom -> gameRoom.getGameRoomId().equals(gameRoomId));
    }
}
