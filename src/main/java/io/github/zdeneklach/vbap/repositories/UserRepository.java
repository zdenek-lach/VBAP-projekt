package io.github.zdeneklach.vbap.repositories;

import io.github.zdeneklach.vbap.entity.MyUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUserDetails, Long> {
}
