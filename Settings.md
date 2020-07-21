# eydiz

이디즈 스프링

## 미리보기

![미리보기 이미지](https://raw.githubusercontent.com/sannimdev/eydiz/master/docs/assets/settings/eydis-studio-project.gif)

![미리보기 이미지](https://raw.githubusercontent.com/sannimdev/eydiz/master/docs/assets/settings/eydiz-detail-view.gif)

## 세팅 방법

1. /src/main/webapp/WEB-INF/mybatis/jdbc.properties 파일 생성

```
jdbc.driverClass=net.sf.log4jdbc.DriverSpy
jdbc.url=jdbc:log4jdbc:oracle:thin:@아이피:1521:xe
jdbc.username=계정명
jdbc.password=비밀번호
```

3. /src/main/java/com/eydiz/reward/kakao/KakaoPayService.java 파일 생성
