package com.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계, 한 명의 주문자가 여러개의 주문을 할 수 있음
    @JoinColumn(name = "member_id") // FK name 지정
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id") // 일대일 관계일 경우에는 양쪽 어느 곳에 FK를 둬도 상관 없음, 많이 조회하는 쪽에 두는 것이 효율적임
    private Delivery delivery;
    
    private LocalDateTime orderDate; // 주문시간, 자바8에서는 LocalDateTime을 쓰면 Hibernate가 지원해줌

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 : order, cancel

    // == 연관관계 편의 메서드 (양방향 일 때 사용) ==
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

}
