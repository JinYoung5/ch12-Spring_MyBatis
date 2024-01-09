package kr.spring.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardVO;
import kr.spring.util.PageUtil;

@Controller		//자동스캔 대상 처리
public class BoardController {
	@Autowired
	private BoardService boardService;		//아래에 postmapping insert.do 의 글 등록을 하기위해 넣어줘야함
	
	//로그 처리(로그 대상 지정)
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	//자바빈 초기화
	@ModelAttribute
	public BoardVO initCommand() {
		return new BoardVO();
	}
	
	@GetMapping("/insert.do")
	public String form() {
		return "insertForm";
	}
	
	@PostMapping("/insert.do")
	public String submit(@Valid BoardVO boardVO, BindingResult result) {
		log.debug("<<BoardVO>> : " + boardVO);
		
		//유효성 체크 결과 오류가 있으면 폼을 호출
		if(result.hasErrors()) {
			return form();
		}
		
		//글 등록
		boardService.insertBoard(boardVO);
		
		return "redirect:/list.do";
	}
	
	@RequestMapping("/list.do")
	public ModelAndView getList(
			@RequestParam(value="pageNum",defaultValue="1")int currentPage) {
		
		//총 레코드수
		int count = boardService.selectBoardCount();
		log.debug("<<count>> : " + count);
		log.debug("<<pageNum>> : " + currentPage);
		
		//페이지 처리									//rownum,rnum, 요청Url
		PageUtil page = new PageUtil(currentPage, count, 10,10,"list.do");
		
		//목록 호출
		List<BoardVO> list = null;
		if(count > 0) {
			Map<String,Integer> map = new HashMap<String,Integer>();
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = boardService.selectBoardList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		//뷰이름 지정
		mav.setViewName("selectList");
		//데이터 저장
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		
		return mav;
	}
	
	@RequestMapping("/detail.do")
	public ModelAndView detail(@RequestParam int num) {
		BoardVO board = boardService.selectBoard(num);
								//뷰이름,속성명,속성값
		return new ModelAndView("selectDetail","board",board);
	}
	
	@GetMapping("/update.do")
	public String formUpdate(@RequestParam int num, Model model) {
		model.addAttribute("boardVO", boardService.selectBoard(num));
		
		
		return "updateForm";
	}
	
	@PostMapping("/update.do")
	public String submitUpdate(@Valid BoardVO boardVO, BindingResult result) {
		//유효성 체크  결과 오류가 있으면 폼을 호출
		if(result.hasErrors()) {
			return "updateForm";
		}
		
		//DB에서 저장된 비밀번호 구하기
		BoardVO db_board = boardService.selectBoard(boardVO.getNum());
		//비밀번호 체크
		if(!db_board.getPasswd().equals(boardVO.getPasswd())) {
								//에러필드, 에러코드
			result.rejectValue("passwd", "invalidPassword");
			return "updateForm";
			
		}
		
		//글 수정
		boardService.updateBoard(boardVO);
		
		return "redirect:/list.do";
	}
	
	@GetMapping("/delete.do")
	public String formDelete(@RequestParam int num, Model model) {
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(num);
		
		model.addAttribute("boardVO",boardVO);
		
		return "deleteForm";
	}
	
	@PostMapping("/delete.do")							//유효성체크하기위해 BindindResult
	public String submitDelete(@Valid BoardVO boardVO, BindingResult result) {
		//유효성 체크 결과 오류가 있으면 폼을 호출
		//비밀번호만 전송 여부 체크
		if(result.hasFieldErrors("passwd")) {
			return "deleteForm";
		}
		
		//DB에 저장된 비밀번호 구하기
		BoardVO db_board = boardService.selectBoard(boardVO.getNum());
		//비밀번호 일치 여부 체크(비밀번호가 불일치하면 진입 : !~)
		if(!db_board.getPasswd().equals(boardVO.getPasswd())) {
								//에러필드, 에러코드
			result.rejectValue("passwd", "invalidPassword");
			
			return "deleteForm";
		}
		
		//글 삭제
		boardService.deleteBoard(boardVO.getNum());
		
		return "redirect:/list.do";
	}
}
