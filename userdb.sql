create table users (
        enabled bit not null,
        id integer not null auto_increment,
        email varchar(255),
        name varchar(255),
        password varchar(255),
        role enum ('ADMIN','USER'),
        username varchar(255),
        primary key (id)
    ) 
   
    
create table jwt (
        token_id integer not null auto_increment,
        user_id integer,
        created_time datetime(6) not null,
        expiration_time datetime(6) not null,
        token varchar(500) not null,
        primary key (token_id)
    ) 
 

    
create table forgot_password (
        fpid integer not null auto_increment,
        otp integer not null,
        user_id integer,
        expiration_time datetime(6) not null,
        primary key (fpid)
    )

   