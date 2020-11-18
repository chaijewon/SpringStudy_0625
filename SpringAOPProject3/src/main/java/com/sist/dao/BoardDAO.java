package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class BoardDAO {
	@Autowired
    private BoardMapper mapper;
	
	public List<BoardVO> boardListData(Map map)
	{
		return mapper.boardListData(map);
	}
	public void boardInsert(BoardVO vo)
	{
		mapper.boardInsert(vo);
	}
	
	public BoardVO boardDetailData(int no)
	{
		// 조회수 증가
		mapper.boardHitIncrement(no);
		return mapper.boardDetailData(no);
	}
}










