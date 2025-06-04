package com.goojob.api.entity;


import com.goojob.api.common.vo.AbstractVO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
@Entity
@Table(name="PERSON")
public class Person extends AbstractVO{

	private static final long serialVersionUID = 8382900731281379592L;

	@Id
	private String id;
//	private String id2; // PK여러개
//	private String id3; // PK여러개

	@Column
	private String encryptedPwd;

	@Column(length = 10)
	private String name;

	@Column(length = 3)
	private String phoneNo1;

	@Column(length = 4)
	private String phoneNo2;

	@Column(length = 4)
	private String phoneNo3;

	@Column(length = 8)
	private String birthDate;

	@Column(unique = true, length = 50)
	private String email;

	@Transient //: DB와 매핑하지 않는 필드
	private int tmp;

}
