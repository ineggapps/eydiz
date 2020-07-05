# eydiz

이디즈 스프링

## 미리보기

![미리보기 이미지](https://raw.githubusercontent.com/ineggapps/eydiz/master/docs/eydis-studio-project.gif)

![미리보기 이미지](https://raw.githubusercontent.com/ineggapps/eydiz/master/docs/eydis-detail-view.gif)

## 세팅 방법

1. /src/main/webapp/WEB-INF/mybatis/jdbc.properties 파일 생성

```
jdbc.driverClass=net.sf.log4jdbc.DriverSpy
jdbc.url=jdbc:log4jdbc:oracle:thin:@아이피:1521:xe
jdbc.username=계정명
jdbc.password=비밀번호
```

3. /src/main/java/com/eydiz/reward/kakao/KakaoPayService.java 파일 생성

```java
package com.eydiz.reward.kakao;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.eydiz.member.Member;
import com.eydiz.member.SessionInfo;
import com.eydiz.reward.SessionRewardInfo;
import com.eydiz.studio.Reward;

@Service("detail.kakaoPay")
public class KakaoPayService {
	private static final String HOST = "https://kapi.kakao.com";
	private static final String ADMIN_KEY = "⌛⌛⌛⌛⌛관리자키입력⌛⌛⌛⌛⌛";

	private KakaoPayReady kakaoPayReady;
	private KakaoPayApproval kakaoPayApproval;

	public String kakaoPayReady(SessionRewardInfo rewardInfo, SessionInfo memberInfo) {

		//세션 관련 처리
		int projectNo = rewardInfo.getProjectNo();
		String projectName = null;
		Map<Integer, Reward> rewards = rewardInfo.getRewards();
		for(Integer key: rewards.keySet()) {
			projectName = rewards.get(key).getProjectName();
			break;
		}
		////카카오페이 관련 처리
		RestTemplate restTemplate = new RestTemplate();

		// 서버로 요청할 헤더 정보
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK" + " " + ADMIN_KEY);
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + "; charset=UTF-8");

		// 서버로 요청할 본문
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", "TC0ONETIME");
		params.add("partner_order_id", rewardInfo.getBuyNo()+"");
		params.add("partner_user_id", memberInfo.getMemberNo()+"");
		params.add("item_name", projectName);
		params.add("quantity", "1");
		params.add("total_amount", rewardInfo.getFinalAmount()+"");
		params.add("tax_free_amount", "0");
		params.add("approval_url", "http://localhost:9090/eydiz/reward/" + projectNo + "/pay/kakao/success");
		params.add("cancel_url", "http://localhost:9090/eydiz/reward/" + projectNo + "/pay/kakao/cancel");
		params.add("fail_url", "http://localhost:9090/eydiz/reward/" + projectNo + "/pay/kakao/fail");

		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);
		try {
			kakaoPayReady = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReady.class);
			return kakaoPayReady.getNext_redirect_pc_url();
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;// 오류난 경우
	}

	public KakaoPayApproval kakaoPayInfo(String pg_token, SessionRewardInfo rewardInfo, SessionInfo memberInfo) {
		RestTemplate restTemplate = new RestTemplate();

		//서버로 요청할 헤더 정보
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK" + " " + ADMIN_KEY);
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + "; charset=UTF-8");

		//서버로 요청할 본문
//		System.out.println("★"+rewardInfo.getBuyNo() + "," + memberInfo.getMemberNo() + "," + pg_token +"," + rewardInfo.getFinalAmount());
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReady.getTid());
        params.add("partner_order_id", rewardInfo.getBuyNo()+"");
        params.add("partner_user_id", memberInfo.getMemberNo()+"");
        params.add("pg_token", pg_token);
        params.add("total_amount", rewardInfo.getFinalAmount()+"");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);
        try {
        	kakaoPayApproval = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApproval.class);
        	return kakaoPayApproval;
        } catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
	}
}
```
