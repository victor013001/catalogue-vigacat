package com.vigacat.catalogue.dao.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Table(name = "genre")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Genre {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String genre;
}
