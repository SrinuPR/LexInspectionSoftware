package com.deloitte.inspection.dao.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.deloitte.inspection.dao.DatabaseSequenceDAO;
import com.deloitte.inspection.exception.DatabaseSequenceException;
import com.deloitte.inspection.model.DatabaseSequence;

@Repository
public class DatabaseSequenceDAOImpl implements DatabaseSequenceDAO{
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public Integer getNextSequenceId(String key) throws DatabaseSequenceException {
		Query query = new Query(Criteria.where("sequenceName").is(key));
		Update update = new Update();
		update.inc("seq", 1);
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		DatabaseSequence sequence = mongoTemplate.findAndModify(query, update, options, DatabaseSequence.class);
		if(sequence==null) {
			DatabaseSequence seq = new DatabaseSequence();
			seq.setSequenceName(key);
			seq.setSeq(0);
			mongoTemplate.save(seq, "LIS_SEQ");
		}
		return !Objects.isNull(sequence) ? sequence.getSeq() : 0;
	}

}
