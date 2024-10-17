-- Users Table --

INSERT INTO forum.users (user_id, username, password, first_name, last_name, email)
VALUES (1, 'nikolayvoynov', 'password', 'Nikolay', 'Voynov', 'nikolay.voynov@mail.com');

INSERT INTO forum.users (user_id, username, password, first_name, last_name, email)
VALUES (2, 'annavulcheva', 'password', 'Anna', 'Vulcheva', 'anna.vulcheva@mail.com');

INSERT INTO forum.users (user_id, username, password, first_name, last_name, email)
VALUES (3, 'kirilkirilov', 'password', 'Kiril', 'Kirilov', 'kiril.kirilov@mail.com');

INSERT INTO forum.users (user_id, username, password, first_name, last_name, email)
VALUES (4, 'mihailboychev', 'password', 'Mihail', 'Boychev', 'mihail.boichev@mail.com');


-- Admins Table --

INSERT INTO forum.admins (admin_id, user_id, phone_number)
VALUES (1, 2, '0887435712');


-- Posts Table --

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (1, 'The Sweet History of Brownies: A Delicious Journey', 'Brownies, the beloved chocolate dessert, have a fascinating history that dates back to the late 19th century. It is widely believed that they originated in the United States, and their creation is often attributed to a culinary mishap. According to popular lore, a chef in a Chicago hotel forgot to add baking powder to his chocolate cake recipe, resulting in a dense, fudgy treat that would later be known as the brownie.', 1);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (2, 'The Magic of Soufflés: A Culinary Classic', 'Soufflés are a classic dish that has captivated the hearts of many food lovers since their creation in the 18th century. Originating from France, the soufflé is a testament to culinary technique and artistry. This light and airy dish can be both savory and sweet, with countless variations to explore. The secret to a perfect soufflé lies in the delicate balance of beaten egg whites and a flavorful base, which can be made from ingredients like cheese, chocolate, or fruit. When baked, the soufflé rises beautifully, creating a delightful contrast between its crisp exterior and soft, creamy interior. Despite their reputation for being tricky to make, soufflés are an impressive dish that showcases a cook''s skill and creativity. Whether served as an elegant appetizer, a comforting main course, or a decadent dessert, soufflés bring a touch of magic to any dining experience.', 2);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (3, 'The Journey of Pasta: From Italy to Your Plate', 'Pasta is one of the most beloved and versatile foods around the world, with a rich history that dates back thousands of years. Its origins can be traced to ancient China and the Mediterranean region, but it was in Italy that pasta truly flourished. Italians transformed simple ingredients like flour and water into a myriad of shapes and forms, each with its own unique purpose and pairing. From the long strands of spaghetti to the delicate folds of ravioli, pasta can be adapted to suit a wide range of sauces and ingredients. Today, pasta is celebrated in countless cultures, and it has become a staple in kitchens everywhere. Whether enjoyed in a classic tomato sauce, a creamy Alfredo, or tossed with fresh vegetables, pasta offers endless possibilities for creativity in the kitchen. Its ability to bring people together around the dinner table makes it a cherished part of culinary traditions worldwide.', 3);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (4, 'A History of Chocolate: From Mesoamerica to the Modern World', 'Chocolate has been cherished for thousands of years, dating back to the ancient civilizations of Mesoamerica. The Mayans and Aztecs believed cocoa beans were a gift from the gods, and today, chocolate is enjoyed in countless forms around the world.', 1);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (5, 'The Story Behind the Invention of Ice Cream', 'Ice cream, a delightful frozen dessert, has been around for centuries. Though its exact origin is debated, many credit the Chinese for creating an early version made from snow and flavored with fruit. It was not until the 17th century that ice cream as we know it became popular in Europe.', 2);


-- Replies Table --

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (1, 4, 1, 'Brownies are my favorite dessert! I had no idea they were the result of a baking mishap. That just makes them even more special. I wonder how different they would have been with the baking powder.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (2, 3, 1, 'I love the history behind brownies. The dense, fudgy texture is what makes them perfect in my opinion. Sometimes the best dishes come from the unexpected! Thanks for sharing this delicious story.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (3, 1, 2, 'Soufflés are definitely a challenge to master, but when they turn out right, it’s so rewarding! I once made a chocolate soufflé, and the texture was out of this world. Your description makes me want to try making one again.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (4, 2, 2, 'I had no idea soufflés could be both savory and sweet! I’ve always been too intimidated to try making one, but your post makes it sound like a fun challenge. Maybe I’ll start with a cheese soufflé.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (5, 3, 3, 'Pasta is such a comfort food for me. It’s incredible how many shapes and varieties there are. I recently tried making homemade ravioli, and it’s a whole new level of appreciation for this versatile dish!');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (6, 1, 3, 'It’s fascinating that pasta has roots in both China and Italy. I’ve always thought of it as purely Italian. This post made me want to explore more pasta dishes from different regions. Great read!');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (7, 2, 4, 'Chocolate has such a rich history. I can’t believe it’s been enjoyed for thousands of years! It’s amazing how something that started with the Mayans and Aztecs is now a universal favorite across cultures.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (8, 4, 4, 'I’m always fascinated by the history of chocolate. The way it’s evolved from a bitter drink to the sweet treat we know today is incredible. Thank you for highlighting this delicious journey!');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (9, 1, 5, 'Ice cream is such a timeless treat. I’ve always wondered where it came from, so it’s cool to learn about its origins. It’s amazing to think that something so simple could have such a long history.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (10, 2, 5, 'I didn’t know ice cream had such debated origins! No matter where it came from, I’m just grateful it exists. It’s interesting to think how it’s evolved from flavored snow to the creamy dessert we all enjoy today.');