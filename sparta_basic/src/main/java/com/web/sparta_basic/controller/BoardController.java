package com.web.sparta_basic.controller;

import com.web.sparta_basic.dto.*;
import com.web.sparta_basic.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/boards")
    public ResponseEntity<BoardSaveResponseDto> saveResponseDto(@RequestBody BoardSaveRequestDto boardSaveRequestDto) {
        return ResponseEntity.ok(boardService.saveBoard(boardSaveRequestDto));
    }

    @GetMapping("/boards")
    public ResponseEntity<List<BoardSimpleResponseDto>> getBoard() {
        return ResponseEntity.ok(boardService.getBoards());
    }

    @GetMapping("/boards/{boardId}")
    public ResponseEntity<BoardDetailresponseDto> getBoard(@PathVariable Long boardId) {
        return ResponseEntity.ok(boardService.getBoard(boardId));
    }

    @PutMapping("/board/{boardId}/title")
    public ResponseEntity<BoardUpdateTitleResponseDto> updateBoardTitle(
            @PathVariable Long boardId, @RequestBody BoardUpdateTitleRequestDto boardUpdateTitleRequestDto) {
        return ResponseEntity.ok(boardService.updateBoardTitle(boardId, boardUpdateTitleRequestDto));
    }

    @PutMapping("/board/{boardId}/contents")
    public ResponseEntity<BoardUpdateContentsResponseDto> updateBoardContents(
            @PathVariable Long boardId, @RequestBody BoardUpdateContentsRequestDto boardUpdateContentsRequestDto) {
        return ResponseEntity.ok(boardService.updateBoardContents(boardId, boardUpdateContentsRequestDto));
    }

    @DeleteMapping("/boards/{boardId}")
    public void deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
    }





}
