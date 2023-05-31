package com.study.jpa.chap04_relation.repository;

import com.study.jpa.chap04_relation.entity.Department;
import com.study.jpa.chap04_relation.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
@Transactional
class employeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @BeforeEach
    void bulkInsert(){
        Department d1 = Department.builder()
                .name("영업부")
                .build();
        Department d2 = Department.builder()
                .name("개발부")
                .build();

        departmentRepository.save(d1);
        departmentRepository.save(d2);

        Employee e1 = Employee.builder()
                .name("라이옹")
                .department(d1) //entity자체를 넣기
                .build();
        Employee e2 = Employee.builder()
                .name("핀")
                .department(d1) //entity자체를 넣기
                .build();
        Employee e3 = Employee.builder()
                .name("제이크")
                .department(d1) //entity자체를 넣기
                .build();
        Employee e4 = Employee.builder()
                .name("크롱")
                .department(d1) //entity자체를 넣기
                .build();

        employeeRepository.save(e1);
        employeeRepository.save(e2);
        employeeRepository.save(e3);
        employeeRepository.save(e4);

    }

    @Test
    @DisplayName("특정사원의 정보 조회")
 void testFindOne(){
        //given
        Long id = 2L;

        //when
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("사원이 없음")
                );

        //then
        System.out.println("\n\n\n");
        System.out.println("employee = " + employee);
        System.out.println("\n\n\n");

        assertEquals("라이옹", employee.getName());
    }

    @Test
    @DisplayName("N+1 문제 발생 예시")
    void testNPlus1Ex() {
        //given
        List<Department> departments = departmentRepository.findAll();//전체부서조회

        //when
    departments.forEach(dept ->{
        System.out.println("\n\n======사원리스트======");

        List<Employee> employees = dept.getEmployees();
        System.out.println(employees);

        System.out.println("\n\n");

    });
        //then
    }


    @Test
    @DisplayName("N+1 문제 해결 예시")
    void testNPlus1Solution() {
        //given
        List<Department> departments = departmentRepository.findAllIncludeEmployees();//전체부서조회

        //when
        departments.forEach(dept ->{
            System.out.println("\n\n======사원리스트======");

            List<Employee> employees = dept.getEmployees();
            System.out.println(employees);

            System.out.println("\n\n");

        });
        //then
    }


}
