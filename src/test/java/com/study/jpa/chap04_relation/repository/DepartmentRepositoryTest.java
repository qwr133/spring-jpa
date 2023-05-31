package com.study.jpa.chap04_relation.repository;

import com.study.jpa.chap04_relation.entity.Department;
import com.study.jpa.chap04_relation.entity.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    @DisplayName("특정 부서를 조회하면 해당 부서원들도 함께 조회되어야한다")
    void testFindDept(){
        //given
        Long id = 2L;
        //when
        Department department = departmentRepository.findById(id).orElseThrow();


        //then
        System.out.println("\n\n\n");
        System.out.println("department" + department);
        System.out.println("\n\n\n");
    }


    @Test
    @DisplayName("Lazy로딩과 Eager로딩의 차이")
                //Eager 로딩: 연관된 엔터티를 부모엔티티가 로드되는 시점에 함꼐 로드하는 방식  -- 5장부분
    void testLazyAndEAger(){
        //3번사원의 정보를 조회하고 싶으면 굳이 부서정보는 필요없다 - join 필요없음
        //given
        Long id = 3L;
        //when
        Employee employee = employeeRepository.findById(id).orElseThrow();
        //then
        System.out.println("\n\n\n");
        System.out.println("employee = " + employee);
        System.out.println("\n\n\n");

    }


    @Test
    @DisplayName("양방향 연관관계에서 연관데이터의 수정")
    void testChangeDept() {
        //3번사원의 부서를 2번부서에서 1번부서로 변경해야한다
        //given
        //타겟사원 찾기
        Employee foundEmp = employeeRepository.findById(3L).orElseThrow();
        //새로운 사원 찾기
        Department newDept = departmentRepository.findById(1L).orElseThrow();

//        타겟사원에서 새로운사원의 부서를 변경하기
        foundEmp.setDepartment(newDept);
        //다시 저장
        employeeRepository.save(foundEmp);
        //when

        //1번부서 정보를 조회해서 모든 사원을 보겠다
        Department foundDept = departmentRepository.findById(1L)
                .orElseThrow();

        //then

        System.out.println("\n\n\n");
        foundDept.getEmployees().forEach(System.out::println);
        System.out.println("\n\n\n");
    }
}