--liquibase formatted sql

--changeset vdlald:1
create table companies
(
    company_id bigint generated by default as identity,
    name       varchar(32),
    user_id    bigint not null,
    primary key (company_id)
);

--changeset vdlald:2
create table contacts
(
    contact_id bigint generated by default as identity,
    created_at timestamp not null default now(),
    updated_at timestamp,
    name       varchar(32),
    company_id bigint,
    user_id    bigint    not null,
    primary key (contact_id)
);

--changeset vdlald:3
create table leads
(
    lead_id    bigint generated by default as identity,
    created_at timestamp not null default now(),
    updated_at timestamp,
    name       varchar(32),
    sale       decimal(19, 2),
    status_id  bigint    not null,
    user_id    bigint    not null,
    primary key (lead_id)
);

--changeset vdlald:4
create table leads_contacts
(
    contact_id bigint not null,
    lead_id    bigint not null
);

--changeset vdlald:5
create table pipelines
(
    pipeline_id bigint generated by default as identity,
    name        varchar(32),
    user_id     bigint not null,
    primary key (pipeline_id)
);

--changeset vdlald:6
create table statuses
(
    status_id   bigint generated by default as identity,
    name        varchar(32),
    pipeline_id bigint not null,
    primary key (status_id)
);

--changeset vdlald:7
create table user_authorities
(
    user_id   bigint not null,
    authority varchar(255)
);

--changeset vdlald:8
create table users
(
    user_id    bigint generated by default as identity,
    created_at timestamp   not null default now(),
    updated_at timestamp,
    password   varchar(60) not null,
    username   varchar(32) not null,
    primary key (user_id)
);

--changeset vdlald:9
create table usersinfo
(
    userinfo_id bigint generated by default as identity,
    email       varchar(64),
    firstname   varchar(32),
    lastname    varchar(32),
    user_id     bigint not null,
    primary key (userinfo_id)
);

--changeset vdlald:10
alter table users
    add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username);

alter table usersinfo
    add constraint UK_7lw66ffkiov27cf2u2d62ewg5 unique (email);

alter table contacts
    add constraint FK5nvk2pja04n1pbiyk1xn7739l foreign key (company_id) references companies;

alter table contacts
    add constraint FKna8bddygr3l3kq1imghgcskt8 foreign key (user_id) references users;

alter table leads
    add constraint FKm4y87aldo2nu9g4iqwm1y9ngr foreign key (status_id) references statuses;

alter table leads
    add constraint FK10u8b7klywjncgkn7xffx7ncu foreign key (user_id) references users;

alter table leads_contacts
    add constraint FK70aoivhgle1c8wi1hxvvohj5m foreign key (lead_id) references leads;

alter table leads_contacts
    add constraint FKpx2f0y60xb8qflhm6vpg6hg0v foreign key (contact_id) references contacts;

alter table pipelines
    add constraint FKjr3b68dg5qsj0uf3psd0js6b8 foreign key (user_id) references users;

alter table statuses
    add constraint FKkw8ywp80vnvotmhg0wgv8jv46 foreign key (pipeline_id) references pipelines;

alter table user_authorities
    add constraint FKswqqnqcyqob25xy8l2iw1mi9 foreign key (user_id) references users;

alter table usersinfo
    add constraint FKhjn8m05kaw7ln6i1t4njjfr0s foreign key (user_id) references users;

