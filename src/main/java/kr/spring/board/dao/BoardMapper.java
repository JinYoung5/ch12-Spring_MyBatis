package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import kr.spring.board.vo.BoardVO;

public interface BoardMapper {
	public void insertBoard(BoardVO board);
	public int selectBoardCount();
	public List<BoardVO> selectBoardList(Map<String,Integer> map);
	public BoardVO selectBoard(int num);
	public void updateboard(BoardVO board);
	public void deleteBoard(int num);
}

//클래스를 명시하지않고 인터페이스로만 한 이유: MyBatis가 자동으로 클래스를 인식함
//BoardMapper.java , BoardMapper.xml, BoardMapper.xml안의 namespace 경로 모두 일치해야 오류 안뜸