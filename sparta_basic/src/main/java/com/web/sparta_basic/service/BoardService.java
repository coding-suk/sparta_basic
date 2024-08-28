package com.web.sparta_basic.service;

import com.web.sparta_basic.dto.*;
import com.web.sparta_basic.entity.Board;
import com.web.sparta_basic.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.BorderUIResource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;


    @Transactional
    public BoardSaveResponseDto saveBoard(BoardSaveRequestDto boardSaveRequestDto) {
        Board newBoard = new Board(boardSaveRequestDto.getTitle(), boardSaveRequestDto.getContents());

        Board savedBoard = boardRepository.save(newBoard);

        return new BoardSaveResponseDto(
                savedBoard.getId(),
                savedBoard.getTitle(),
                savedBoard.getContents()
        );
    }

    public List<BoardSimpleResponseDto> getBoards() {
        List<Board> boardList = boardRepository.findAll();

        List<BoardSimpleResponseDto> dtoList  = new ArrayList<>();

        for (Board board : boardList) {
            BoardSimpleResponseDto dto = new BoardSimpleResponseDto(board.getTitle());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Transactional
    public BoardDetailresponseDto getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NullPointerException(("그 보드 없다")));

        return new BoardDetailresponseDto(
                board.getId(),
                board.getTitle(),
                board.getContents()
        );
    }

    @Transactional
    public BoardUpdateTitleResponseDto updateBoardTitle(
            Long boardId, BoardUpdateTitleRequestDto boardUpdateTitleRequestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NullPointerException(("그 보드 없다")));

         board.updateTitle(boardUpdateTitleRequestDto.getTitle());

         return new BoardUpdateTitleResponseDto(
                 board.getId(),
                 board.getTitle(),
                 board.getContents()
         );
    }

    @Transactional
    public BoardUpdateContentsResponseDto updateBoardContents(
            Long boardId, BoardUpdateContentsRequestDto boardUpdateContentsRequestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NullPointerException(("그 보드 없다")));

        board.updateContents(board.getContents());
        return new BoardUpdateContentsResponseDto(
                 board.getId(),
                board.getTitle(),
                board.getContents());
    }

    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }
}
