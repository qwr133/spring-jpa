package com.study.jpa.chap04_relation.entity;

import lombok.*;

import javax.persistence.*;

@Setter @Getter
//jpa연관관계 매핑에서는 연관관계 데이터는 ToString에서 제외해야한다.
@ToString(exclude = {"department"})
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode @Builder
@Entity
@Table(name = "tbl_emp")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private long id;

    @Column(name = "emp_name", nullable = false)
    private String name;


    @ManyToOne(fetch = FetchType.LAZY) //사원이 나고 부서가 M이니까
    @JoinColumn(name = "dept_id")
    private Department department; //단방향 양방관계



}
