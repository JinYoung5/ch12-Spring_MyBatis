package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.vo.BoardVO;

public interface BoardMapper {
	@Insert("INSERT INTO aboard (num,writer,title,passwd,content,reg_date) VALUES (aboard_seq.nextval,#{writer},#{title},#{passwd},#{content},SYSDATE)")
	public void insertBoard(BoardVO board);
	@Select("SELECT COUNT(*) FROM aboard")
	public int selectBoardCount();
	public List<BoardVO> selectBoardList(Map<String,Integer> map);
	@Select("SELECT * FROM aboard WHERE num=#{num}")
	public BoardVO selectBoard(int num);
	@Update("UPDATE aboard SET writer=#{writer},title=#{title},content=#{content} WHERE num=#{num}")
	public void updateBoard(BoardVO board);
	@Delete("DELETE FROM aboard WHERE num=#{num}")
	public void deleteBoard(int num);
}

//클래스를 명시하지않고 인터페이스로만 한 이유: MyBatis가 자동으로 클래스를 인식함
//BoardMapper.java , BoardMapper.xml, BoardMapper.xml안의 namespace 경로 모두 일치해야 오류 안뜸
//MyBaits를 꼭 사용안해도되는데 어노테이션형태를 사용할경우 sql문이 짧을 때 작업하기 좋지만 sql문이 길어지면 추후에 작업이 어렵거나, xml로 작업할 때 처럼 구분문을 사용할 수 없음