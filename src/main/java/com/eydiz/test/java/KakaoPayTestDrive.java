package com.eydiz.test.java;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class KakaoPayTestDrive {
    private static final String HOST = "https://kapi.kakao.com";

    public static void main(String[] args) throws RestClientException, URISyntaxException {
	    paymentReady();
    }

    private static void paymentReady() throws RestClientException, URISyntaxException {

	    RestTemplate restTemplate = new RestTemplate();

	    // 서버로 요청할 Body
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	    params.add("cid", "TC0ONETIME");
	    params.add("partner_order_id", "10010");
	    params.add("partner_user_id", "hello_test");
	    params.add("item_name", "테스트 상품입니다");
	    params.add("quantity", "1");
	    params.add("total_amount", "12300");
	    params.add("tax_free_amount", "0");
	    params.add("approval_url", "http://localhost:9090/success.do");
	    params.add("cancel_url", "http://localhost:9090/abort.do");
	    params.add("fail_url", "http://localhost:9090/failure.do");
	
	    // 서버로 요청할 Header
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", "KakaoAK " + "ebc75f2be7b5f122bca0c3d7bd736ce9");
	    headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
	    headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

	    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
	    String response = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), request, String.class);
	    System.out.println(response);
    }
}