package com.springpractice.springpractice;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class BoardDTO {
    private int boardNum;
    private String writer;
    private int readCnt;
}
