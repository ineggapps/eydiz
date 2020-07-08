--구매내역 초기화
delete from reward_shipping_location;
delete from reward_buy_detail;
delete from reward_buy_cancel;
delete from reward_buy_overview;
commit;

--상품 목록
select * from reward;

--구매 상세
desc reward_buy_detail;
select * from reward_buy_overview;
select * from reward_buy_Detail;
--구매 취소 테이블
desc reward_buy_cancel;
select * from reward_buy_cancel;
--취소데이터 지우기
delete from reward_buy_cancel;
delete from test_log;
commit;
--취소데이터 입력
INSERT INTO reward_buy_cancel(cancelno, buyno, canceledamount, memo, canceledDate) VALUES(reward_buy_cancel_seq.NEXTVAL, 39, 759800 , '취소', SYSDATE);
commit;
select * from test_log;

--트리거
CREATE OR REPLACE TRIGGER insertTriggerRewardCancel
BEFORE INSERT ON reward_buy_cancel
FOR EACH ROW
    
DECLARE
    vBuyNo NUMBER;
    vAAAAA NUMBER;
BEGIN
        vBuyNo := :NEW.buyNo;
        DBMS_OUTPUT.PUT_LINE(vbuyNo);
        
        select count(*) into vAAAAA FROM reward_buy_overview ro 
            JOIN reward_buy_detail rd ON rd.buyNo = ro.buyNo
            WHERE ro.buyNo = vBuyNo;
        DBMS_OUTPUT.PUT_LINE(vAAAAA);
                
        FOR REC IN (
            SELECT rewardNo, rd.requestQuantity
            FROM reward_buy_overview ro
            JOIN reward_buy_detail rd ON rd.buyNo = ro.buyNo
            WHERE ro.buyNo = vBuyNo
        ) LOOP 
            DBMS_OUTPUT.PUT_LINE(REC.rewardNo||'.a'||REC.requestQuantity);
            -- 리워드 잔여수량 정보 갱신
           UPDATE reward SET remainQuantity = remainQuantity + REC.requestQuantity
           WHERE rewardNo = REC.rewardNo;  
           INSERT INTO test_log VALUES (test_log_seq.NEXTVAL, REC.requestQuantity, REC.rewardNo);
        END LOOP;
END;
/
drop trigger insertTriggerRewardCancel;
select * from reward_buy_cancel;

--트리거 연습 구문
select rc.buyno, rewardNo, requestQuantity
from reward_buy_cancel rc
join reward_buy_overview ro on ro.buyno = rc.buyno
join reward_buy_detail rd on rd.buyno = ro.buyno
where cancelNo=14;
--
SELECT rewardNo, requestQuantity
            FROM reward_buy_overview ro
            JOIN reward_buy_detail rd ON rd.buyNo = ro.buyNo
            WHERE ro.buyNo = 39;
            
            commit;




--테스트 테이블
select test_log_seq.CURRVAL from dual;
create table test_log(
    num number,
    memo1 VARCHAR(500),
    memo2 VARCHAR(500)
);
drop table test_log;
select * from test_log;
create sequence test_log_seq;



