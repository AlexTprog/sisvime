package com.sisvime.app.repository;

import com.sisvime.app.entity.roles.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends JpaRepository<Rol, Long> {
}
