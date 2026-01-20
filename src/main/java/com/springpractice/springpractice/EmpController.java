package com.springpractice.springpractice;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/emps")
public class EmpController {
    //사원 목록 객체 선언
    List<EmpDTO> empDTOList;
    Scanner sc;

    //사원 목록 객체 생성
    public EmpController(){
        empDTOList = new ArrayList<>();
        sc = new Scanner(System.in);

        empDTOList.add(new EmpDTO(1, "김남준", "개발부", 8000, "부장"));
        empDTOList.add(new EmpDTO(2, "김석진", "영업부", 7000, "과장"));
        empDTOList.add(new EmpDTO(3, "민윤기", "개발부", 6000, "대리"));
        empDTOList.add(new EmpDTO(4, "정호석", "인사부", 5000, "과장"));
        empDTOList.add(new EmpDTO(5, "박지민", "영업부", 4000, "부장"));
        empDTOList.add(new EmpDTO(6, "김태형", "홍보부", 3000, "사원"));
        empDTOList.add(new EmpDTO(7, "전정국", "홍보부", 2000, "사원"));

    }

//    모든 사원 목록 조회 API
    //url : (GET) http://localhost:8080/emps
    @GetMapping("")
    public List<EmpDTO> getEmpDTOList(){
        System.out.println("사원 목록을 조회합니다.");
        return empDTOList;
    }

    //해당 부서의 사원 수를 조회 API
    //url : (GET) http://localhost:8080/emps/departName/{departName}
    @GetMapping("/departName/{departName}")
    public int getDepartName(@PathVariable("departName") String departName){ //영업부의 사원 수는?
        //해당 부서의 사원 수를 저장하는 변수
        int cnt = 0;

        for (EmpDTO e : empDTOList){
            if (e.getDepartName().equals(departName)){
                cnt++;
            }
        }
        System.out.println(departName + "부서의 사원은 총 " + cnt + "명 입니다.");
        return cnt;
    }

    //사번이 일치하는 사원의 급여 수정 API
    //url : (PUT) http://localhost:8080/emps/{empNo}/salary
    @PutMapping("/{empNo}/salary")
    public EmpDTO setSalary(@PathVariable("empNo") int empNo){
        int setSal = sc.nextInt(); //키보드로 입력 받은 수정된 급여
        EmpDTO result = null; // 사원의 수정된 급여 저장공간

        for (EmpDTO e : empDTOList){
            if (e.getEmpNo() == empNo){
                e.setSalary(e.getSalary() + sc.nextInt());
                result = e;
            }
        }
        return result;
    }

    //해당 부서의 모든 사원 정보 조회 API
    //url: (GET) http://localhost:8080/emps/departName/{departName}/list
    @GetMapping("/departName/{departName}/list")
    public List<EmpDTO> getDeptEmpList(@PathVariable("departName") String departName){ //홍보부 모든 사원 정보 조회해
        List<EmpDTO>getDeptEmpList = new ArrayList<>();

        for (EmpDTO e: empDTOList){
            if (e.getDepartName().equals(departName)){
                getDeptEmpList.add(e);
            }
        }
        return getDeptEmpList;
    }

    //사원 등록 API
    //url : (POST)http://localhost:8080/emps
    @PostMapping("")
    public void postEmp(@RequestBody EmpDTO empDTO){
        System.out.println(empDTO);
        empDTOList.add(empDTO);
        System.out.println("등록되었습니다");
    }





}
