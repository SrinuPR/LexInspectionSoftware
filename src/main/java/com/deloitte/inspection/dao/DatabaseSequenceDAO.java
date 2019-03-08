package com.deloitte.inspection.dao;

import com.deloitte.inspection.exception.DatabaseSequenceException;

public interface DatabaseSequenceDAO {

	Integer getNextSequenceId(String key) throws DatabaseSequenceException;

}
