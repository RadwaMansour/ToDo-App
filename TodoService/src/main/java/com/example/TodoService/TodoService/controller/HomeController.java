package com.example.TodoService.TodoService.controller;


import com.example.TodoService.TodoService.model.Item;
import com.example.TodoService.TodoService.model.ItemsDetails;
import com.example.TodoService.TodoService.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


//@RestController = @Controller + @RequestBody
@RestController
@Api(tags = "This is the documentation for item Apis")
public class HomeController
{
    @Autowired
    private ItemService itemService;


    // add Product
    @PostMapping("/items")
    @ApiOperation(value = "This is api wil be used to insert the item")
    @ApiResponses(value={
            @ApiResponse(code = 200,message = "this is the successful response"),
            @ApiResponse(code = 404,message = "Not found item"),
            @ApiResponse(code = 500,message = "error of server")
    }
    )
    public Item insertItem (@RequestBody ItemsDetails newItem)  {
      return itemService.insert(newItem);
    }


    // delete Item
    @DeleteMapping("/items")
    @ApiOperation(value = "This is api wil be used to delete the Item")
    @ApiResponses(value={
            @ApiResponse(code = 200,message = "this is the successful response"),
            @ApiResponse(code = 404,message = "Not found item"),
            @ApiResponse(code = 500,message = "error of server")
    }
    )
    public void deleteItem(@RequestParam("itemId") int id) {

        itemService.deleteById(id);
    }
    // update Item
    @PutMapping("/items")
    @ApiOperation(value = "This is api wil be used to update the Item")
    @ApiResponses(value={
            @ApiResponse(code = 200,message = "this is the successful response"),
            @ApiResponse(code = 404,message = "Not found item"),
            @ApiResponse(code = 500,message = "error of server")
    }
    )
    public Item updateItem (@RequestBody ItemsDetails itemDetails,@RequestParam int id)
    {
        System.out.println("radwa1111111111");
        return itemService.update(itemDetails,id);
    }


    // search by Id --> findItemById
    @GetMapping("/items/findItemById")
    @ApiOperation(value = "This is api wil be used to get the find Item By Id")
    @ApiResponses(value={
            @ApiResponse(code = 200,message = "this is the successful response"),
            @ApiResponse(code = 404,message = "Not found item"),
            @ApiResponse(code = 500,message = "error of server")
    }
    )
    public ItemsDetails findItemById(@RequestParam("id") int id) {

        //get the product related to the name
        return itemService.findByIdItemsDetails(id);
    }


    // test app
    @GetMapping("/testApp")
    @ApiIgnore
    public String testAppStatus(){
        return "the app is now running";
    }
}
