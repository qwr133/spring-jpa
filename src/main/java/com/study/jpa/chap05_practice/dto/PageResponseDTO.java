package com.study.jpa.chap05_practice.dto;

import com.study.jpa.chap05_practice.entity.Post;
import lombok.*;
import org.springframework.data.domain.Page;

@Getter @Setter @ToString
public class PageResponseDTO<T> {

private int startPage;
private int endPage;
private int currentPage;

private boolean prev;
private boolean next;

private int totalCount;

//한 페이지에 배치할 페이지 수(1~10 // 11~20)
    private static final int PAGE_COUNT =10;
    public PageResponseDTO(Page<T> pageData) {
        this.totalCount = (int) pageData.getTotalElements();
        this.currentPage = pageData.getPageable().getPageNumber() + 1; //db에 -1로 했으니 화면에 +1로 해줘야함
        this.endPage = (int) (Math.ceil((double) currentPage / PAGE_COUNT) * PAGE_COUNT);
        this.startPage = endPage - PAGE_COUNT + 1;

        int realEnd = pageData.getTotalPages();

        if (realEnd < this.endPage) this.endPage = realEnd;

        this.prev = startPage > 1;
        this.next = endPage < realEnd;
    }
}
