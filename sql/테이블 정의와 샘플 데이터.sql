---- 회원
--회원
CREATE TABLE member(
    --회원번호: 기본키
    memberNo NUMBER NOT NULL,
    -- 활성화여부 0: 이용불가, 1: 일반회원
    available NUMBER(1) DEFAULT 1 NOT NULL,
    CONSTRAINT PK_MEMBER_MEMBERNUM primary key(memberNo)
);

CREATE SEQUENCE member_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;

--회원상세
CREATE TABLE member_detail(
    memberNo NUMBER NOT NULL,
    memberId VARCHAR2(50) NOT NULL,
    memberNickname VARCHAR2(50) NOT NULL,
    memberPwd VARCHAR2(200) NOT NULL,
    memberEmail VARCHAR2(200) NOT NULL,
    memberSocial NUMBER,
    memberImageUrl VARCHAR2(200),
    CONSTRAINT PK_MEMBER_DETAIL_MEMBERNO PRIMARY KEY(memberNo),
    CONSTRAINT UK_MEMBER_DETAIL_MEMBERID UNIQUE(memberId),
    CONSTRAINT UK_MEMBER_DETAIL_MEMBEREMAIL UNIQUE(memberEmail),
    CONSTRAINT FK_MEMBER_DETAIL_MEMBERNO FOREIGN KEY(memberNo) REFERENCES MEMBER(memberNo) ON DELETE CASCADE
);

--회원 중 관리자
CREATE TABLE member_admin(
    memberNo NUMBER NOT NULL,
    CONSTRAINT PK_MEMBER_ADMIN_MEMBERNO PRIMARY KEY(memberNo),
    CONSTRAINT FK_MEMBER_ADMIN_MEMBERNO FOREIGN KEY(memberNo) REFERENCES member(memberNo)
);


CREATE SEQUENCE member_admin_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;

----브랜드
--브랜드 정보
CREATE TABLE brand(
    --원래는 회원번호를 기본키로 잡아야 하지만, 본래의 서비스는 브랜드도 회원당 여러 개를 만들 수 있으므로 구조는 그대로 클론하였음. (추후 확장 가능성을 두고...) 
    brandNo NUMBER NOT NULL,
    memberNo NUMBER NOT NULL,
    brandName VARCHAR2(255) NOT NULL,
    managerName VARCHAR2(100) NOT NULL,
    managerEmail VARCHAR2(200) NOT NULL,
    managerPhone VARCHAR2(100) NOT NULL,
    CONSTRAINT PK_BRAND_BRANDNO PRIMARY KEY(brandNo),
    CONSTRAINT FK_BRAND_MEMBERNO FOREIGN KEY(memberNo) REFERENCES member(memberNo)
);

CREATE SEQUENCE brand_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;

--프로젝트
-- 프로젝트 개요

--프로젝트 카테고리
-- 펀딩
-- 테크가전, 패션잡화, 뷰티, 푸드, 홈리빙, 디자인소품, 여행레저, 스포츠모빌리티, 반려동물, 교육키즈, 게임취미, 출판
-- 후원

CREATE TABLE project_category( -- 프로젝트 카테고리
    categoryNo NUMBER NOT NULL,
    parentCategoryNo NUMBER NULL,
    categoryName VARCHAR2(100) NOT NULL,
    imageUrl VARCHAR2(200) NOT NULL,
    CONSTRAINT PK_CATEGORY_NO PRIMARY KEY(categoryNo),
    CONSTRAINT FK_CATEGORY_NO FOREIGN KEY(parentCategoryNo) REFERENCES project_category(categoryNo)    
);


CREATE SEQUENCE project_category_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;

CREATE TABLE project_status(
    statusNo NUMBER NOT NULL,
    statusName VARCHAR2(100) NOT NULL,
    CONSTRAINT PK_PROJECT_STATUS PRIMARY KEY(statusNo)
);


CREATE SEQUENCE project_status_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;

CREATE TABLE project(
    projectNo NUMBER NOT NULL, -- 프로젝트 일련번호
    brandNo NUMBER NOT NULL, -- 브랜드 일련번호 
    categoryNo NUMBER NOT NULL, -- 프로젝트 구분 (펀딩, 후원 등... ) <= 계층형임에 유의
    projectName VARCHAR2(100) NOT NULL, -- 프로젝트명
    projectStatusMemo VARCHAR2(255)  NOT NULL, -- 프로젝트 진행상황 텍스트 작성
    projectImageUrl VARCHAR2(200), -- 프로젝트 이미지 경로
    projectGoalAmount NUMBER NOT NULL, -- 목표 금액
    projectAttainAmount NUMBER DEFAULT 0 NOT NULL, -- 달성 금액 (0)
    projectCreatedDate DATE DEFAULT SYSDATE NOT NULL, -- 프로젝트 생성일자
    projectUpdatedDate DATE DEFAULT SYSDATE NOT NULL, -- 프로젝트 수정일자
    projectStartDate DATE NOT NULL, -- 프로젝트 시작일자
    projectEndDate DATE NOT NULL, -- 프로젝트 종료일자
    statusNo NUMBER NOT NULL, -- 프로젝트 상태번호 (심사중, 반려, 신고, 활성화, 종료)
    projectSummary VARCHAR2(4000) NULL, -- 프로젝트 요약
    projectStory CLOB NULL, -- 프로젝트 스토리
    CONSTRAINT PK_PROJECT_PROJECTNO PRIMARY KEY(projectNo),
    CONSTRAINT FK_PROJECT_BRANDNO FOREIGN KEY(brandNo) REFERENCES brand(brandNo),
    CONSTRAINT FK_PROJECT_CATEGORYNO FOREIGN KEY(categoryNo) REFERENCES project_category(categoryNo),
    CONSTRAINT FK_PROJECT_STATUSNO FOREIGN KEY(statusNo) REFERENCES project_status_info(statusNo)
);

CREATE SEQUENCE project_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;

   
CREATE TABLE project_detail( -- 프로젝트 상세 (심사를 위한 테이블)
    projectNo NUMBER NOT NULL, -- 프로젝트 번호
    statusMemo CLOB NOT NULL, --프로젝트 
    planForReward CLOB NOT NULL,
    CONSTRAINT PK_PROJECT_DETAIL_PROJECTNO PRIMARY KEY(projectNo),
    CONSTRAINT FK_PROJECT_DETAIL_PROJECTNO FOREIGN KEY(projectNo) REFERENCES project(projectNo) 
);

CREATE TABLE project_like(
    projectNo NUMBER NOT NULL,
    memberNo NUMBER NOT NULL,
    CONSTRAINT PK_PROJECT_LIKE PRIMARY KEY (projectNo, memberNo),
    CONSTRAINT FK_PROJECT_LIKE_PROJECT_NO FOREIGN KEY(projectNo) REFERENCES project(projectNo) ON DELETE CASCADE,
    CONSTRAINT FK_PROJECT_LIKE_MEMBER_NO FOREIGN KEY(memberNo) REFERENCES member(memberNo) ON DELETE CASCADE
);

CREATE TABLE project_status_list( -- 식별 테이블
    projectNo NUMBER NOT NULL, -- 프로젝트 번호
    statusNo NUMBER NOT NULL, -- 프로젝트 진행상태번호
    statusMemo VARCHAR2(4000), -- 프로젝트 메모
    createdDate DATE default SYSDATE, -- 상태 생성 시각...
    CONSTRAINT FK_PROJECT_STATUS_LIST_PROJECT_NO FOREIGN KEY(projectNo) REFERENCES project(projectNo),
    CONSTRAINT FK_PROJECT_STATUS_LIST_STATUS_NO FOREIGN KEY(statusNo) REFERENCES project_status_info(statusNo)
);

CREATE TABLE project_hashtag(
    tagNo NUMBER NOT NULL, -- 해시태그 번호
    projectNo NUMBER NOT NULL, -- 프로젝트 번호 
    keyword VARCHAR2(100) NOT NULL, -- 키워드
    CONSTRAINT PK_PROJECT_HASHTAG_TAGNO PRIMARY KEY(projectNo, keyword), --중복 방지.. tagNo는 시간순서로 등록한 것 정렬 역할
    CONSTRAINT FK_PROJECT_HASHTAG_PROJECTNO FOREIGN KEY(projectNo) REFERENCES project(projectNo)
);

CREATE SEQUENCE project_hashtag_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;

CREATE TABLE project_news(
    newsNo NUMBER NOT NULL,
    projectNo NUMBER NOT NULL,
    createdDate DATE DEFAULT SYSDATE NOT NULL,
    title VARCHAR2(255) NOT NULL,
    content CLOB,
    CONSTRAINT PK_PROJECT_NEWS_NEWSNO PRIMARY KEY(newsNo),
    CONSTRAINT FK_PROJECT_NEWS_PROJECTNO FOREIGN KEY(projectNo) REFERENCES project(projectNo)
);

CREATE SEQUENCE project_news_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;

CREATE TABLE project_news_comment(
    commentNo NUMBER NOT NULL,
    parentCommentNo NUMBER DEFAULT NULL,
    newsNo NUMBER NOT NULL,
    memberId VARCHAR2(50) NOT NULL,
    createdDate DATE DEFAULT SYSDATE NOT NULL,
    content VARCHAR2(4000),
    CONSTRAINT PK_PROJECT_NEWS_COMMENT_COMMENTNO PRIMARY KEY (commentNo),
    CONSTRAINT FK_PROJECT_NEWS_COMMENT_PARENTCOMMENTNO FOREIGN KEY(parentCommentNo) REFERENCES project_news_comment(commentNo),
    CONSTRAINT FK_PROJECT_NEWS_COMMENT_NEWSNO FOREIGN KEY(newsNo) REFERENCES project_news(newsNo),
    CONSTRAINT FK_PROJECT_NEWS_COMMENT_MEMBERID FOREIGN KEY(memberId) REFERENCES member_detail(memberId)
);

CREATE SEQUENCE project_news_comment_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;

CREATE TABLE project_report(
    projectNo NUMBER NOT NULL,
    memberNo NUMBER NOT NULL,
    content VARCHAR2(4000) NOT NULL,
    createdDate DATE DEFAULT SYSDATE NOT NULL,
    CONSTRAINT PK_PROJECT_REPORT PRIMARY KEY(projectNo, memberNo),
    CONSTRAINT FK_PROJECT_REPORT_PROJECTNO FOREIGN KEY(projectNo) REFERENCES project(projectNo),
    CONSTRAINT FK_PROJECT_REPORT_MEMBERNO FOREIGN KEY(memberNo) REFERENCES member(memberNo)
);

CREATE TABLE project_report_result(
    projectNo NUMBER NOT NULL,
    receptionDate DATE NOT NULL,
    memo VARCHAR2(4000) NOT NULL,
    CONSTRAINT PK_PROJECT_REPORT_RESULT PRIMARY KEY(projectNo),
    CONSTRAINT FK_PROJECT_REPORT_RESULT FOREIGN KEY(projectNo) REFERENCES project(projectNo)
);

--프로젝트 커뮤니티 (덧글)

CREATE TABLE project_community(
    commentNo NUMBER NOT NULL,
    parentCommentNo NUMBER,
    projectNo NUMBER NOT NULL,
    memberId VARCHAR2(50) NOT NULL,
    content VARCHAR2(4000) NOT NULL,
    createdDate DATE DEFAULT SYSDATE NOT NULL,
    CONSTRAINT PK_PROJECT_COMMUNITY_COMMENTNO PRIMARY KEY(commentNo),
    CONSTRAINT FK_PROJECT_COMMUNITY_PARENTCOMMENTNO FOREIGN KEY(parentCommentNo) REFERENCES project_community(commentNo) ON DELETE CASCADE,
    CONSTRAINT FK_PROJECT_COMMUNITY_PROJECTNO FOREIGN KEY(projectNo) REFERENCES project(projectNo),
    CONSTRAINT FK_PROJECT_COMMUNITY_MEMBERID FOREIGN KEY(memberId) REFERENCES member_detail(memberId)
);

CREATE SEQUENCE project_community_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;

-- 문의하기 테이블
CREATE TABLE project_inquiry(
    inquiryNo NUMBER NOT NULL, -- 문의내용 일련번호
    projectNo NUMBER NOT NULL, -- 프로젝트 번호 (이 컬럼으로 받는이 추적)
    fromMemberNo NUMBER NOT NULL, --보내는이  (받는이는 프로젝트 만든이 조인하여 참조하기)
    talk VARCHAR2(4000), -- 대화내용
    sentDate DATE DEFAULT SYSDATE NOT NULL, -- 전송일시
    readDate DATE, --읽음 일시
    imageUrl VARCHAR2(200), -- 이미지 첨부 시 이미지 파일 경로 (대화 1개당 1개의 이미지만 첨부가 가능)
    CONSTRAINT PK_PROJECT_PROJECT_INQUIRY_INQUIRYNO PRIMARY KEY (inquiryNo),
    CONSTRAINT FK_PROJECT_PROJECT_INQUIRY_PROJECTNO FOREIGN KEY(projectNo) REFERENCES project(projectNo),
    CONSTRAINT FK_PROJECT_INQUIRY_FROMMEMBERNO FOREIGN KEY(fromMemberNo) REFERENCES member(memberNo)
);

CREATE SEQUENCE project_inquiry_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;

----리워드
--리워드 조건

CREATE TABLE reward(
    rewardNo NUMBER NOT NULL,
    projectNo NUMBER NOT NULL,
    amount NUMBER NOT NULL, -- 구매금액
    rewardTitle VARCHAR2(255) NOT NULL,
    rewardContent VARCHAR2(4000) NOT NULL,
    rewardOption VARCHAR2(255),
    isShipping NUMBER(1) NOT NULL, -- 배송지 필요여부 (0: 배송지 필요없음, 1: 배송지 필요)
    shipAmount NUMBER,
    startShippingDate DATE,
    limitQuantity NUMBER NOT NULL,
    remainQuantity NUMBER,
    CONSTRAINT PK_REWARD_REWARDNO PRIMARY KEY(rewardNo),
    CONSTRAINT FK_REWARD_PROJECTNO FOREIGN KEY(projectNo) REFERENCES project(projectNo)
);


CREATE SEQUENCE reward_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;

-- 고객 구매 리워드 개요

CREATE TABLE courier( --택배사
    courierNo NUMBER NOT NULL,
    courierName VARCHAR2(100) NOT NULL,
    phone VARCHAR2(100) NOT NULL,
    CONSTRAINT PK_COURIER_COURIERNO PRIMARY KEY(courierNo)
);

CREATE SEQUENCE courier_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;


CREATE TABLE shipping_status(
    statusNo NUMBER NOT NULL,
    statusName VARCHAR2(100) NOT NULL,
    CONSTRAINT PK_SHIPPING_STATUS_STATUSNO PRIMARY KEY(statusNo)
);

CREATE SEQUENCE shipping_status_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;

CREATE TABLE member_shipping_location( -- 회원 배송지 주소록
    locationNo NUMBER NOT NULL,
    memberId VARCHAR2(50) NOT NULL,
    zipCode VARCHAR2(50) NOT NULL,
    address1 VARCHAR2(255) NOT NULL,
    address2 VARCHAR2(255) NOT NULL,
    recipient VARCHAR2(255) NOT NULL,
    CONSTRAINT PK_MEMBER_SHIPPING_LOCATION_LOCATIONNO PRIMARY KEY(locationNo),
    CONSTRAINT FK_MEMBER_SHIPPING_LOCATION_MEMBERID FOREIGN KEY(memberId) REFERENCES member_detail(memberId)
);


CREATE SEQUENCE member_shipping_location_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;


CREATE TABLE reward_buy_overview(
    buyNo NUMBER NOT NULL,
    projectNo NUMBER NOT NULL,
    memberNo NUMBER NOT NULL,
    totalAmount NUMBER NOT NULL,
    shipAmount NUMBER NOT NULL,
    finalAmount NUMBER NOT NULL,
    boughtDate DATE DEFAULT SYSDATE NOT NULL,
    CONSTRAINT PK_REWARD_BUY_OVERVIEW_BUYNO PRIMARY KEY(buyNo),
    CONSTRAINT FK_REWARD_BUY_OVERVIEW_PROJECTNO FOREIGN KEY(projectNo) REFERENCES project(projectNo),
    CONSTRAINT FK_REWARD_BUY_OVERVIEW_MEMBERNO FOREIGN KEY(memberNo) REFERENCES member(memberNo)
);

CREATE SEQUENCE reward_buy_overview_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;

CREATE TABLE reward_buy_detail(
    detailNo NUMBER NOT NULL,
    buyNo NUMBER NOT NULL,
    rewardNo NUMBER NOT NULL,
    requestQuantity NUMBER NOT NULL,
    purchasePrice NUMBER NOT NULL,
    optionAnswer VARCHAR2(255), --옵션이 없는 상품이 있을 수도 있음.
    CONSTRAINT PK_REWARD_BUY_DETAIL_DETAILNO PRIMARY KEY(detailNo),
    CONSTRAINT FK_REWARD_BUY_BUYNO FOREIGN KEY(buyNo) REFERENCES reward_buy_overview(buyNo),
    CONSTRAINT FK_REWARD_BUY_REWARDNO FOREIGN KEY(rewardNo) REFERENCES reward(rewardNo)
);


CREATE SEQUENCE reward_buy_detail_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;

--카카오페이 관련 테이블
CREATE TABLE reward_buy_kakao(
    buyNo NUMBER NOT NULL,
    tid VARCHAR2(50) NOT NULL,
    CONSTRAINT PK_REWARD_BUY_KAKAO PRIMARY KEY(buyNo),
    CONSTRAINT FK_REWARD_BUY_KAKAO_BUY_NO FOREIGN KEY(buyNo) REFERENCES reward_buy_overview(buyNo)
);

CREATE TABLE reward_buy_cancel(
    cancelNo NUMBER NOT NULL,
    buyNo NUMBER NOT NULL,
    canceledAmount NUMBER NOT NULL,
    memo VARCHAR2(4000) NOT NULL,
    canceledDate DATE DEFAULT SYSDATE NOT NULL, -- canceled <<< 주의
    CONSTRAINT REWARD_BUY_CANCEL_CANCELNO PRIMARY KEY(cancelNo),
    CONSTRAINT REWARD_BUY_CANCEL_BUYNO FOREIGN KEY(buyNo) REFERENCES reward_buy_overview(buyNo),
);

CREATE SEQUENCE reward_buy_cancel_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;


CREATE TABLE reward_shipping_location(
    buyNo NUMBER NOT NULL,
    phone VARCHAR2(100) NOT NULL,
    zipCode VARCHAR2(50) NOT NULL,
    address1 VARCHAR2(255) NOT NULL,
    address2 VARCHAR2(255) NOT NULL,
    recipient VARCHAR2(255) NOT NULL,
    message VARCHAR2(255), -- 배송 메시지
    courierNo NUMBER, -- 택배사 번호
    invoiceNumber VARCHAR2(100), -- 송장번호 번호
    statusNo NUMBER DEFAULT 0, -- 배송상태번호
    wasReceived NUMBER(1) DEFAULT 0, -- 수취확인 (1:수취확인)
    CONSTRAINT PK_REWARD_SHIPPING_LOCATION_BUYNO PRIMARY KEY(buyNo),
    CONSTRAINT FK_REWARD_SHIPPING_LOCATION_BUYNO FOREIGN KEY(buyNo) REFERENCES reward_buy_overview(buyNo),
    CONSTRAINT FK_REWARD_SHIPPING_LOCATION_COURIERNO FOREIGN KEY(courierNo) REFERENCES courier(courierNo),
    CONSTRAINT FK_REWARD_SHIPPING_LOCATION_STATUSNO FOREIGN KEY(statusNo) REFERENCES shipping_status(statusNo)
);


CREATE SEQUENCE reward_shipping_location_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;

CREATE TABLE member_account( -- 현금계좌
    accountNo NUMBER NOT NULL,
    memberNo NUMBER NOT NULL,
    balance NUMBER DEFAULT 0,
    CONSTRAINT PK_MEMBER_ACCOUNT_ACCOUNTNO PRIMARY KEY(accountNo),
    CONSTRAINT FK_MEMBER_ACCOUNT_MEMBERNO FOREIGN KEY(memberNo) REFERENCES member(memberNo)
);

CREATE SEQUENCE member_account_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;

CREATE TABLE  member_account_charge(
    chargeNo NUMBER NOT NULL,
    accountNo NUMBER NOT NULL,
    memberNo NUMBER NOT NULL,
    chargedDate DATE DEFAULT SYSDATE NOT NULL,
    chargeAmount NUMBER NOT NULL,
    CONSTRAINT PK_MEMBER_ACCOUNT_CHARGE_CHARGENO PRIMARY KEY(chargeNo),
    CONSTRAINT FK_MEMBER_ACCOUNT_CHARGE_ACCOUNTNO FOREIGN KEY(accountNo) REFERENCES member_account(accountNo),
    CONSTRAINT FK_MEMBER_ACCOUNT_CHARGE_MEMBERNO FOREIGN KEY(memberNo) REFERENCES member(memberNo)
);

CREATE SEQUENCE member_account_charge_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;


----
--이은미--
-------------------------------------------------------------------------------------------------------

-- story_category
CREATE TABLE story_category (
    storyCnum NUMBER NOT NULL PRIMARY KEY,
    stroyCname VARCHAR2(100) NOT NULL
);

-- story_board
CREATE TABLE story_board(
    storyNum NUMBER NOT NULL PRIMARY KEY,
    memberId VARCHAR2(50) NOT NULL,
    storyCnum NUMBER NOT NULL,
    storyContent VARCHAR2(3000) NOT NULL,
    storyCreated DATE DEFAULT SYSDATE,
    storyComment VARCHAR2(3000) NOT NULL,
    CONSTRAINT fk_story_board_memberId FOREIGN KEY(memberId) REFERENCES member_detail(memberId),
    CONSTRAINT fk_story_board_storyCnum FOREIGN KEY(storyCnum) REFERENCES story_category(storyCnum)
);

-- story_comment
CREATE TABLE story_comment (
    commentNum NUMBER NOT NULL PRIMARY KEY,
    storyCnum NUMBER NOT NULL,
    memberId VARCHAR2(50) NOT NULL,
    commentContent VARCHAR2(3000) NOT NULL,
    commentCreated DATE DEFAULT SYSDATE,
    CONSTRAINT fk_story_comment_memberId FOREIGN KEY(memberId) REFERENCES member_detail(memberId),
    CONSTRAINT fk_story_comment_storyCnum FOREIGN KEY(storyCnum) REFERENCES story_category(storyCnum)
);

-- story_like
CREATE TABLE story_like (
    storyNum NUMBER NOT NULL,
    memberId   VARCHAR2(50) NOT NULL,
    PRIMARY  KEY(storyNum, memberId),
    FOREIGN  KEY(storyNum) REFERENCES story_board(storyNum) ON DELETE CASCADE,
    FOREIGN  KEY(memberId) REFERENCES member_detail(memberId) ON DELETE CASCADE
);

-- story seq
CREATE SEQUENCE story_board_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;

CREATE SEQUENCE story_category_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;
   
CREATE SEQUENCE story_comment_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;   

-- cast_category
CREATE TABLE cast_category (
    castCnum NUMBER NOT NULL PRIMARY KEY,
    castCname VARCHAR2(100) NOT NULL
);

-- cast_board
CREATE TABLE cast_board(
    castNum NUMBER NOT NULL PRIMARY KEY,
    memberNo NUMBER NOT NULL,
    castCnum NUMBER NOT NULL,
    castTitle VARCHAR2(500) NOT NULL,
    castContent VARCHAR2(3000) NOT NULL,
    castCreated DATE DEFAULT SYSDATE,
    CONSTRAINT fk_cast_board_memberNo FOREIGN KEY(memberNo) REFERENCES member(memberNo),
    CONSTRAINT fk_cast_board_castCnum FOREIGN KEY(castCnum) REFERENCES cast_category(castCnum)
);

-- cast_like
CREATE TABLE cast_like (
    castNum NUMBER NOT NULL,
    memberId   VARCHAR2(50) NOT NULL,
    PRIMARY  KEY(castNum, memberId),
    FOREIGN  KEY(castNum) REFERENCES cast_board(castNum) ON DELETE CASCADE,
    FOREIGN  KEY(memberId) REFERENCES member_detail(memberId) ON DELETE CASCADE
);

-- cast seq
CREATE SEQUENCE cast_board_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;
   
CREATE SEQUENCE cast_category_seq
   INCREMENT BY 1
   START WITH 1
   NOMAXVALUE
   NOCYCLE
   NOCACHE;


   --- 한준익

CREATE TABLE school_category (
    sccaNo NUMBER PRIMARY KEY,
    sccaName VARCHAR2(50) NOT NULL 
);
   
CREATE TABLE school (
    scNo NUMBER PRIMARY KEY,
    memberId VARCHAR2(50) NOT NULL,
    sccaNo NUMBER,
    scSubject VARCHAR2(100) NOT NULL,
    scContent CLOB NOT NULL,
    scSavefilename VARCHAR2(400),
    scDate DATE DEFAULT SYSDATE,
    FOREIGN KEY (memberId) REFERENCES member_detail (memberId),
    FOREIGN KEY (sccaNo) REFERENCES school_category (sccaNo)
);

CREATE TABLE fao ( -- 이용가이드
    faoNo NUMBER PRIMARY KEY,
    memberNo NUMBER NOT NULL,
    faoSubject VARCHAR2(200) NOT NULL,
    faoContent CLOB NOT NULL,
    faoCreated DATE DEFAULT SYSDATE,
    FOREIGN KEY (memberNo) REFERENCES member (memberNo)
);

CREATE TABLE notice_catagory (
    nocaNo NUMBER PRIMARY key,
    nocaName VARCHAR2(10) NOT NULL
);
    
CREATE TABLE notice (
    noticeNo NUMBER PRIMARY Key,
    memberNo NUMBER NOT NULL,
    nocaNo NUMBER NOT NULL,
    noticeSubject VARCHAR2(50) NOT NULL,
    noticeContent CLOB NOT NULL,
    noticeCount NUMBER(10),
    noticeCreated DATE DEFAULT SYSDATE,
    noticeFilename VARCHAR2(100),
    noticeSaveFilename VARCHAR2(100),
    FOREIGN key (memberNo) REFERENCES member(memberNo),
    FOREIGN key (nocaNo) REFERENCES notice_catagory (nocaNo)
);

CREATE TABLE funding_mate (
    fmNo NUMBER PRIMARY KEY,
    memberNo NUMBER NOT NULL,
    fmcaNo NUMBER NOT NULL,
    fmSubject VARCHAR2(50) not null,
    fmContent CLOB not null,
    fmCount NUMBER(10),
    fmFilename VARCHAR2(100),
    fmNumber NUMBER(4),
    fmAllNumber NUMBER(4),
    FOREIGN key (memberNo) REFERENCES member_detail (memberNo),
    FOREIGN key (fmcaNo) REFERENCES funding_mate_catagory (fmcaNo)
);

CREATE TABLE funding_mate_catagory (
    fmcaNo NUMBER PRIMARY key,
    fmcaName VARCHAR2(10) NOT NULL
);


CREATE SEQUENCE funding_mate_seq
    INCREMENT BY 1
    START WITH 1
    NOMAXVALUE
    NOCYCLE
    NOCACHE;

CREATE SEQUENCE notice_catagory_seq
    INCREMENT BY 1
    START WITH 1
    NOMAXVALUE
    NOCYCLE
    NOCACHE;
    
CREATE SEQUENCE notice_seq
    INCREMENT BY 1
    START WITH 1
    NOMAXVALUE
    NOCYCLE
    NOCACHE;        

CREATE SEQUENCE fao_seq 
    INCREMENT BY 1
    START WITH 1
    NOMAXVALUE
    NOCYCLE
    NOCACHE;

CREATE SEQUENCE school_seq 
    INCREMENT BY 1
    START WITH 1
    NOMAXVALUE
    NOCYCLE
    NOCACHE;

CREATE SEQUENCE school_category_seq
    INCREMENT BY 1
    START WITH 1
    NOMAXVALUE
    NOCYCLE
    NOCACHE;


--은진 어워즈 테이블
CREATE TABLE awards_group (
    awardsGroupNum NUMBER NOT NULL,
    awardsGroupName VARCHAR2(50) NOT NULL,
    awardsGroupDetail VARCHAR2(200) NOT NULL,
    CONSTRAINT PK_AWARDS_AWARDSGROUPNUM PRIMARY KEY(awardsGroupNum)
);

CREATE TABLE awards_group_detail (
    awardsGroupNum NUMBER NOT NULL,
    categoryNo NUMBER NOT NULL,
    CONSTRAINT FK_AWARDS_GROUP_DETAIL_AWARDSGROUPNUM FOREIGN KEY(awardsGroupNum) REFERENCES awards_group(awardsGroupNum),
    CONSTRAINT FK_AWARDS_GROUP_DETAIL_CATEGORYNO FOREIGN KEY(categoryNo) REFERENCES project_category(categoryNo)
); 

CREATE SEQUENCE awards_group_seq
    INCREMENT BY 1
    START WITH 1
    NOMAXVALUE
    NOCYCLE
    NOCACHE;
    
--주의: member, member_detail 테이블이 비어있을 때 수행해야 하는 구문.
INSERT INTO member(MEMBERNO, AVAILABLE) VALUES(member_seq.NEXTVAL,1);
INSERT INTO member_detail(memberNo, memberId, memberNickname, memberPwd, memberEmail, memberSocial) VALUES(1, 'eydiz', '관리자',' java$!', 'admin@eydiz.com', 0);
INSERT INTO member(MEMBERNO, AVAILABLE) VALUES(member_seq.NEXTVAL,1);
INSERT INTO member_detail(memberNo, memberId, memberNickname, memberPwd, memberEmail, memberSocial) VALUES(2, 'gildong', '홍길동',' java$!', 'gildong@eydiz.com', 0);
INSERT INTO member(MEMBERNO, AVAILABLE) VALUES(member_seq.NEXTVAL,1);
COMMIT;


INSERT INTO project_category(categoryNo, parentCategoryNo, categoryName) VALUES(project_category_seq.NEXTVAL, null, '펀딩');
INSERT INTO project_category(categoryNo, parentCategoryNo, categoryName) VALUES(project_category_seq.NEXTVAL, null, '후원');
INSERT INTO project_category(categoryNo, parentCategoryNo, categoryName) VALUES(project_category_seq.NEXTVAL, 1, '테크·가전');
INSERT INTO project_category(categoryNo, parentCategoryNo, categoryName) VALUES(project_category_seq.NEXTVAL, 1, '패션·잡화');
INSERT INTO project_category(categoryNo, parentCategoryNo, categoryName) VALUES(project_category_seq.NEXTVAL, 1, '뷰티');
INSERT INTO project_category(categoryNo, parentCategoryNo, categoryName) VALUES(project_category_seq.NEXTVAL, 1, '푸드');
INSERT INTO project_category(categoryNo, parentCategoryNo, categoryName) VALUES(project_category_seq.NEXTVAL, 1, '홈리빙');
INSERT INTO project_category(categoryNo, parentCategoryNo, categoryName) VALUES(project_category_seq.NEXTVAL, 1, '디자인소품');
INSERT INTO project_category(categoryNo, parentCategoryNo, categoryName) VALUES(project_category_seq.NEXTVAL, 1, '여행·레저');
INSERT INTO project_category(categoryNo, parentCategoryNo, categoryName) VALUES(project_category_seq.NEXTVAL, 1, '스포츠·모빌리티');
INSERT INTO project_category(categoryNo, parentCategoryNo, categoryName) VALUES(project_category_seq.NEXTVAL, 1, '반려동물');
INSERT INTO project_category(categoryNo, parentCategoryNo, categoryName) VALUES(project_category_seq.NEXTVAL, 1, '모임');
INSERT INTO project_category(categoryNo, parentCategoryNo, categoryName) VALUES(project_category_seq.NEXTVAL, 1, '공연·컬쳐');
INSERT INTO project_category(categoryNo, parentCategoryNo, categoryName) VALUES(project_category_seq.NEXTVAL, 1, '소셜·캠페인');
INSERT INTO project_category(categoryNo, parentCategoryNo, categoryName) VALUES(project_category_seq.NEXTVAL, 1, '교육·키즈');
INSERT INTO project_category(categoryNo, parentCategoryNo, categoryName) VALUES(project_category_seq.NEXTVAL, 1, '게임·취미');
INSERT INTO project_category(categoryNo, parentCategoryNo, categoryName) VALUES(project_category_seq.NEXTVAL, 1, '출판');
COMMIT;
UPDATE project_category SET ImageUrl='/eydiz/resource/images/project/category_donate.png' WHERE categoryNo=2;
UPDATE project_category SET ImageUrl='/eydiz/resource/images/project/category_tech.png' WHERE categoryNo=3;
UPDATE project_category SET ImageUrl='/eydiz/resource/images/project/category_fashion.png' WHERE categoryNo=4;
UPDATE project_category SET ImageUrl='/eydiz/resource/images/project/category_beauty.png' WHERE categoryNo=5;
UPDATE project_category SET ImageUrl='/eydiz/resource/images/project/category_food.png' WHERE categoryNo=6;
UPDATE project_category SET ImageUrl='/eydiz/resource/images/project/category_living.png' WHERE categoryNo=7;
UPDATE project_category SET ImageUrl='/eydiz/resource/images/project/category_design.png' WHERE categoryNo=8;
UPDATE project_category SET ImageUrl='/eydiz/resource/images/project/category_leisure.png' WHERE categoryNo=9;
UPDATE project_category SET ImageUrl='/eydiz/resource/images/project/category_sport.png' WHERE categoryNo=10;
UPDATE project_category SET ImageUrl='/eydiz/resource/images/project/category_pet.png' WHERE categoryNo=11;
UPDATE project_category SET ImageUrl='/eydiz/resource/images/project/category_circle.png' WHERE categoryNo=12;
UPDATE project_category SET ImageUrl='/eydiz/resource/images/project/category_concert.png' WHERE categoryNo=13;
UPDATE project_category SET ImageUrl='/eydiz/resource/images/project/category_social.png' WHERE categoryNo=14;
UPDATE project_category SET ImageUrl='/eydiz/resource/images/project/category_educational.png' WHERE categoryNo=15;
UPDATE project_category SET ImageUrl='/eydiz/resource/images/project/category_game.png' WHERE categoryNo=16;
UPDATE project_category SET ImageUrl='/eydiz/resource/images/project/category_publish.png' WHERE categoryNo=17;
COMMIT;


INSERT INTO project_status(statusNo, statusName) VALUES(0, '작성 중');
INSERT INTO project_status(statusNo, statusName) VALUES(1, '심사 중');
INSERT INTO project_status(statusNo, statusName) VALUES(2, '반려');
INSERT INTO project_status(statusNo, statusName) VALUES(3, '신고 접수');
INSERT INTO project_status(statusNo, statusName) VALUES(4, '신고 반영');
INSERT INTO project_status(statusNo, statusName) VALUES(5, '활성');--승인되어 진행 중인 프로젝트
INSERT INTO project_status(statusNo, statusName) VALUES(6, '종료');--프로젝트 기간이 모두 끝남
COMMIT;

INSERT INTO shipping_status(statusno, statusname) VALUES(0, '결제 완료');
INSERT INTO shipping_status(statusno, statusname) VALUES(1, '배송 준비 중');
INSERT INTO shipping_status(statusno, statusname) VALUES(2, '배송 중');
INSERT INTO shipping_status(statusno, statusname) VALUES(3, '수취 완료');
COMMIT;

--샘플 데이터 준익

--샘플 데이터 은미
INSERT INTO story_category(storyCnum, stroyCname) VALUES(1,'펀딩후기');
INSERT INTO story_category(storyCnum, stroyCname) VALUES(2,'기부후기');
INSERT INTO story_category(storyCnum, stroyCname) VALUES(3,'프로젝트후기');
COMMIT;

--샘플 데이터 은진


--구매내역 초기화 코드
delete from reward_shipping_location;
delete from reward_buy_detail;
delete from reward_buy_kakao;
delete from reward_buy_cancel;
delete from reward_buy_overview;
update reward set remainQuantity=limitQuantity;
commit;

--리워드 구매 시 트리거

CREATE OR REPLACE TRIGGER insertTriggerRewardBuyDetail
AFTER INSERT ON reward_buy_detail
FOR EACH ROW

BEGIN
     UPDATE reward SET remainQuantity = remainQuantity - :NEW.requestQuantity
           WHERE rewardNo = :NEW.rewardNo;  
END;
/

CREATE OR REPLACE TRIGGER updateTriggerRewardBuyDetail
AFTER UPDATE ON reward_buy_detail
FOR EACH ROW
BEGIN
     UPDATE reward SET remainQuantity = remainQuantity + :OLD.requestQuantity - :NEW.requestQuantity
            WHERE rewardNo = :NEW.rewardNo;
END;
/

CREATE OR REPLACE TRIGGER deleteTriggerRewardBuyDetail
AFTER DELETE ON reward_buy_detail
FOR EACH ROW
BEGIN
     UPDATE reward SET remainQuantity = remainQuantity + :OLD.requestQuantity
           WHERE rewardNo = :OLD.rewardNo;
END;
/


-- 취소 트리거
CREATE OR REPLACE TRIGGER insertTriggerRewardCancel
AFTER INSERT ON reward_buy_cancel
FOR EACH ROW

DECLARE
        CURSOR cur_list IS 
            SELECT rewardNo, requestQuantity
            FROM reward_buy_cancel rc
            JOIN reward_buy_overview ro ON ro.buyNo = rc.buyNo
            JOIN reward_buy_detail rd ON rd.buyNo = ro.buyNo
            WHERE cancelNo = :NEW.cancelNo;
        curChoice cur_list%ROWTYPE;
BEGIN
         OPEN cur_list;
        LOOP
            FETCH cur_list INTO curChoice;
            EXIT WHEN cur_list%NOTFOUND;
            --
           UPDATE reward SET remainQuantity = remainQuantity + curChoice.requestQuantity
           WHERE rewardNo = curChoice.rewardNo;  
            --
        END LOOP;
        CLOSE cur_list;
END;
/

CREATE OR REPLACE TRIGGER updateTriggerRewardCancel
AFTER UPDATE ON reward_buy_cancel
FOR EACH ROW
BEGIN
     UPDATE reward SET remainQuantity = remainQuantity + :OLD.requestQuantity - :NEW.requestQuantity
            WHERE rewardNo = :NEW.rewardNo;
END;
/

CREATE OR REPLACE TRIGGER deleteTriggerRewardCancel
AFTER DELETE ON reward_buy_cancel
FOR EACH ROW
BEGIN
     UPDATE reward SET remainQuantity = remainQuantity + :OLD.requestQuantity
           WHERE rewardNo = :OLD.rewardNo;
END;
/


-- 취소 트리거 관련
CREATE OR REPLACE TRIGGER insertTriggerRewardCancel
BEFORE INSERT ON reward_buy_cancel
FOR EACH ROW
    
DECLARE
    vBuyNo NUMBER;
    --vAAAAA NUMBER;
BEGIN
        vBuyNo := :NEW.buyNo;
        DBMS_OUTPUT.PUT_LINE(vbuyNo);
        
        --select count(*) into vAAAAA FROM reward_buy_overview ro 
        --    JOIN reward_buy_detail rd ON rd.buyNo = ro.buyNo
        --    WHERE ro.buyNo = vBuyNo;
        --DBMS_OUTPUT.PUT_LINE(vAAAAA);
                
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

CREATE OR REPLACE TRIGGER deleteTriggerRewardCancel
BEFORE INSERT ON reward_buy_cancel
FOR EACH ROW
    
DECLARE
    vBuyNo NUMBER;
BEGIN
        vBuyNo := :OLD.buyNo;
        DBMS_OUTPUT.PUT_LINE(vbuyNo);
        
        FOR REC IN (
            SELECT rewardNo, rd.requestQuantity
            FROM reward_buy_overview ro
            JOIN reward_buy_detail rd ON rd.buyNo = ro.buyNo
            WHERE ro.buyNo = vBuyNo
        ) LOOP 
            -- 리워드 잔여수량 정보 갱신
           UPDATE reward SET remainQuantity = remainQuantity - REC.requestQuantity
           WHERE rewardNo = REC.rewardNo;  
           INSERT INTO test_log VALUES (test_log_seq.NEXTVAL, REC.requestQuantity, REC.rewardNo);
        END LOOP;
END;
/


--SYS계정에서 프로시저 및 잡 스케줄러 생성 권한 주기
GRANT CREATE ANY JOB TO eydiz;


--잡스케줄러로 프로젝트 마감기간 끝났는지 확인하기
CREATE OR REPLACE PROCEDURE PROJECT_MONITOR_STATUS
IS
BEGIN
    UPDATE project SET statusNo = 6 WHERE projectEndDate - SYSDATE < 0 AND statusNo=5;
    COMMIT;
END;
/

BEGIN
    DBMS_SCHEDULER.CREATE_JOB
    (
    JOB_NAME => 'PROJECT_REFRESHER',
    JOB_TYPE => 'STORED_PROCEDURE',
    JOB_ACTION => 'PROJECT_MONITOR_STATUS',
    REPEAT_INTERVAL => 'FREQ=MINUTELY; INTERVAL =60', --1시간에 1번
    START_DATE => SYSTIMESTAMP,
    COMMENTS => '날짜 갱신 테스트 (개발용 60분간)'
    );
END;
/
COMMIT;


-- 잡 스케줄러 생성 확인
SELECT 
JOB_NAME, 
JOB_TYPE, 
JOB_ACTION, 
REPEAT_INTERVAL, 
ENABLED, 
AUTO_DROP, 
STATE, 
COMMENTS 
FROM 
USER_SCHEDULER_JOBS;

-- 잡 스케줄러 실행
BEGIN
    DBMS_SCHEDULER.ENABLE ('PROJECT_REFRESHER');
END;

-- 잡 실행객체 로그 확인
SELECT * FROM USER_SCHEDULER_JOB_LOG;

-- 참조
-- https://miid.tistory.com/228
-- https://goddaehee.tistory.com/51

-- 잡 삭제

BEGIN
    DBMS_SCHEDULER.DROP_JOB
    (
        JOB_NAME => 'PROJECT_REFRESHER',
        FORCE => FALSE
    );
END;





-- DECLARE
-- X NUMBER;
-- BEGIN
-- SYS.DBMS_JOB.SUBMIT
-- ( job => X
-- ,what => 'PROJECT_MONITOR_STATUS'
-- ,next_date => to_date('13-07-2020 08:35:00','dd/mm/yyyy hh24:mi:ss')
-- ,interval => 'TRUNC(SYSDATE)+30/(60*60*24)'
-- ,no_parse => TRUE
-- );
-- END;
-- /
-- COMMIT; --커밋해야 dba_jobs 테이블에서 조회 가능

-- --잡 스케줄러 지우기
-- select job, what, failures, total_time, last_date, last_sec, next_date, next_sec, interval
-- from dba_jobs order by next_date;
-- --21 101 81 83 82 65
-- EXECUTE dbms_ijob.remove(65);