package io.github.zdeneklach.vbap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "game_room")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class GameRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "game_room_id", nullable = false)
    private Long gameRoomId;
    @Column(name = "room_name")
    private String name;

    @JsonIgnore
    @OneToMany(
            mappedBy = "gameRoom",
            cascade = CascadeType.ALL
    )
    private List<Computer> computers;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "gameroom_to_customer",
            joinColumns = {@JoinColumn(name = "game_room_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id")}
    )
    private List<Customer> reservedCustomers;

    public GameRoom(String name) {
        this.name = name;
        this.computers = new ArrayList<>();
        this.reservedCustomers = new ArrayList<>();
    }

    public void assignCustomer(Customer customer) {
        reservedCustomers.add(customer);
        customer.addGameRoom(this);
    }

    public void removeCustomer(Long customerId) {
        reservedCustomers.removeIf(c -> c.getId().equals(customerId));
    }
}
