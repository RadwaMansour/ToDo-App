package com.example.TodoService.TodoService.service;

import com.example.TodoService.TodoService.model.ItemsDetails;
import com.example.TodoService.TodoService.repositories.ProductRepository;
import com.example.TodoService.TodoService.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService
{
    @Autowired
    private ProductRepository productRepository;


    public Item insert(ItemsDetails item)  {

        Item item1 = new Item(item.getUserId(),item.getTitle());
        item1.setItemsDetails(item);

        return productRepository.save(item1);

    }

    @Override
    public ItemsDetails findByIdItemsDetails(int id) {
        Item item = productRepository.findById(id).get();
       ItemsDetails itemsDetails=item.getItemsDetails();
        return itemsDetails;
    }
    @Override
    public Item findByIdItem(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public Item update(ItemsDetails itemsDetails,int id) {

        ItemsDetails item=findByIdItemsDetails(id);

        Item itemUpdate=findByIdItem(id);

        item.setDescription(itemsDetails.getDescription());
        item.setStatus(itemsDetails.getStatus());
        item.setCreated_at(itemsDetails.getCreated_at());
        item.setPriority(itemsDetails.getPriority());
        item.setTitle(itemsDetails.getTitle());

        itemUpdate.setTitle(itemsDetails.getTitle());
        itemUpdate.setItemsDetails(item);

        return productRepository.save(itemUpdate);
    }

}
