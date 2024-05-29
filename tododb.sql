create table item (
        id integer not null auto_increment,
        item_details_id integer,
        user_id integer,
        title varchar(255),
        primary key (id)
    ) 
    
create table `item-details` (
        id integer not null auto_increment,
        priority tinyint,
        status tinyint,
        user_id integer not null,
        created_at datetime(6),
        description varchar(255),
        title varchar(255),
        primary key (id)
    ) 