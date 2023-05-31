package com.study.jpa.chap05_practice.entity;


import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString(exclude = {"post"})
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_hash_tag")
public class HashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_no")
    private Long id;

    private String tagName; //해시태그 이름

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_no") //상대방 pk 이름과 같게 (똑같이 안해도도 되긴함)
    private Post post;

}
