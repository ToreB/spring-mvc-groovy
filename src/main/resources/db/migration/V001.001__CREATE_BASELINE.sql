create table todo (
    id varchar(255) not null,
    name varchar(255) not null,
    description varchar(255),
    completed boolean not null,
    owner varchar(255) not null,
    created_at datetime not null,
    updated_at datetime not null,

    constraint todo_pk primary key (id)
)