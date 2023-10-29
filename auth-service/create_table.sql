create table user
(
    user_id              int auto_increment
        primary key,
    email                varchar(255)                                                                null,
    first_name           varchar(255)                                                                null,
    last_name            varchar(255)                                                                null,
    password             varchar(255)                                                                null,
    phone                varchar(255)                                                                null,
    status               enum ('ACTIVE', 'DISABLE', 'DELETE')                     default 'ACTIVE'   null,
    user_name            varchar(255)                                                                null,
    user_role            enum ('ADMIN', 'VENDOR', 'CUSTOMER') default 'CUSTOMER' null,
    constraint UK_ob8kqyqqgmefl0aco34akdtpe
        unique (email)
);