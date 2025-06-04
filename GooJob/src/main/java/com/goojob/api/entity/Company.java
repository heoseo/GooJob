package com.goojob.api.entity;

import com.goojob.api.common.vo.AbstractVO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
@Entity
@Table(name="COMPANY")
public class Company extends AbstractVO{

	private static final long serialVersionUID = -4059841856004608970L;

	/**
	 * 기업ID
	 */
	@Id
	private String id;

	/**
	 * 암호화비밀번호
	 */
	@Column
	private String encryptedPwd;

	/**
	 * 사업자등록번호
	 */
	@Column
	private String biz;

	/**
	 * 기업명
	 */
	@Column
	private String name;

	/**
	 * 대표번호 (대표번호가 여러개일 경우 테이블을 별도로 관리하는 것도 고려)
	 * 법인번호???
	 */
	@Column
	private String cgn;

	/**
	 * 이메일
	 */
	@Column
	private String email;


	/**
	 * 업종
	 */
	@Column
	private String industry;

	/**
	 * 웹사이트
	 */
	@Column
	private String website;

	/**
	 * 본사주소
	 */
	@Column
	private String address;

	/**
	 * 생성일시
	 */
	@Column
	private String createdDtim;

	/**
	 * 수정일시
	 */
	@Column
	private String updatedDtim;
}
