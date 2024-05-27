package com.example.TodoService.TodoService.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "item-details")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("This is the model class responsible of Items details ")
public class ItemsDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("The id of the Items Details")
    private int id;


    private String title;
    private int userId=3;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "priority")
    private Priority priority;

    @Column(name = "status")
    private Status status;

    @JsonIgnore
    @OneToOne(mappedBy = "itemsDetails" , cascade = CascadeType.ALL)
    private Item item;
    public ItemsDetails(int id, String description, Date created_at, Priority priority, Status status) {
        this.id = id;
        this.description = description;
        this.created_at = created_at;
        this.priority = priority;
        this.status = status;

    }


}
