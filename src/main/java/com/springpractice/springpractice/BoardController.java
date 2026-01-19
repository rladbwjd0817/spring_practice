package com.springpractice.springpractice;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private List<BoardDTO> boardList;

    public BoardController(){
        boardList = new ArrayList<>();

        boardList.add(new BoardDTO(1,"최자바", 7));
        boardList.add(new BoardDTO(2,"이유바", 4));
        boardList.add(new BoardDTO(3,"최이영", 1));
        boardList.add(new BoardDTO(4,"김자바", 5));
        boardList.add(new BoardDTO(5,"박수정", 2));
    }

    //전체 게시글 목록 조회
//    localhost:8080/boards
    @GetMapping("")
    public List<BoardDTO> getBoardList(){
        System.out.println("게시글을 조회합니다.");
        return boardList;
    }

//    특정 작성자의 게시글만 조회하는 API
//    (GET) localhost:8080/boards/writer/{writer}
    @GetMapping("/writer/{writer}")
    public BoardDTO getBoard(@PathVariable("writer") String writer){
        BoardDTO result = null;

        for (BoardDTO e : boardList){
            if (e.getWriter().equals(writer)){
                result = e;
            }
        }
        System.out.println(result + "님 게시글을 조회합니다.");
        return result;
    }

//    조회수를 증가시키는 API
//    localhost:8080/boards/{boardNum}/view
    @PutMapping("/{boardNum}")
    public BoardDTO setReadCnt(@PathVariable("boardNum") int boardNum){
        BoardDTO result = null;

        for (BoardDTO e : boardList){
            if (e.getBoardNum() == boardNum){
                e.setReadCnt(e.getReadCnt() + 1);
                result = e;
            }
        }
        return result;
    }

//    조회수가 특정 숫자 이상인 게시글만 조회하는 API
//    localhost:8080/boards/readCnt/{minCount}
    @GetMapping("/readCnt/{minCount}")
    public List<BoardDTO> numBoard(@PathVariable("minCount") int minCount){
        List<BoardDTO>result = new ArrayList<>();

        for (BoardDTO e : boardList){
            if (e.getReadCnt() >= minCount){
                result.add(e);
            }
        }
        result. sort((a, b) -> b.getReadCnt() - a.getReadCnt());

        System.out.println("조회수가" + minCount + "이상인 게시글 조회합니다.");
        return result;
    }

}
