create table users (
   user_id varchar(255) not null,
   creation_date timestamp(6) not null,
   modification_date timestamp(6),
   email varchar(255) not null,
   first_name varchar(255) not null,
   last_name varchar(255) not null,
   primary key (user_id)
);