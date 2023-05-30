package com.study.jpa.chap02_querymethod.repository;

import com.study.jpa.chap02_querymethod.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.*;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {

    List<Student> findByName(String name); //camel case, class 필드명(컬럼x) 으로 꼭 작성 필요!!
    //이름이 유니크가 아니고 동명이인이 있을 수 있기 때문에 list로 받기

    List<Student> findByCityAndMajor(String city, String major);

    List<Student> findByMajorContaining(String major);

    // 네이티브 쿼리 사용
    @Query(value = "SELECT * FROM tbl_student WHERE stu_name = :nm", nativeQuery = true)
    Student findNameWithSQL(@Param("nm") String name);


    //JPQL
    // select 별칭 from 엔터티 클래스명 as 별칭
    // where 별칭.필드명=?

    //native-sql: SELECT * FROM tbl_student -- table명
    //            where stu_name = ?

    //spql : SELECT st FROM Student as st --class 명이 나옴!!
    //       where st.name =?

    //도시 이름으로 학생 조회
//    @Query("SELECT * FROM tbl_student WHERE city = :city") :city를 사용하려면 String 부분에 param 주입 꼭 필요 -line 42
//    @Query(value = "SELECT * FROM tbl_student WHERE city = ?1", nativeQuery = true) 위랑 같음
    @Query("SELECT s FROM Student s WHERE s.city= ?1") //nativeQuery 생략가능 city 부를때도 s.city로 해야함
    Student getByCityWithJPQL(String city);

//    @Query("SELECT st FROM Student st WHERE st.name LIKE %?1%")
    @Query("SELECT st FROM Student st WHERE st.name LIKE %:nm%")
    List<Student> searchByNamesWithJPQL(@Param("nm") String name);

    //JPQL로 수정 삭제 쿼리 쓰리
    @Modifying //조회가 아닐경우 무조건 붙여야한다
    @Query("DELETE FROM Student s WHERE s.name = ?1")
    void deleteByNameWithJQPL(String name);


}
