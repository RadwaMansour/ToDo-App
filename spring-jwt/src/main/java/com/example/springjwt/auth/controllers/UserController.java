package com.example.springjwt.auth.controllers;



import com.example.springjwt.auth.entities.User;
import com.example.springjwt.auth.services.UserService;
import com.example.springjwt.auth.utils.RegisterRequest;
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
@RequestMapping("/api/v1/user/")
public class UserController
{
    @Autowired
    private UserService userService;


    // add User
    @PostMapping("/user")
    @ApiOperation(value = "This is api wil be used to insert the User")
    @ApiResponses(value={
            @ApiResponse(code = 200,message = "this is the successful response"),
            @ApiResponse(code = 404,message = "Not found User"),
            @ApiResponse(code = 500,message = "error of server")
    }
    )
    public User insertItem (@RequestBody RegisterRequest registerRequest)  {

        return userService.insert(registerRequest);
    }


    // delete User
    @DeleteMapping("/user")
    @ApiOperation(value = "This is api wil be used to delete the User")
    @ApiResponses(value={
            @ApiResponse(code = 200,message = "this is the successful response"),
            @ApiResponse(code = 404,message = "Not found User"),
            @ApiResponse(code = 500,message = "error of server")
    }
    )
    public void delete(@RequestParam("id") int id) {

        userService.deleteById(id);
    }
    @DeleteMapping("/user/username")
    @ApiOperation(value = "This is api wil be used to delete the User")
    @ApiResponses(value={
            @ApiResponse(code = 200,message = "this is the successful response"),
            @ApiResponse(code = 404,message = "Not found User"),
            @ApiResponse(code = 500,message = "error of server")
    }
    )
    public void delete(@RequestParam("username") String username, @RequestParam("password") String password) {

        userService.deleteByUser(username,password);
    }
    // update User
    @PutMapping("/user")
    @ApiOperation(value = "This is api wil be used to update the User")
    @ApiResponses(value={
            @ApiResponse(code = 200,message = "this is the successful response"),
            @ApiResponse(code = 404,message = "Not found User"),
            @ApiResponse(code = 500,message = "error of server")
    }
    )
    public User update (@RequestBody User user,@RequestParam int id)
    {
        return userService.update(user,id);
    }


    // search by Id --> finduserById
    @GetMapping("/user/findById")
    @ApiOperation(value = "This is api wil be used to get the find user By Id")
    @ApiResponses(value={
            @ApiResponse(code = 200,message = "this is the successful response"),
            @ApiResponse(code = 404,message = "Not found user"),
            @ApiResponse(code = 500,message = "error of server")
    }
    )
    public User findById(@RequestParam("id") int id) {

        //get the user related to the name
        return userService.findById(id);
    }


}
