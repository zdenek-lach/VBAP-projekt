package io.github.zdeneklach.vbap.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "game")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "game_id", nullable = false)
    private Long gameRoomId;
    @Column(name = "name")
    private String name;

    @ManyToOne()
    private Computer computer;

    public Game(String name) {
        this.name = name;
    }


}
