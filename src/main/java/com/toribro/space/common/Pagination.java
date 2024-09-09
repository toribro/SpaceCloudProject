package com.toribro.space.common;


public class Pagination {
    /*
     *
     * int listCount; //현재 총 게시글수
     * int currentPage;//현재 페이지(사용자가 요청한페이지
     * int pageLimit; //페이지 하단에 보여질 페이징바의 개수
     * int boardLimit // 한페이지내에 보여질 게시글 최대갯수
     * 값을 전달받아 maxPage,startPage ,endPage값을
     * 구하고 하나의 PageInfo객체로 만들어서 반환
     *
     * */



    public static PageInfo getPageInfo(int listCount,int currentPage,int pageLimit,int boardLimit) {
        int maxPage=(int)Math.ceil((double)listCount/boardLimit);//가장 마지막 페이지(총 페이지 수)
        int startPage=(currentPage-1)/pageLimit*pageLimit+1;//페이징바의 시작
        int endPage=startPage+pageLimit-1;//페이장바의 끝수

        endPage=endPage>maxPage? maxPage :endPage;
        PageInfo pi = new PageInfo(listCount,currentPage,pageLimit,boardLimit,
                maxPage,startPage,endPage);

        return pi;
    }
}