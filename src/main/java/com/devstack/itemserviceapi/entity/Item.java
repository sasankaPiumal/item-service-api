package com.devstack.itemserviceapi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id",length = 45)
    private long ItemId;

    @Column(name = "code",length = 45,nullable = false,unique = true)
    private long code;

    @Column(name = "description",length = 250,nullable = false)
    private  String description;

    @Column(name = "quantity",nullable = false)
    private long qty;

    @Column(name = "unit_price",length = 15)
    private double unitPrice;

    @Column(name = "active_state",columnDefinition = "TINYINT")
    private boolean activeState;

}
