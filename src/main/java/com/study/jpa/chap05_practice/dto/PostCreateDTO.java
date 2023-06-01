package com.study.jpa.chap05_practice.dto;

import com.study.jpa.chap05_practice.entity.Post;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PostCreateDTO {

    @NotBlank
    @Size(min=2, max=5)
    private String writer;

    @NotBlank
    @Size(min=1, max=10)
    private String title;
    private String content;

    private List<String> hashTags;


    //dto를 entity로 변환하는 메소드 -hashtag는 db에 조회용도로만 사용하기 때문에 쓰이지 않음
    public Post toEntity(){
        return Post.builder()
                .writer(this.writer)
                .Content(this.content)
                .title(this.title)
                .build();
    }


}
