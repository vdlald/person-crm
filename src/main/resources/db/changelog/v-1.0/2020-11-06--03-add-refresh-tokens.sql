--liquibase formatted sql

--changeset vdlald:28
create table refresh_tokens
(
    token       varchar(36) not null primary key ,
    valid_until timestamp   not null default now(),
    user_id     bigint      not null
);

--changeset vdlald:29
alter table refresh_tokens
    add constraint refresh_tokens_fk_user_id foreign key (user_id) references users(user_id)