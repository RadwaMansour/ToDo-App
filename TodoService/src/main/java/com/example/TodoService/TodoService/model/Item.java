package com.example.TodoService.TodoService.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "item")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("This is the model class responsible of Todo Service")
public class Item{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("The id of the product")
    private int id;

    @Column(name = "title")
    private String title;

    @JsonIgnore
    @Column( name = "userId")
    private int userId;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "item_details_id")
    private ItemsDetails itemsDetails;


    public Item(String title) {
        this.title = title;
    }
    public Item(int userId) {
        this.userId = userId;
    }
    public Item(String title, int userId, ItemsDetails itemsDetails) {
        this.title = title;
        this.userId = userId;
        this.itemsDetails = itemsDetails;
    }

    public Item(int userId,String title) {
        this.userId = userId;
        this.title = title;
    }



}
