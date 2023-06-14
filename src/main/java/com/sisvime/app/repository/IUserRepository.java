package com.sisvime.app.repository;

import com.sisvime.app.entity.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
