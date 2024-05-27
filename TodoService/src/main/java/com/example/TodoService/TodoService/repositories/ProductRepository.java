package com.example.TodoService.TodoService.repositories;

import com.example.TodoService.TodoService.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Item, Integer>
{
    //@Query(value = "SELECT * FROM item where id",nativeQuery = true)
   // ItemsDetails findById(int id);
    @Query(value = "SELECT * FROM item where title",nativeQuery = true)
    Item findItemByTitle(String title);

    //Item findById(int id);
}
