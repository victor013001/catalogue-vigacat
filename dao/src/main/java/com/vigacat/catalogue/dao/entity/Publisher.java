package com.vigacat.catalogue.dao.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Table(name = "publisher")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Publisher {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String publisher;
}
