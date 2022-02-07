package io.github.zdeneklach.vbap.repositories;

import io.github.zdeneklach.vbap.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository extends JpaRepository<Computer,Long> {
}
