package com.study.jpa.chap05_practice.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@ToString(exclude = {"hashtags"})
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_post")

//db에 맞춰서 저장해야함
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_no")
    private Long id;

    @Column(nullable = false)
    private String writer; //작성자

    @Column(nullable = false)
    private String title; //제목
    private String Content; //내용

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createDate; //작성시간

    @UpdateTimestamp
    private LocalDateTime updateDate; //수정시간

    //db에 넣는 용도가 아닌 조회용도
    @OneToMany(mappedBy = "post")
    @Builder.Default //빌더의 기본값 추가
                    //아무리 빌더를 넣어도 초기화가 되지 않음으로 이를 방지하기 위해서 Builder.Default 어노테이션 삽입 필수
    private List<HashTag> hashTags = new ArrayList<>();

    //500에러 발생후 추가작성
    //양방향 매핑에서 리스트쪽에 데이터를 추가하는 편의 메서드 생성
    public void addHashTag(HashTag hashTag){
        hashTags.add(hashTag);
        if(this!=hashTag.getPost()){
            hashTag.setPost(this);
        }
    }
}

