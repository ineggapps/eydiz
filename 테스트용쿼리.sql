--회원정보 보기

SELECT * FROM member m
JOIN member_detail d ON m.memberNo = d.memberNo
ORDER BY m.memberNo DESC;