package com.cunoc.library.adapters.out.repositories;

import com.cunoc.library.adapters.out.entities.CareerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends JpaRepository<CareerEntity, String> {
}