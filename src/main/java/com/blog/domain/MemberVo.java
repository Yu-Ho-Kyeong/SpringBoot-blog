package com.blog.domain;

import lombok.Data;

@Data
public class MemberVo {

	private Long id;
	private String memberId;
	private String username;
	private String email;
	private String picUrl;

}
