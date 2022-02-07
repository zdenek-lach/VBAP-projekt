package io.github.zdeneklach.vbap.repositories;

import io.github.zdeneklach.vbap.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
}
