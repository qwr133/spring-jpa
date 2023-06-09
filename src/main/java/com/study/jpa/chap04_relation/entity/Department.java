package com.study.jpa.chap04_relation.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter @Getter
@ToString(exclude = {"employees"})
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode @Builder
@Entity
@Table(name = "tbl_dept")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Long id;

    @Column(name = "dept_name", nullable = false)
    private String name;


    //양방향 매핑에서는 상대방 엔터티의 갱신에 관여할 수 없다
    //단순히 읽기전용(조회)으로만 사용해야함
    //mappedBy는 상대방 엔터티의 조인되는 필드명을 써준다
    //필수가 아니기 때문에 ~~ 6/1 9시 수업 들어서 메모
    @OneToMany(mappedBy = "department") //employee에 있는 department 부분작성 D x
    private List<Employee> employees = new ArrayList<>();
}
