package com.goojob.api.dao;

import com.goojob.api.common.vo.AbstractVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TestDAO extends AbstractVO{

	private static final long serialVersionUID = 8723353209118144258L;

	@Schema(description = "이름", example = "테스트")
	private String userNm;

}
