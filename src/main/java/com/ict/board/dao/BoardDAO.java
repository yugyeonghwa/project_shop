package com.ict.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.common.dao.PostVO;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int getTotalCount() {
		try {
			return sqlSessionTemplate.selectOne("board.qna_count");
		} catch (Exception e) {
			System.out.println("count err : "+e);
		}
		return -1;
	}
	
	public List<PostVO> getQnaList(int offset, int limit) {
		try {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("offset", offset);
			map.put("limit", limit);
			return sqlSessionTemplate.selectList("board.qna_list", map);
		} catch (Exception e) {
			System.out.println("qnalist err : "+e);
		}
		return null;
	}
	
	public int getQnaInsert(PostVO postVO) {
		try {
			return sqlSessionTemplate.insert("board.qna_insert", postVO);
		} catch (Exception e) {
			System.out.println("qnainsert err : "+e);
		}
		return -1;
	}
	
	public List<PostVO> getReportList() {
		try {
			return sqlSessionTemplate.selectList("board.report_list");
		} catch (Exception e) {
			System.out.println("reportlist err : " +e);
		}
		return null;
	}
	
	public PostVO getQnaDetail(String post_idx) {
		try {
			return sqlSessionTemplate.selectOne("board.qna_detail", post_idx);
		} catch (Exception e) {
			System.out.println("qnadetail err : "+e);
		}
		return null;
	}
	
	public int getQnaUpdate(PostVO postVO) {
		try {
			return sqlSessionTemplate.update("board.qna_update", postVO);
		} catch (Exception e) {
			System.out.println("qnaupdate err : "+e);
		}
		return -1;
	}
	
	public int getQnaDelete(PostVO postVO) {
		try {
			if (postVO.getStep().equals("0")) {
				return sqlSessionTemplate.update("board.qna_delete", postVO);
			} else {
				return sqlSessionTemplate.delete("board.qna_ans_delete", postVO);
			}
		} catch (Exception e) {
			System.out.println("qnadelete err : " + e);
		}
		return -1;
	}
	
	public int getLevUpdate(Map<String, Integer> map) {
		try {
			return sqlSessionTemplate.update("board.qna_lev_update", map);
		} catch (Exception e) {
			System.out.println("lev update err : "+e);
		}
		return -1;
	}
	
	public int getQnaAnswerInsert(PostVO postVO) {
		try {
			if (postVO.getPost_status().equals("0")) {
				sqlSessionTemplate.update("board.qna_status", postVO);
				return sqlSessionTemplate.insert("board.qna_ans_insert", postVO);
			} else {
				return 0;
			}
		} catch (Exception e) {
			System.out.println("qna answer insert err : "+e);
		}
		return -1;
	}
	
	public int getQnaHit(String post_idx) {
		try {
			return sqlSessionTemplate.update("board.qna_hit", post_idx);
		} catch (Exception e) {
			System.out.println("qna hit err : "+e);
		}
		return -1;
	}
	
	
}

