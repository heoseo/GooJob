package com.goojob.api.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goojob.api.common.support.ControllerSupport;
import com.goojob.api.common.support.ResponseEntity;
import com.goojob.api.common.util.EncryptUtil;
import com.goojob.api.common.util.StringUtil;
import com.goojob.api.dao.SignUpDAO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Tag(name = "회원", description = "회원관련 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SignUpController extends ControllerSupport {

	private static final long serialVersionUID = -6237892020592492543L;

	/**
     * 회원가입
     */
	@Operation(summary = "회원가입 요청", description = "<strong>로그인 요청</strong>\n\n200 - Y:사용가능 N: 사용불가\n\n400 - 필수값 누락\n\n500 - Server Error")
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpDAO dao) {

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

	/**
	 * 휴대폰 인증번호 전송
	 * @param loginData
	 */
	@PostMapping("/sendVerifyCode")
    public void sendVerifyCode(@RequestBody(required = true) HashMap<String, String> loginData) {
        String phone = (String) loginData.get("phone");
        String num = (String) loginData.get("num");

       String hostNameUrl ="https://sens.apigw.ntruss.com";
       String requestUrl = "/sms/v2/services/";
       String requestUrlType = "/messages";
       String accessKey = "ncp_iam_BPAMKR4YwyHTG2hsUiO7";	//AccessKey 입력
       String secretKey = "ncp_iam_BPKMKR5yaH91dpGvMoFPk4PB4TuiEwlb8f"; //SecretKey 입력
       String serviceId = "ServiceId 입력";
       String method = "POST";
       String timestamp = Long.toString(System.currentTimeMillis());

       requestUrl += serviceId + requestUrlType;

       String apiUrl = hostNameUrl + requestUrl;

       // JSON을 활용한 body data 생성
       JSONObject bodyJson = new JSONObject();
       JSONObject toJson = new JSONObject();
       JSONArray toArr = new JSONArray();

       toJson.put("subject", ""); // LMS만 적용가능, 문자 제목
       toJson.put("content", "인증번호 [" + num +"]"); // 보내고자 하는 문자 내용
       toJson.put("to", phone); // 보내고자 하는 핸드폰 번호
       toArr.add(toJson);

       bodyJson.put("type", "sms"); // LMS, SMS 선택
       bodyJson.put("contentType", "COMM"); // AD(광고), COMM(일상) 선택
       bodyJson.put("countryCode", "82"); // 국가번호
       bodyJson.put("from", "등록된 핸드폰번호"); // 보낼 핸드폰 번호(기존등록된 번호만 가능)
       bodyJson.put("subject", "");
       bodyJson.put("content", "");
       bodyJson.put("messages", toArr);

       String body = bodyJson.toJSONString();

       try {
           URL url = new URL(apiUrl);

           HttpURLConnection con = (HttpURLConnection) url.openConnection();
           con.setUseCaches(false);
           con.setDoOutput(true);
           con.setDoInput(true);
           con.setRequestProperty("content-type", "application/json");
           con.setRequestProperty("x-ncp-apigw-timestamp", timestamp);
           con.setRequestProperty("x-ncp-iam-access-key", accessKey);
           con.setRequestProperty("x-ncp-apigw-signature-v2", EncryptUtil.makeSignature(timestamp));
           con.setRequestMethod(method);
           con.setDoOutput(true);
           DataOutputStream wr = new DataOutputStream(con.getOutputStream());

           wr.write(body.getBytes());
           wr.flush();
           wr.close();

           int responseCode = con.getResponseCode();
           BufferedReader br;
           System.out.println("responseCode" + " " + responseCode);
           if(responseCode == 202) {
               //정상호출
               br = new BufferedReader(new InputStreamReader(con.getInputStream()));
           }
           else {
               //에러발생
               br = new BufferedReader(new InputStreamReader(con.getInputStream()));
           }

           String inputLine;
           StringBuffer response = new StringBuffer();
           while((inputLine = br.readLine()) != null) {
               response.append(inputLine);
           }
           br.close();
           System.out.println(response.toString());
       }
       catch (Exception e) {
           System.out.println(e);
       }
    }



}
