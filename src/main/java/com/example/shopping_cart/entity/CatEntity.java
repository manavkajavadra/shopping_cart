package com.example.shopping_cart.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "category")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Data
@Builder

public class CatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer catId;
    private String catName;
    private String status;
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    private Integer createdBy;
}
