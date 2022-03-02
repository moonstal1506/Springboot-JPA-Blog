package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
	private int id;
	
	@Column(nullable=false, length=100)
	private String title;
	
	@Lob
	private String content;//섬머노트 라이브러리 <html>태그가 섞여서 디자인 됨
	
	private int count;//조회수
	
	@ManyToOne(fetch =FetchType.EAGER) //many=board, user=one
	@JoinColumn(name="userId")
	private User user; //db는 오브젝트를 저장할 수 없다.- fk/자바는 오브젝트를 저장할 수 있다.
	
	//mappedBy 연관관계의 주인이 아니다( FK가 아님) db에 칼럼 만들지마
	@OneToMany(mappedBy="board", fetch = FetchType.EAGER) 
	//	@JoinColumn(name="replyId") 필요없음 매니쪽에만
	@JsonIgnoreProperties({"board"})
	private List<Reply> replys;
	
	@CreationTimestamp
	private Timestamp createDate;
}
