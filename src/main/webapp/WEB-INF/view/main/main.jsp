<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   String cp = request.getContextPath();
%>

<article class="row">
  <div class="rowFull">
    <h2>슬라이드</h2>
  </div>
</article>
<article class="row">
  <div class="rowInner">
    <h2 class="hidden">전체 분류</h2>
    <ul class="categoryContent">
      <li>
        <div class="categoryItem">
          <a href="#"><span class="circle"></span><span class="text">전체보기</span></a>
        </div>
      </li>
      <li>
        <div class="categoryItem">
          <a href="#"
            ><span class="circle"></span><span class="text">테크&centerdot;가전</span></a
          >
        </div>
      </li>
      <li>
        <div class="categoryItem">
          <a href="#"
            ><span class="circle"></span><span class="text">패션&centerdot;잡화</span></a
          >
        </div>
      </li>
      <li>
        <div class="categoryItem">
          <a href="#"><span class="circle"></span><span class="text">뷰티</span></a>
        </div>
      </li>
      <li>
        <div class="categoryItem">
          <a href="#"><span class="circle"></span><span class="text">푸드</span></a>
        </div>
      </li>
      <li>
        <div class="categoryItem">
          <a href="#"><span class="circle"></span><span class="text">홈리빙</span></a>
        </div>
      </li>
      <li>
        <div class="categoryItem">
          <a href="#"><span class="circle"></span><span class="text">디자인소품</span></a>
        </div>
      </li>
      <li>
        <div class="categoryItem">
          <a href="#"
            ><span class="circle"></span><span class="text">여행&centerdot;레저</span></a
          >
        </div>
      </li>
      <li>
        <div class="categoryItem">
          <a href="#"
            ><span class="circle"></span
            ><span class="text">스포츠&centerdot;모빌리티</span></a
          >
        </div>
      </li>
      <li>
        <div class="categoryItem">
          <a href="#"><span class="circle"></span><span class="text">반려동물</span></a>
        </div>
      </li>
    </ul>
  </div>
</article>
<article class="row">
  <div class="rowInner">
    <div class="titleBar">
      <div class="title"><h2>전체보기</h2></div>
      <div class="option">
        <form name="titleBarForm">
          <input
            type="text"
            name="keyword"
            class="keyword transparent"
            placeholder="검색"
          />
          <a href="#" class="searchButton"><span class="hidden">검색</span></a>
          <select name="sort1">
            <option value="1">전체</option>
            <option value="2">진행 중</option>
            <option value="3">종료된</option>
          </select>
          <select name="sort2">
            <option value="1">추천순</option>
            <option value="2">인기순</option>
            <option value="3">모금액순</option>
            <option value="4">마감 임박순</option>
            <option value="5">최신순</option>
            <option value="6">응원 참여자순</option>
          </select>
        </form>
      </div>
    </div>
    <ul class="gridContent">
      <li class="item">
        <div class="itemInner">
          <div
            class="thumbnail"
            style="
              background-image: url('https://images.pexels.com/photos/995301/pexels-photo-995301.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260');
            "
          >
            <span class="hidden">드럼</span>
          </div>
          <div class="textWrap">
            <div class="subject">
              <a href="#"
                ><span
                  >[1억 앵콜] 드럼? 게임하듯 쉽게 따라하세요, 어느덧 나도 드럼 고수!</span
                ></a
              >
            </div>
            <ul class="desc">
              <li><span class="category">게임&centerdot;취미</span></li>
              <li><span class="name">모플레이</span></li>
            </ul>
            <div class="status">
              <div class="progress">
                <div class="progressBar" style="width: 100%;"></div>
              </div>
              <ul>
                <li>
                  <span class="percent">330%</span>&centerdot;<span class="totalAmount"
                    >338,050,000원</span
                  >
                </li>
                <li><span class="remainDays">9일 남음</span></li>
              </ul>
            </div>
          </div>
        </div>
      </li>
      <li class="item">
        <div class="itemInner">
          <div
            class="thumbnail"
            style="
              background-image: url('https://images.unsplash.com/photo-1517420879524-86d64ac2f339?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1226&q=80');
            "
          >
            <span class="hidden">드럼</span>
          </div>
          <div class="textWrap">
            <div class="subject">
              <a href="#"
                ><span
                  >[글로벌 최초 펀딩] 스마트워치가 3만원대? 가성비 끝판왕! 헬로우
                  솔라</span
                ></a
              >
            </div>
            <ul class="desc">
              <li><span class="category">테크&centerdot;가전</span></li>
              <li><span class="name">주식회사 위즈굿</span></li>
            </ul>
            <div class="status">
              <div class="progress">
                <div class="progressBar" style="width: 30%;"></div>
              </div>
              <ul>
                <li>
                  <span class="percent">30%</span>&centerdot;<span class="totalAmount"
                    >338,050,000원</span
                  >
                </li>
                <li><span class="remainDays">11일 남음</span></li>
              </ul>
            </div>
          </div>
        </div>
      </li>
      <li class="item">
        <div class="itemInner">
          <div
            class="thumbnail"
            style="
              background-image: url('https://images.unsplash.com/photo-1563822249510-04678c78df85?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80');
            "
          >
            <span class="hidden">드럼</span>
          </div>
          <div class="textWrap">
            <div class="subject">
              <a href="#"
                ><span
                  >[누적3억펀딩, 제주귤이 돌아왔다] 여름에도 만나는 상큼시원
                  하우스감귤</span
                ></a
              >
            </div>
            <ul class="desc">
              <li><span class="category">푸드</span></li>
              <li><span class="name">아일랜드박스</span></li>
            </ul>
            <div class="status">
              <div class="progress">
                <div class="progressBar" style="width: 50%;"></div>
              </div>
              <ul>
                <li>
                  <span class="percent">50%</span>&centerdot;<span class="totalAmount"
                    >338,050,000원</span
                  >
                </li>
                <li><span class="remainDays">9일 남음</span></li>
              </ul>
            </div>
          </div>
        </div>
      </li>
      <li class="item">
        <div class="itemInner">
          <div
            class="thumbnail"
            style="
              background-image: url('https://images.pexels.com/photos/995301/pexels-photo-995301.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260');
            "
          >
            <span class="hidden">드럼</span>
          </div>
          <div class="textWrap">
            <div class="subject">
              <a href="#"
                ><span
                  >[1억 앵콜] 드럼? 게임하듯 쉽게 따라하세요, 어느덧 나도 드럼 고수!</span
                ></a
              >
            </div>
            <ul class="desc">
              <li><span class="category">게임&centerdot;취미</span></li>
              <li><span class="name">모플레이</span></li>
            </ul>
            <div class="status">
              <div class="progress">
                <div class="progressBar" style="width: 100%;"></div>
              </div>
              <ul>
                <li>
                  <span class="percent">330%</span>&centerdot;<span class="totalAmount"
                    >338,050,000원</span
                  >
                </li>
                <li><span class="remainDays">9일 남음</span></li>
              </ul>
            </div>
          </div>
        </div>
      </li>
      <li class="item">
        <div class="itemInner">
          <div
            class="thumbnail"
            style="
              background-image: url('https://images.unsplash.com/photo-1517420879524-86d64ac2f339?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1226&q=80');
            "
          >
            <span class="hidden">드럼</span>
          </div>
          <div class="textWrap">
            <div class="subject">
              <a href="#"
                ><span
                  >[글로벌 최초 펀딩] 스마트워치가 3만원대? 가성비 끝판왕! 헬로우
                  솔라</span
                ></a
              >
            </div>
            <ul class="desc">
              <li><span class="category">테크&centerdot;가전</span></li>
              <li><span class="name">주식회사 위즈굿</span></li>
            </ul>
            <div class="status">
              <div class="progress">
                <div class="progressBar" style="width: 30%;"></div>
              </div>
              <ul>
                <li>
                  <span class="percent">30%</span>&centerdot;<span class="totalAmount"
                    >338,050,000원</span
                  >
                </li>
                <li><span class="remainDays">11일 남음</span></li>
              </ul>
            </div>
          </div>
        </div>
      </li>
      <li class="item">
        <div class="itemInner">
          <div
            class="thumbnail"
            style="
              background-image: url('https://images.unsplash.com/photo-1563822249510-04678c78df85?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80');
            "
          >
            <span class="hidden">드럼</span>
          </div>
          <div class="textWrap">
            <div class="subject">
              <a href="#"
                ><span
                  >[누적3억펀딩, 제주귤이 돌아왔다] 여름에도 만나는 상큼시원
                  하우스감귤</span
                ></a
              >
            </div>
            <ul class="desc">
              <li><span class="category">푸드</span></li>
              <li><span class="name">아일랜드박스</span></li>
            </ul>
            <div class="status">
              <div class="progress">
                <div class="progressBar" style="width: 50%;"></div>
              </div>
              <ul>
                <li>
                  <span class="percent">50%</span>&centerdot;<span class="totalAmount"
                    >338,050,000원</span
                  >
                </li>
                <li><span class="remainDays">9일 남음</span></li>
              </ul>
            </div>
          </div>
        </div>
      </li>
    </ul>
  </div>
</article>