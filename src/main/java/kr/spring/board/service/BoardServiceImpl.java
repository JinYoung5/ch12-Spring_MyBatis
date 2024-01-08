package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.board.dao.BoardMapper;
import kr.spring.board.vo.BoardVO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{

	@Autowired //BoardMapper 를 주입받아야됨
	BoardMapper boardMapper;
	
	@Override
	public void insertBoard(BoardVO board) {
		boardMapper.insertBoard(board);
	}

	@Override
	public int selectBoardCount() {
		return boardMapper.selectBoardCount();
	}

	@Override
	public List<BoardVO> selectBoardList(Map<String, Integer> map) {
		return boardMapper.selectBoardList(map);
	}

	@Override
	public BoardVO selectBoard(int num) {
		return boardMapper.selectBoard(num);
	}

	@Override
	public void updateboard(BoardVO board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBoard(int num) {
		// TODO Auto-generated method stub
		
	}

}
