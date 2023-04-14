CREATE TABLE categories(
    id numeric primary key,
    name varchar(50) default null,
    parent_id numeric default null,
    sequence numeric default null
);
