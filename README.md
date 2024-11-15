# Culinary Forum

![TasteHubLogo](forum-management-system/src/main/resources/static/images/tasteHub-logo.png)

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
5. Basic Authentication is implemented. In Http Headers set Key: `Authorization` and Value: `username password` - check `insert_data.sql` for valid username and password.

## Database relations
You can find it in `forum-management-system/db`.

## Functionality

### Admin `/api/admins`

* `GET /{id}`  - Get user
* `POST /{id}`  - Make user admin 
* `PUT /block/{id}`  - Block user
* `PUT /unblock/{id}`  - Unblock user
* `PUT /{adminId}`  - Update phone number of admin

### User  `/api/users`

* `GET`  - Search user by username, email, first name 
* `GET /{id}`  - Get user
* `POST`  - Register user
* `PUT /{id}`  - Update profile
* `DELETE /{id}`  - Delete user

### Post `/api/posts`

* `GET`  - Posts filter by title, creator, tag and sort by title, likes, ascending, descending
* `GET /{id}`  - Get post
* `GET /most-commented`  - Get 10 most commented posts
* `GET /most-recent`  - Get 10 most recent posts
* `POST`  - Create post
* `PUT /{id}`  - Update post
* `PUT /{id}/like`  - Like post and remove like if second attempt
* `DELETE /{id}`  - Delete post

### Reply `api/posts/{postId}/replies`

* `GET`  - Get replies of post
* `GET /{id}`  - Get reply
* `POST`  - Create reply and add it to post
* `PUT /{replyId}`  - Update reply
* `DELETE /{replyId}`  - Delete reply

