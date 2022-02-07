package io.github.zdeneklach.vbap.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "computer")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "computer_id", nullable = false)
    private Long id;
    @JsonIgnore
    @OneToMany(
            mappedBy = "computer",
            cascade = CascadeType.ALL
    )
    private List<Game> games;


    @ManyToOne
    private GameRoom gameRoom;

    public Computer(GameRoom gameRoom) {
        this.games = new ArrayList<>();
        this.gameRoom = gameRoom;
    }

    public void installNewGame(Game game) {
        game.setComputer(this);
        this.games.add(game);
    }
}
