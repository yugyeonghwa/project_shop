package com.ict.review.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.common.dao.ReviewVO;
import com.ict.review.dao.ReviewDAO;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewDAO reviewDAO;

	@Override
	public int getReviewCnt() {
		return reviewDAO.getReviewCnt();
	}

	@Override
	public List<ReviewVO> getReviewList(int offset, int limit) {
		return reviewDAO.getReviewList(offset, limit);
	}

	@Override
	public int getReviewInsert(ReviewVO reviewVO) {
		return reviewDAO.getReviewInsert(reviewVO);
	}

	@Override
	public int getReviewHit(String review_idx) {
		return reviewDAO.getReviewHit(review_idx);
	}

	@Override
	public ReviewVO getReviewDetail(String review_idx) {
		return reviewDAO.getReviewDetail(review_idx);
	}

	@Override
	public int getReviewUpdate(ReviewVO reviewVO) {
		return reviewDAO.getReviewUpdate(reviewVO);
	}

	@Override
	public int getLevUpdate(Map<String, Integer> map) {
		return reviewDAO.getLevUpdate( map);
	}

	@Override
	public int getReviewAnswerInsert(ReviewVO reviewVO) {
		return reviewDAO.getReviewAnswerInsert(reviewVO);
	}
	
	@Override
	public int getReviewDelete(ReviewVO reviewVO) {
		return reviewDAO.getReviewDelete(reviewVO);
	}
	
}
