package com.blog.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
- 멤버 권한 
    - ADMIN 계정은 하나만
    - 나머지는 모두 USER계정
*/

@RequiredArgsConstructor // 생성자 주입
@Getter
public enum Role {
	
	USER("ROLE_USER"),
	ADMIN("ROLE_ADMIN");

	private final String value;

}
