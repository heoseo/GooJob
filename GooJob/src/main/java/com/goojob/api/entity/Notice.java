package com.goojob.api.entity;

import com.goojob.api.common.vo.AbstractVO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name="Notice")
public class Notice extends AbstractVO {

	private static final long serialVersionUID = 4684659169110593611L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column
	private String title;

	@Column(length = 10)
	private String name;
}
