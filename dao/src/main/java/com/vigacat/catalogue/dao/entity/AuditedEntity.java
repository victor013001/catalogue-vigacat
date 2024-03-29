package com.vigacat.catalogue.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class AuditedEntity {

    @Column
    private String createdBy;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String updatedBy;

    @Column
    private LocalDateTime updatedAt;

}
