package org.shriram.Journal.repository;

import org.bson.types.ObjectId;
import org.shriram.Journal.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<JournalEntry, ObjectId> {


}
