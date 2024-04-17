package com.ict.common;

import org.springframework.stereotype.Component;

@Component
public class Paging {
	// 첫번째 페이지이면서 첫번째 블록 지정
		private int nowPage = 1;
		private int nowBlock = 1;

		// 한개의 페이지에 10개의 행(10줄)이 들어감
		private int numPerPage=10;
		// 한개의 블록에 5개의 페이지 표현
		private int pagePerBlock=5;
		
		// DB의 게시물의 총 개수
		private int totalRecord = 0;

		// 게시물의 수를 이용하여 전체 페이지의 수를 도출함
		// 만약에 총 게시물의 수가 45개있으면 한 페이지당 10개씩이니 페이지의 수는 총 5개임
		private int totalPage=0;
		
		// 게시물의 총 블록의 수 
		// 만약에 총 게시물의 수가 45개있으면 블록은 총 2개 있는 것임, 한 블록당 3페이지가 있기 때문에
		private int totalBlock=0;
		
		// 한번에 가져올 게시물의 시작 번호와 끝 번호
		private int begin = 0;
		private int end=0;
		
		// 한번에 가져올 블록의 시작 번호와 끝 번호	
		private int beginBlock=0;
		private int endBlock=0;
		
		private int offset=0;

		public int getNowPage() {
			return nowPage;
		}

		public void setNowPage(int nowPage) {
			this.nowPage = nowPage;
		}

		public int getNowBlock() {
			return nowBlock;
		}

		public void setNowBlock(int nowBlock) {
			this.nowBlock = nowBlock;
		}

		public int getNumPerPage() {
			return numPerPage;
		}

		public void setNumPerPage(int numPerPage) {
			this.numPerPage = numPerPage;
		}

		public int getPagePerBlock() {
			return pagePerBlock;
		}

		public void setPagePerBlock(int pagePerBlock) {
			this.pagePerBlock = pagePerBlock;
		}

		public int getTotalRecord() {
			return totalRecord;
		}

		public void setTotalRecord(int totalRecord) {
			this.totalRecord = totalRecord;
		}

		public int getTotalPage() {
			return totalPage;
		}

		public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}

		public int getTotalBlock() {
			return totalBlock;
		}

		public void setTotalBlock(int totalBlock) {
			this.totalBlock = totalBlock;
		}

		public int getBegin() {
			return begin;
		}

		public void setBegin(int begin) {
			this.begin = begin;
		}

		public int getEnd() {
			return end;
		}

		public void setEnd(int end) {
			this.end = end;
		}

		public int getBeginBlock() {
			return beginBlock;
		}

		public void setBeginBlock(int beginBlock) {
			this.beginBlock = beginBlock;
		}

		public int getEndBlock() {
			return endBlock;
		}

		public void setEndBlock(int endBlock) {
			this.endBlock = endBlock;
		}

		public int getOffset() {
			return offset;
		}

		public void setOffset(int offset) {
			this.offset = offset;
		}
		
}
