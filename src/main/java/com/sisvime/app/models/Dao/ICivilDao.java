package com.sisvime.app.models.Dao;

import com.sisvime.app.models.entity.Civil;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICivilDao extends PagingAndSortingRepository<Civil, Long> {

}
