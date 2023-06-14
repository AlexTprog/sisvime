package com.sisvime.app.repository;

import com.sisvime.app.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDriverRepostiory extends JpaRepository<Driver, Long> {
}
