package com.goojob.api.common.vo;

import lombok.Data;

@Data
public class ApiResponseVO<T> extends AbstractVO {

	private static final long serialVersionUID = -7705762135749129818L;

	/**
	 * Status
	 */
	private Status status;

	/**
	 * Data
	 */
	private T data;

}
