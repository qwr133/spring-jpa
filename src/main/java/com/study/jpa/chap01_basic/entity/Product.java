package com.study.jpa.chap01_basic.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter @Getter @ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "tbl_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "prod_id") //자바에선 prodId, DB에선 prod_id로
    private long prodId;

    @Column(name= "prod_nm", nullable = false, unique = true, length = 30) //null,unique 제약조건, varchar=30
    private String prodName;

    @Builder.Default
    private int price =0;

    @Enumerated(EnumType.STRING) //db에 저장될때 0,1,2로 저장되는게 아니라 string type으로 저장됨
    private Category category;

    @CreationTimestamp //생성 시 default currenttime 지정됨
    @Column(updatable = false) //수정 불가능하게
    private LocalDateTime createdDate;

    @UpdateTimestamp // 업데이트시 시간이 자동으로 바뀜
    private LocalDateTime updatedDate;

    public enum Category{
        FOOD, FASHION, ELECTRONIC
    }



}
