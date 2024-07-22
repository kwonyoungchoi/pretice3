package com.example.pretice3.Entity;

import com.example.pretice3.dto.BoardDTO;
import com.example.pretice3.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BoardEntityTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void BoardInsertTest() {
        BoardEntity boardEntity = BoardEntity.builder()
                .subject("게시물 제목")
                .content("게시물 내용")
                .build();
        System.out.println(boardRepository.save(boardEntity));
    }

    @Test
    public void BoardUpdateTest() {
        BoardEntity boardEntity = BoardEntity.builder()
                .id(1)
                .subject("수정 게시물")
                .content("내용 수정")
                .build();
        System.out.println(boardRepository.save(boardEntity));
    }

    @Test
    public void BoardListTest(){
        List<BoardEntity> list = boardRepository.findAll();
        System.out.println(list);
    }

    @Test
    public void listOneTest(){
        Optional<BoardEntity> list = boardRepository.findById(1);
        System.out.println(list);
    }

    @Test
    public void deleteTest(){
        boardRepository.deleteById(1);;
    }

    @Test
    public void testCRUDOperations() {
        //등록
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setSubject("게시물 제목");
        boardEntity.setContent("게시물 내용");
        boardEntity = boardRepository.save(boardEntity);
        System.out.println("등록 자료 : " + boardEntity);

        //조회
        Optional<BoardEntity> savedBoard = boardRepository.findById(boardEntity.getId());
        assertTrue(savedBoard.isPresent());
        assertEquals("게시물 제목", savedBoard.get().getSubject());
        System.out.println("자료 조회 : " + savedBoard.get());

        //수정
        savedBoard.get().setSubject("게시물 수정");
        boardRepository.save(savedBoard.get());
        System.out.println("수정한 자료 :  " + savedBoard.get());

        //수정 검증
        Optional<BoardEntity> updatedBoard = boardRepository.findById(boardEntity.getId());
        assertTrue(updatedBoard.isPresent());
        assertEquals("게시물 수정" ,updatedBoard.get().getSubject());
        System.out.println("수정검증 : " + updatedBoard.get());

        //삭제
        boardRepository.deleteById(boardEntity.getId());
        System.out.println("삭제할 자료 : " +  boardEntity.getId());

        //삭제 검증
        Optional<BoardEntity> deletedBoard = boardRepository.findById(boardEntity.getId());
        assertTrue(deletedBoard.isEmpty());
        System.out.println("삭제검증 조회번호" + boardEntity.getId() + " 없음");
    }

    @Test
    public void testDTOConversion() {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setSubject("DTOConversionTest");
        boardEntity = boardRepository.save(boardEntity);

        BoardDTO boardDTO = BoardDTO.builder()
                .id(boardEntity.getId())
                .subject(boardEntity.getSubject())
                .build();

        assertEquals(boardEntity.getId(), boardDTO.getId());
        assertEquals(boardEntity.getContent(), boardDTO.getContent());

        System.out.println("원본 BoardEntity : " + boardEntity);
        System.out.println("원본 BoardDTO : " + boardDTO);

    }

}