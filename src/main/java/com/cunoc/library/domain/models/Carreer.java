package com.cunoc.library.domain.models;

import com.cunoc.library.adapters.out.entities.CarreerEntity;

import java.util.Date;

public record Carreer (
        String code,
        String name,
        Date createdAt,
        Date updatedAt
) {
    public Carreer(CarreerEntity carreerEntity) {
        this(
                carreerEntity.getCode(),
                carreerEntity.getName(),
                carreerEntity.getCreatedAt(),
                carreerEntity.getUpdatedAt()
        );
    }
}
