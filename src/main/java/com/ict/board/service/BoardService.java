package com.ict.board.service;

import java.util.List;
import java.util.Map;

import com.ict.common.dao.PostVO;

public interface BoardService {
	public int getTotalCount();
	public List<PostVO> getQnaList(int offset, int limit);
	public int getQnaInsert(PostVO postVO);
	public PostVO getQnaDetail(String post_idx);
	public int getQnaHit(String post_idx);
	public int getQnaUpdate(PostVO postVO);
	public int getQnaDelete(PostVO postVO);
	public int getLevUpdate(Map<String, Integer> map);
	public int getQnaAnswerInsert(PostVO postVO);
	
}
