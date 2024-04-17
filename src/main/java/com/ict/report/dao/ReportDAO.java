package com.ict.report.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.common.dao.ReportVO;

@Repository
public class ReportDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int getReportCnt() {
		try {
			return sqlSessionTemplate.selectOne("report.report_cnt");
		} catch (Exception e) {
			System.out.println("dao rep cnt err : "+e);
		}
		return -1;
	}

	public List<ReportVO> getReportList(int offset, int limit) {
		try {
			Map<String, Integer>map = new HashMap<String, Integer>();
			map.put("offset", offset);
			map.put("limit", limit);
			return sqlSessionTemplate.selectList("report.report_list", map);
		} catch (Exception e) {
			System.out.println("dao rep list err : "+e);
		}
		return null;
	}

	public int getReportInsert(ReportVO reportVO) {
		try {
			return sqlSessionTemplate.insert("report.report_ins", reportVO);
		} catch (Exception e) {
			System.out.println("dao rep ins err : "+e);
		}
		return -1;
	}

	public int getReportHit(String report_idx) {
		try {
			return sqlSessionTemplate.update("report.report_hit", report_idx);
		} catch (Exception e) {
			System.out.println("dao rep hit err : "+e);
		}
		return -1;
	}

	public ReportVO getReportDetail(String report_idx) {
		try {
			return sqlSessionTemplate.selectOne("report.report_detail", report_idx);
		} catch (Exception e) {
			System.out.println("dao rep detail err : "+e);
		}
		return null;
	}

	public int getReportUpdate(ReportVO reportVO) {
		try {
			return sqlSessionTemplate.update("report.report_update", reportVO);
		} catch (Exception e) {
			System.out.println("dao rep update err : "+e);
		}
		return -1;
	}

	public int getLevUpdate(Map<String, Integer> map) {
		try {
			return sqlSessionTemplate.update("report.report_lev_update", map);
		} catch (Exception e) {
			System.out.println("dao rep levup err : "+e);
		}
		return -1;
	}

	public int getReportAnswerInsert(ReportVO reportVO) {
		try {
			if (reportVO.getReport_status().equals("0")) {
				sqlSessionTemplate.update("report.report_status", reportVO);
				return sqlSessionTemplate.insert("report.report_ans_insert", reportVO);
			} else {
				return 0;
			}
		} catch (Exception e) {
			System.out.println("dao rep ans ins err : "+e);
		}
		return -1;
	}

	public int getReportDelete(ReportVO reportVO) {
		try {
			if (reportVO.getStep().equals("0")) {
				return sqlSessionTemplate.update("report.report_delete", reportVO);
			} else {
				return sqlSessionTemplate.delete("report.report_ans_delete", reportVO);
			}
		} catch (Exception e) {
			System.out.println("dao rep del err : "+e);
		}
		return -1;
	}
	
}
