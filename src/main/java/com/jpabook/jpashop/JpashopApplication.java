package com.jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 이 어노테이션이 붙어 있으면, 해당 패키지와 패키지 안의 하위 요소들을 컴포넌트 스캔을 함, 스프링 빈으로 자동 등록
public class JpashopApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpashopApplication.class, args);
	}

}
