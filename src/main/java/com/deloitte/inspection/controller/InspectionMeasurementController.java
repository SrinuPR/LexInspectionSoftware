package com.deloitte.inspection.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/inspectionMeasurement")
public class InspectionMeasurementController {
	
	private static final Logger logger = LogManager.getLogger(InspectionMeasurementController.class);

}
