package com.study.jpa.chap05_practice.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.jpa.chap05_practice.entity.Post;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Setter @Getter
@ToString @AllArgsConstructor
@NoArgsConstructor @EqualsAndHashCode
@Builder
public class PostDetailResponseDTO {

    private String author; //클라이언트가 writer를 author로 달라고하면 이런식으로 주면 됨
                            //왜? dto는 화면공유, entity는 db공유기 떄문에 상관X
    private String title;
    private String content;
    private List<String> hashTags; //문자열 배열로 해시태그를 주세요~

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime regDate;

    //엔터티를 DTO로 변환하는 생성자
    public PostDetailResponseDTO(Post post){
        this.author=post.getWriter();
        this.title=post.getTitle();
        this.content = post.getContent();
        this.regDate = post.getCreateDate();
        this.hashTags= post.getHashTags()
                            .stream()
                            .map(ht->ht.getTagName())
                            .collect(Collectors.toList());
    }

}
