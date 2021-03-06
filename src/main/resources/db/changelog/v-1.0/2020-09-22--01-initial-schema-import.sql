--liquibase formatted sql

--changeset vdlald:1
create table companies
(
    company_id bigint auto_increment,
    name       varchar(32),
    user_id    bigint not null,
    primary key (company_id)
);

--changeset vdlald:2
create table contacts
(
    contact_id bigint auto_increment,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    name       varchar(32),
    company_id bigint,
    user_id    bigint    not null,
    primary key (contact_id)
);

--changeset vdlald:3
create table leads
(
    lead_id    bigint auto_increment,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    name       varchar(32),
    sale       decimal(19, 2),
    status_id  bigint,
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
    pipeline_id bigint auto_increment,
    name        varchar(32),
    user_id     bigint not null,
    primary key (pipeline_id)
);

--changeset vdlald:6
create table statuses
(
    status_id   bigint auto_increment,
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
    user_id    bigint auto_increment,
    created_at timestamp   not null default now(),
    updated_at timestamp not null default now(),
    password   varchar(60) not null,
    username   varchar(32) not null,
    primary key (user_id)
);

--changeset vdlald:9
create table usersinfo
(
    userinfo_id bigint auto_increment,
    email       varchar(64),
    firstname   varchar(32),
    lastname    varchar(32),
    user_id     bigint not null,
    primary key (userinfo_id)
);

--changeset vdlald:10
alter table users
    add constraint users_unique_username unique (username);

alter table usersinfo
    add constraint usersinfo_unique_email unique (email);

alter table contacts
    add constraint contacts_fk_company_id foreign key (company_id) references companies (company_id);

alter table contacts
    add constraint contacts_fk_user_id foreign key (user_id) references users (user_id);

alter table leads
    add constraint leads_fk_status_id foreign key (status_id) references statuses (status_id);

alter table leads
    add constraint leads_fk_user_id foreign key (user_id) references users (user_id);

alter table leads_contacts
    add constraint leads_contacts_fk_lead_id foreign key (lead_id) references leads (lead_id);

alter table leads_contacts
    add constraint leads_contacts_fk_contact_id foreign key (contact_id) references contacts (contact_id);

alter table pipelines
    add constraint pipelines_fk_user_id foreign key (user_id) references users (user_id);

alter table statuses
    add constraint statuses_fk_pipeline_id foreign key (pipeline_id) references pipelines (pipeline_id);

alter table user_authorities
    add constraint user_authorities_fk_user_id foreign key (user_id) references users (user_id);

alter table usersinfo
    add constraint usersinfo_fk_user_id foreign key (user_id) references users (user_id);

