create table users
(
    user_id    int auto_increment primary key,
    username   varchar(20) not null unique,
    password   varchar(15) not null,
    first_name varchar(32) not null,
    last_name  varchar(32) not null,
    email      varchar(50) not null unique,
    is_blocked boolean     not null default false,

    check (char_length(first_name) >= 4 AND char_length(first_name) <= 32),
    check ( char_length(last_name) >= 4 AND char_length(last_name) <= 32)
);

create table admins
(
    admin_id     int auto_increment primary key,
    user_id      int not null,
    phone_number varchar(15) unique,

    constraint admins_users_user_id_fk
        foreign key (user_id) references users (user_id)
);

create table posts
(
    post_id    int auto_increment primary key,
    title      varchar(64)   not null,
    content    varchar(8192) not null,
    created_by int           ,
    likes      int default 0,

    constraint posts_users_user_id_fk
        foreign key (created_by) references users (user_id) on delete set null,

    check (char_length(title) >= 16 AND char_length(title) <= 64),
    check (char_length(content) >= 32 AND char_length(content) <= 8192)
);

create table replies
(
    reply_id   int auto_increment primary key,
    created_by int           ,
    post_id    int           not null,
    content    varchar(8192) not null,

    constraint replies_users_user_id_fk
        foreign key (created_by) references users (user_id) on delete set null,
    constraint replies_posts_post_id_fk
        foreign key (post_id) references posts (post_id),

    check (char_length(content) >= 32 AND char_length(content) <= 8192)
);