package com.example.TodoService.TodoService.repositories;

import com.example.TodoService.TodoService.model.Item;
import com.example.TodoService.TodoService.model.ItemsDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface ItemsDetailsRepository extends JpaRepository<ItemsDetails, Integer>
{

}