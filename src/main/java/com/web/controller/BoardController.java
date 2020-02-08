package com.web.controller;


import com.web.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Pageable;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired // 의존성주입
    BoardService boardService;

    @GetMapping({"", "/"}) // 여러개 받기 가
    public String board(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model) {
        model.addAttribute("board", boardService.findBoardByIdx(idx));
        return "/board/form";
    }

    @GetMapping("/list")
    public String list(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("boardList", boardService.findBoardList(pageable));
        return "/board/list"; // src/resources/templates를 기준으로 데이터를 바인딩할 타깃의 뷰 경로를 지정한다.
    }
}
// 흐름 -> 컨트롤러 -> 서비스 -> 레포지토리 -> 디비 다시 역순.
// 디비 -> 레포지토리 -> 서비스 -> 컨트롤러 -> 타임리프나 머스타치 뷰전달