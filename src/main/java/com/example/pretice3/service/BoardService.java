package com.example.pretice3.service;

import com.example.pretice3.Entity.BoardEntity;
import com.example.pretice3.dto.BoardDTO;
import com.example.pretice3.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    //등록
    public BoardDTO createBoard(BoardDTO boardDTO) {
        BoardEntity boardEntity = modelMapper.map(boardDTO, BoardEntity.class);
        BoardEntity savedBoard = boardRepository.save(boardEntity);

        return modelMapper.map(savedBoard, BoardDTO.class);
    }

    //조회(모두)
    public List<BoardDTO> getAllBoards() {
        List<BoardEntity> boardEntities = boardRepository.findAll();

        return Arrays.asList(modelMapper.map(boardEntities, BoardDTO[].class));
    }

    //조회(상세)
    public BoardDTO getBoardByid(Integer boardId) {
        Optional<BoardEntity> optionalBoard = boardRepository.findById(boardId);

        return modelMapper.map(optionalBoard, BoardDTO.class);
    }

    public BoardDTO updateBoard(BoardDTO updateBoardDTO) {
        Optional<BoardEntity> optionalBoard = boardRepository.findById(updateBoardDTO.getId());

        if (optionalBoard.isPresent()) {
            BoardEntity existingBoard = optionalBoard.get();

            existingBoard.setSubject(updateBoardDTO.getSubject());
            existingBoard.setContent(updateBoardDTO.getContent());
            BoardEntity updatedBoard = boardRepository.save(existingBoard);

            return modelMapper.map(updatedBoard, BoardDTO.class);
        }
        return null;
    }

    public void deleteBoard(Integer boardId) {
        boardRepository.deleteById(boardId);
    }

}
