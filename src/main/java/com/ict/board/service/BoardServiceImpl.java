package com.ict.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.board.dao.BoardDAO;
import com.ict.common.dao.PostVO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public int getTotalCount() {
		return boardDAO.getTotalCount();
	}
	
	@Override
	public List<PostVO> getQnaList(int offset, int limit) {
		return boardDAO.getQnaList(offset, limit);
	}

	@Override
	public int getQnaInsert(PostVO postVO) {
		return boardDAO.getQnaInsert(postVO);
	}
	
	@Override
	public PostVO getQnaDetail(String post_idx) {
		return boardDAO.getQnaDetail(post_idx);
	}
	
	@Override
	public int getQnaUpdate(PostVO postVO) {
		return boardDAO.getQnaUpdate(postVO);
	}
	
	@Override
	public int getQnaDelete(PostVO postVO) {
		return boardDAO.getQnaDelete(postVO);
	}
	
	@Override
	public int getLevUpdate(Map<String, Integer> map) {
		return boardDAO.getLevUpdate(map);
	}
	
	@Override
	public int getQnaAnswerInsert(PostVO postVO) {
		return boardDAO.getQnaAnswerInsert(postVO);
	}
	
	@Override
	public int getQnaHit(String post_idx) {
		return boardDAO.getQnaHit(post_idx);
	}
	
	
}
