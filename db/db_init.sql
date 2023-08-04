create table books (
                       id serial primary key,
                       name character varying(40),
                       author character varying(40),
                       pages integer,
                       price integer,
                       status boolean
);

create table readers (
                         id serial primary key,
                         name character varying(40),
                         email character varying(40),
                         number character varying(40),
                         user_id integer
);

create table form1 (
                       id serial primary key,
                       id_reader integer,
                       id_book integer,
                       date_take character varying(40),
                       date_return character varying(40),
                       date_fact_return character varying(40),
                       count integer,
                       merge integer
);


create table task (
                      id serial primary key,
                      id_reader integer,
                      status character varying(40),
                      date_from character varying(40),
                      date_to character varying(40)
);

create table archive_books (
                               id serial primary key,
                               id_book integer,
                               name character varying(40),
                               author character varying(40),
                               pages integer,
                               price integer
);


create table archive_readers (
                                 id serial primary key,
                                 id_reader integer,
                                 id_book integer,
                                 name character varying(40),
                                 email character varying(40),
                                 number character varying(40),
                                 date_take character varying(40),
                                 date_return character varying(40),
                                 date_fact_return character varying(40),
                                 count integer,
                                 merge integer
);

create table t_role(
                       id serial primary key,
                       name character varying(40)
);

insert into t_role(id, name) values(1,'ROLE_ADMIN');
insert into t_role(id, name) values(2,'ROLE_USER');

create table t_user(
                       id serial primary key,
                       username character varying(255),
                       password character varying(255),
                       reader_id integer
);
insert into t_user(id, username, password, reader_id) values(1,'admin','$2a$10$bkjFYaCJ59kIBoW.sHXUbeyE7wosYHi4K14FVdZq7L6JTXh4fmRdO',0);


create table t_user_roles(
                             user_id integer,
                             roles_id integer
);

insert into t_user_roles(user_id, roles_id) values(1,1);
