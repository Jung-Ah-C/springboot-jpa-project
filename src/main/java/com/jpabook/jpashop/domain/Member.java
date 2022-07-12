package com.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded // 내장 타입이 있다는 것을 표시
    private Address address;

    @OneToMany(mappedBy = "member") // 일대다 관계, order entity의 member에 의해서 맵핑된 값이라고 명시 -> 읽기 전용
    private List<Order> orders = new ArrayList<>();

}
