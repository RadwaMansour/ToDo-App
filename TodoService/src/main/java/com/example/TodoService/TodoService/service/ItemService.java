package com.example.TodoService.TodoService.service;

import com.example.TodoService.TodoService.model.Item;
import com.example.TodoService.TodoService.model.ItemsDetails;


public interface ItemService
{
    Item insert(ItemsDetails item) ;


    Item findByIdItem(int id);
    ItemsDetails findByIdItemsDetails(int id);
    void deleteById(int id);
    Item update(ItemsDetails item,int id);
   // List<item> getAllProducts();

    //List<Item> findItemByTitle(String title);

}
