package com.study.jpa.chap04_relation.repository;

import com.study.jpa.chap04_relation.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT DISTINCT d FROM Department d JOIN FETCH d.employees")
                          //d - 별칭, Department: entity 클래스 별칭? (JPQL 쿼리문)
     List<Department> findAllIncludeEmployees(); //모든 사원을 조회할 것이다


}
