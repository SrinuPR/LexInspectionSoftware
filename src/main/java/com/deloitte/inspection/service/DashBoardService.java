package com.deloitte.inspection.service;

import com.deloitte.inspection.response.dto.DashBoardResponseDTO;

public interface DashBoardService {

	public DashBoardResponseDTO getDashboardData(String userId) throws Exception;

}
