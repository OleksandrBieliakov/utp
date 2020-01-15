drop table groups_users;
drop table users;
drop table groups;

CREATE TABLE users(
   user_id integer not null PRIMARY KEY,
   user_login VARCHAR (50) NOT NULL,
   user_password VARCHAR (50) NOT NULL
);

CREATE TABLE groups(
   group_id integer not null PRIMARY KEY,
   group_name VARCHAR (250) NOT NULL,
   group_description VARCHAR (500)
);

CREATE TABLE groups_users(
   group_id integer not null,
   user_id integer not null,
   primary key(group_id, user_id),
   CONSTRAINT group_id_fkey FOREIGN KEY (group_id)
   REFERENCES groups (group_id),
   CONSTRAINT user_id_fkey FOREIGN KEY (user_id)
   REFERENCES users (user_id)
);