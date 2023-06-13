package com.sisvime.app.repository;

import com.sisvime.app.entity.Cite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICiteRepostiory extends JpaRepository<Cite, Long> {
}
