package com.cunoc.library.adapters.out.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "career")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareerEntity {

    @Id
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
