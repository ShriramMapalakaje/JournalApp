package org.shriram.Journal.repository;

import org.bson.types.ObjectId;
import org.shriram.Journal.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);

    void deleteByUserName(String userName);
}
