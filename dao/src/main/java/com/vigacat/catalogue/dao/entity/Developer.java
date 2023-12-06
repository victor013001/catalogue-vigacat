package com.vigacat.catalogue.dao.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Table(name = "developer")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Developer {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String developer;
}
