package com.bsuir.masasha.hostel.core.repo;

import com.bsuir.masasha.hostel.core.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface MessageRepository extends CrudRepository<Message, Integer> {

    List<Message> findByTag(String tag);

}