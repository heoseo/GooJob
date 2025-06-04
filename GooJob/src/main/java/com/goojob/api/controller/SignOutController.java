package com.goojob.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goojob.api.common.support.ControllerSupport;
import com.goojob.api.common.support.ResponseEntity;
import com.goojob.api.common.util.StringUtil;
import com.goojob.api.dao.TestDAO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "회원", description = "회원관련 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SignOutController extends ControllerSupport {

	private static final long serialVersionUID = -6237892020592492543L;

	/**
     * 로그아웃 컨트롤러
     */
	@Operation(summary = "로그아웃 요청", description = "<strong>로그인 요청</strong>\n\n200 - Y:사용가능 N: 사용불가\n\n400 - 필수값 누락\n\n500 - Server Error")
    @PostMapping("/signOut")
    public ResponseEntity<?> signOut(@RequestBody TestDAO dao) {

		debug("debug log @@@@");
		debug("debuf log >> {}", dao.getUserNm());

		if(StringUtil.isNullOrEmpty(dao.getUserNm()))
		{
			return getFailResponse(400, "필수값 누락");
		}

    	try{
    		//joinService.signup(pvo);
    		return getOkResponse(dao.getUserNm());
    	} catch(Exception e) {
    		return getFailResponse(e.getMessage());
    	}

	}
}
