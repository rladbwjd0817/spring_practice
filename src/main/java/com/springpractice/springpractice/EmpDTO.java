package com.springpractice.springpractice;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class EmpDTO {
    private int empNo; //사원번호
    private String empName; //사원명
    private String departName; //부서명
    private int salary; //급여
    private String rank; //직급

}
