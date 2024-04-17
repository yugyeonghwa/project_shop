package com.ict.review.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.common.dao.ReviewVO;

@Repository
public class ReviewDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int getReviewCnt() {
		try {
			return sqlSessionTemplate.selectOne("review.review_cnt");
		} catch (Exception e) {
			System.out.println("dao cnt err : "+e);
		}
		return -1;
	}

	public List<ReviewVO> getReviewList(int offset, int limit) {
		try {
			Map<String, Integer>map = new HashMap<String, Integer>();
			map.put("offset", offset);
			map.put("limit", limit);
			return sqlSessionTemplate.selectList("review.review_list", map);
		} catch (Exception e) {
			System.out.println("dao rev list err : "+e);
		}
		return null;
	}

	public int getReviewInsert(ReviewVO reviewVO) {
		try {
			return sqlSessionTemplate.insert("review.review_insert", reviewVO);
		} catch (Exception e) {
			System.out.println("dao rev ins err : "+e);
		}
		return -1;
	}

	public int getReviewHit(String review_idx) {
		try {
			return sqlSessionTemplate.update("review.review_hit", review_idx);
		} catch (Exception e) {
			System.out.println("dao hit err : "+e);
		}
		return -1;
	}

	public ReviewVO getReviewDetail(String review_idx) {
		try {
			return sqlSessionTemplate.selectOne("review.review_detail", review_idx);
		} catch (Exception e) {
			System.out.println("dao rev detail err : "+e);
		}
		return null;
	}

	public int getReviewUpdate(ReviewVO reviewVO) {
		try {
			return sqlSessionTemplate.update("review.review_update", reviewVO);
		} catch (Exception e) {
			System.out.println("dao rev update err : "+e);
		}
		return -1;
	}

	public int getLevUpdate(Map<String, Integer> map) {
		try {
			return sqlSessionTemplate.update("review.review_lev_update", map);
		} catch (Exception e) {
			System.out.println("dao rev levup err : "+e);
		}
		return -1;
	}

	public int getReviewAnswerInsert(ReviewVO reviewVO) {
		try {
			return sqlSessionTemplate.insert("review.review_ans_ins", reviewVO);
		} catch (Exception e) {
			System.out.println("dao rev ans ins err : "+e);
		}
		return -1;
	}
	
	public int getReviewDelete(ReviewVO reviewVO) {
		try {
			if (reviewVO.getStep().equals("0")) {
				return sqlSessionTemplate.update("review.review_delete", reviewVO);
			} else {
				return sqlSessionTemplate.delete("review.review_ans_delete", reviewVO);
			}
		} catch (Exception e) {
			System.out.println("dao rev del err : "+e);
		}
		return -1;
	}
	
}











