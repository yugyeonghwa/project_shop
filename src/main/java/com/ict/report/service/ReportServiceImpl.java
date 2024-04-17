package com.ict.report.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.common.dao.ReportVO;
import com.ict.report.dao.ReportDAO;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportDAO reportDAO;

	@Override
	public int getReportCnt() {
		return reportDAO.getReportCnt();
	}

	@Override
	public List<ReportVO> getReportList(int offset, int limit) {
		return reportDAO.getReportList(offset, limit);
	}

	@Override
	public int getReportInsert(ReportVO reportVO) {
		return reportDAO.getReportInsert(reportVO);
	}

	@Override
	public int getReportHit(String report_idx) {
		return reportDAO.getReportHit(report_idx);
	}

	@Override
	public ReportVO getReportDetail(String report_idx) {
		return reportDAO.getReportDetail(report_idx);
	}

	@Override
	public int getReportUpdate(ReportVO reportVO) {
		return reportDAO.getReportUpdate(reportVO);
	}

	@Override
	public int getLevUpdate(Map<String, Integer> map) {
		return reportDAO.getLevUpdate(map);
	}

	@Override
	public int getReportAnswerInsert(ReportVO reportVO) {
		return reportDAO.getReportAnswerInsert(reportVO);
	}

	@Override
	public int getReportDelete(ReportVO reportVO) {
		return reportDAO.getReportDelete(reportVO);
	}
	
}
