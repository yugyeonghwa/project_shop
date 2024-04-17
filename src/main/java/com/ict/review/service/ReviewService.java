package com.ict.review.service;

import java.util.List;
import java.util.Map;

import com.ict.common.dao.ReviewVO;

public interface ReviewService {
	public int getReviewCnt();
	public List<ReviewVO> getReviewList(int offset, int limit);
	public int getReviewInsert(ReviewVO reviewVO);
	public int getReviewHit(String review_idx);
	public ReviewVO getReviewDetail(String review_idx);
	public int getReviewUpdate(ReviewVO reviewVO);
	public int getLevUpdate(Map<String, Integer> map);
	public int getReviewAnswerInsert(ReviewVO reviewVO);
	public int getReviewDelete(ReviewVO reviewVO);
}
