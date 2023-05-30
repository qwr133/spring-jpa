package com.study.jpa.chap02_querymethod.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter //실무적 측면에서 setter는 신중히 만들 것 - setter에 의해서 객체 불변성이 깨지기 때문
@Getter @ToString
@EqualsAndHashCode(of={"id"}) //id를 비교해서 id가 있으면 이름이 같은 것으로 판단함
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_student")
public class Student {

    @Id
    @Column(name= "stu_id")
    @GeneratedValue(generator = "uid") //하단의 name 값 일치 시켜주기
    @GenericGenerator(strategy = "uuid", name = "uid") //uuid- 파일명 랜덤으로 값 붙여지는 것, name은 내 마음대로
    private String id;

    @Column(name = "stu_name", nullable = false)
    private String  name;

    private String city;
    private String major;




}
