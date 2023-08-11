create database todolist;
\c todolist;

create table task (
  id serial primary key,
  name varchar(50) not null,
  duration int not null,
  isFinished boolean not null,
  importance int not null
);

insert into task (name, duration, isFinished, importance) values ('Faire la vaisselle', 30, 'false', 7);
