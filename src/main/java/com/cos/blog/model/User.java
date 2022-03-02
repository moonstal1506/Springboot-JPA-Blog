package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//ORM->Java(다른언어) Object->테이블로 매핑해주는 기술
@Entity//User클래스가 MySQL에 테이블이 생성 된
//@DynamicInsert //insert할때 null 인 필드 제외
public class User {
	
	@Id//Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY)//연결된 db의 넘버링 전략 따라간다
	private int id; //시퀀스, auto_increment
	
	@Column(nullable=false, length =100, unique=true)
	private String username;//아이디
	
	@Column(nullable=false, length =100)
	private String password;
	
	@Column(nullable=false, length =50)
	private String email;
	
    //	@ColumnDefault("user")
	//DB는 RoleType 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role;//Enum을 쓰는게 좋다. //ADMIN,USER
	
	private String oauth;//kakao,google
	
	@CreationTimestamp//시간자동입력
	private Timestamp createDate;
}
