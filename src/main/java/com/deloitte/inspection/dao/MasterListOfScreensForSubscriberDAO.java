package com.deloitte.inspection.dao;

import java.util.List;

import com.deloitte.inspection.exception.MasterListOfScreensForSubscriberException;
import com.deloitte.inspection.model.LISMasterListOfScreensForSubscriber;

public interface MasterListOfScreensForSubscriberDAO {

	public void deleteScreenForSubscriber(Integer subscriberId, String screenId) throws MasterListOfScreensForSubscriberException;

	public void insertScreensForSubscribers(List<LISMasterListOfScreensForSubscriber> insertList) throws MasterListOfScreensForSubscriberException;

	public List<LISMasterListOfScreensForSubscriber> getScreensforSubscriber(Integer subscriberId) throws MasterListOfScreensForSubscriberException;

}
