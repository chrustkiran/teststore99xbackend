package com.interview.xtest.repositories;

import com.interview.xtest.entities.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
