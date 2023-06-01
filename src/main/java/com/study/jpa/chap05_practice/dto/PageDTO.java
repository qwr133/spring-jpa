package com.study.jpa.chap05_practice.dto;


import lombok.*;

@Getter @Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class PageDTO {
    private int page;
    private int size;

    public PageDTO(){
        this.page=1;
        this.size=10;
    }
}
