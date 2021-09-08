create table employee
(
    id         serial primary key,
    first_name varchar(100),
    last_name  varchar(100),
    inn        varchar(100),
    created    timestamp without time zone not null default now()
);

create table person
(
    id          serial primary key not null,
    login       varchar(100),
    password    varchar(100),
    employee_id int                not null references employee (id)
);

insert into employee (first_name, last_name, inn)
values ('ilya', 'pavlov', 'inn123456');
insert into employee (first_name, last_name, inn)
values ('petr', 'arsentev', 'inn654321');

insert into person (login, password, employee_id)
values ('pavlovia-1', '123', 1);
insert into person (login, password, employee_id)
values ('pavlovia-2', '123', 1);
insert into person (login, password, employee_id)
values ('arsentev-1', '123', 2);
insert into person (login, password, employee_id)
values ('arsentev-2', '123', 2);
