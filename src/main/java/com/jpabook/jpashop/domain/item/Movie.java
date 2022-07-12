package com.jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("M") // single table을 사용할 때, 구분하기 위해 넣어주는 값
@Getter @Setter
public class Movie extends Item {

    private String director;
    private String actor;

}
