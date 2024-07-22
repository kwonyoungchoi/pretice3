package com.example.pretice3.service;

import com.example.pretice3.dto.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void BoardInsertTest() {
        //데이터 값 준비
        BoardDTO boardDTO = BoardDTO.builder()
                .subject("새로운 게시글")
                .content("새로운 게시글 내용")
                .build();
        System.out.println(boardService.createBoard(boardDTO));
    }

    @Test
    public void BoasrdUpdateTest() {
        BoardDTO boardDTO = BoardDTO.builder()
                .id(3)
                .subject("수정된 제목")
                .content("수정된 내용")
                .build();
        System.out.println(boardService.updateBoard(boardDTO));
    }

    @Test
    public void BoardListTest() {
        List<BoardDTO> list = boardService.getAllBoards();
        System.out.println(list);
    }

    @Test
    public void listOneTest() {
        BoardDTO data = boardService.getBoardByid(2);
        System.out.println(data.toString());
    }

    @Test
    public void deleteTest() {
        boardService.deleteBoard(2);
    }

}