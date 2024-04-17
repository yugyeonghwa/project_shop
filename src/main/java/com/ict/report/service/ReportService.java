package com.ict.report.service;

import java.util.List;
import java.util.Map;

import com.ict.common.dao.ReportVO;

public interface ReportService {
	public int getReportCnt();
	public List<ReportVO> getReportList(int offset, int limit);
	public int getReportInsert(ReportVO reportVO);
	public int getReportHit(String report_idx);
	public ReportVO getReportDetail(String report_idx);
	public int getReportUpdate(ReportVO reportVO);
	public int getLevUpdate(Map<String, Integer> map);
	public int getReportAnswerInsert(ReportVO reportVO);
	public int getReportDelete(ReportVO reportVO);
	
}
