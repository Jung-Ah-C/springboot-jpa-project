package com.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded // 내장 타입
    private Address address;

    @Enumerated(EnumType.STRING) // EnumType 지정은 꼭 "STRING"으로 사용하는 것이 좋다. 중간에 EnumType 값이 추가 됐을 때 혼란이 없도록 하기 위해서.
    private DeliveryStatus deliveryStatus; // ready (배송 준비), comp (배송중)
}
