# create table profile_photos
# (
#     profile_photo_id int auto_increment primary key,
#     url              varchar(255) not null,
#     public_id        varchar(255) not null unique
# );

create table users
(
    user_id    int auto_increment primary key,
    username   varchar(20) not null unique,
    password   varchar(15) not null,
    first_name varchar(32) not null,
    last_name  varchar(32) not null,
    email      varchar(50) not null unique,
    is_admin   boolean     not null default false,
    is_blocked boolean     not null default false,
    profile_picture varchar(255) null,
#     profile_photo_id int,
#
#     constraint users_profile_photos_profile_photo_id_fk
#         foreign key (profile_photo_id) references profile_photos (profile_photo_id) on delete set null,

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
    title      varchar(64)                         not null,
    content    varchar(8192)                       not null,
    created_by int,
    created_at timestamp default current_timestamp not null,
    updated_at timestamp default current_timestamp on update current_timestamp,

    constraint posts_users_user_id_fk
        foreign key (created_by) references users (user_id) on delete set null,

    check (char_length(title) >= 16 AND char_length(title) <= 64),
    check (char_length(content) >= 32 AND char_length(content) <= 8192)
);

create table likes
(
    like_id int auto_increment primary key,
    post_id int,
    user_id int,

    UNIQUE (user_id, post_id),

    constraint likes_users_user_id_fk
        foreign key (user_id) references users (user_id) on delete set null,

    constraint likes_posts_post_id_fk
        foreign key (post_id) references posts (post_id) on delete set null
);

create table replies
(
    reply_id   int auto_increment primary key,
    created_by int,
    post_id    int                                 not null,
    content    varchar(8192)                       not null,
    created_at timestamp default current_timestamp not null,
    updated_at timestamp default current_timestamp on update current_timestamp,

    constraint replies_users_user_id_fk
        foreign key (created_by) references users (user_id) on delete set null,
    constraint replies_posts_post_id_fk
        foreign key (post_id) references posts (post_id),

    check (char_length(content) >= 32 and char_length(content) <= 8192)
);

create table tags
(
    tag_id   int auto_increment primary key,
    tag_name varchar(50) not null unique,

    check (char_length(tag_name) >= 2 and char_length(tag_name) <= 50)
);

create table posts_tags
(
    post_id int,
    tag_id  int,
    primary key (post_id, tag_id),

    foreign key (post_id) references posts (post_id) on delete cascade,
    foreign key (tag_id) references tags (tag_id) on delete cascade
);

