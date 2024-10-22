# Culinary Forum

## Description

Culinary Forum is forum system following REST API design best practices, 
where registered users can create culinary posts, add replies/ comments 
related to any post and vote for posts they like.

## Set and Start up project

1. Check `application.properties` and set your personal database `url`, `username` and `password`. 
You can find it in `/src/main/resources/application.properties`;
2. Check `build.gradle` and review database dependency.
You can find it in `forum-management-system/build.gradle`;
3. Check `HibernateConfig.java` and set your `Datasource Driver` and `Hibernate Properties`;
4. Set connection with database and use `create.sql` and `insert_data.sql` to create forum database and fill it with data.
You can find them in `forum-management-system/db`.

## Database relations

 <img title="a title" alt="Alt text" src="//db/forum_scheme.jpg">

## Functionality

### Admin `/api/admins`

* `PUT /block/{id}`  - Block User
* `PUT /unblock/{id}`  - Unblock User

### User  `/api/users`

* `POST /register`  - Register User
* `PUT /{id}`  - Update profile

### Post `/api/posts`

* `GET /{id}`  - Get Post 
* `POST /create`  - Create Post
* `PUT /{id}`  - Update Post
* `DELETE /{id}`  - Delete Post

### Reply `api/posts/{postId}/replies`

* `POST /create`  - Create Reply and Add it to Post*
* `PUT /{replyId}`  - Update Reply
* `DELETE /{replyId}`  - Delete Reply

