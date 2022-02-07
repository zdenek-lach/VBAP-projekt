package io.github.zdeneklach.vbap.repositories;

import io.github.zdeneklach.vbap.entity.GameRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRoomRepository extends JpaRepository<GameRoom, Long> {
}
