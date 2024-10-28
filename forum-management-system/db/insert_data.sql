-- Users Table --

INSERT INTO forum.users (user_id, username, password, first_name, last_name, email)
VALUES (1, 'nikolayvoynov', 'password', 'Nikolay', 'Voynov', 'nikolay.voynov@mail.com');

INSERT INTO forum.users (user_id, username, password, first_name, last_name, email, is_admin)
VALUES (2, 'annavulcheva', 'password', 'Anna', 'Vulcheva', 'anna.vulcheva@mail.com', true);

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

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (6, 'Rich Tradition of Tea and Its Cultural Significance', 'Tea has been cherished for thousands of years, originating in China as a medicinal drink before evolving into a daily staple. Its cultural significance varies across countries, from the elaborate tea ceremonies in Japan to the traditional British afternoon tea. Today, tea is the second most consumed beverage worldwide, celebrated for its diverse flavors and health benefits.', 1);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (7, 'Art and Science of Baking: A Journey Through Time', 'Baking is both an art and a science, with origins that date back to ancient civilizations who used simple ingredients to create bread and pastries. Over the centuries, techniques and recipes evolved, influenced by cultural exchanges and innovations. Today, baking is enjoyed as a popular hobby and profession, bringing joy to many around the globe.', 2);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (8, 'Evolution of Fast Food: From Convenience to Culture', 'Fast food has transformed the way we eat, originating in the United States in the early 20th century with the introduction of automats and drive-ins. The concept of quick and affordable meals revolutionized dining habits, leading to the global dominance of fast food chains. Today, it reflects societal trends, health movements, and cultural preferences.', 3);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (9, 'Secrets of Fermentation: A Culinary Adventure', 'Fermentation is an ancient technique that has shaped human diets for thousands of years, preserving food and enhancing flavors. From yogurt and sauerkraut to kimchi and kombucha, fermented foods are celebrated for their unique tastes and health benefits. This culinary adventure continues to grow, with innovations and trends making fermentation more popular than ever.', 4);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (10, 'Influence of Spices on Global Cuisine', 'Spices have played a crucial role in shaping cuisines around the world, with their origins dating back to ancient trade routes. From the fragrant cinnamon of Sri Lanka to the fiery chili peppers of the Americas, spices not only enhance flavors but also carry cultural significance. Their trade has historically influenced economies and led to exploration and colonization.', 1);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (11, 'Journey of Olive Oil: From Ancient Times to Modern Use', 'Olive oil, often referred to as liquid gold, has a rich history that dates back thousands of years, particularly in Mediterranean cultures. It has been used for cooking, medicinal purposes, and religious rituals. Today, olive oil is celebrated for its health benefits and versatility in the kitchen, making it a staple in kitchens around the world.', 2);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (12, 'Crafting the Perfect Cup of Tea: Tips and Techniques', 'Brewing the perfect cup of tea is an art that involves understanding various factors such as water temperature, steeping time, and tea variety. Different types of tea, from delicate green to robust black, require unique approaches for optimal flavor. This guide will explore the nuances of tea preparation to help enthusiasts elevate their tea-drinking experience.', 3);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (13, 'Sustainable Eating: Embracing Local and Seasonal Foods', 'Sustainable eating focuses on choosing local and seasonal ingredients to reduce environmental impact while supporting local farmers. This approach promotes biodiversity and encourages healthier eating habits. As consumers become more aware of food origins and their ecological footprint, the movement towards sustainability in the food industry continues to grow.', 4);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (14, 'History of Bread: A Staple Food Across Cultures', 'Bread is one of the oldest prepared foods, dating back thousands of years to ancient civilizations. Its variety is vast, reflecting local ingredients and cultural practices. From sourdough in Europe to naan in India, bread serves as a symbol of sustenance and community. Understanding its history reveals much about human civilization and culinary evolution.', 1);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (15, 'Health Benefits of Fermented Foods: Why They Matter', 'Fermented foods have surged in popularity due to their numerous health benefits, including improved digestion and enhanced immunity. Foods like yogurt, kimchi, and kombucha are rich in probiotics, which support gut health. This post explores the science behind fermentation and offers practical tips on incorporating these foods into daily diets.', 2);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (16, 'Exploring Culinary Herbs: A Guide for Home Cooks', 'Culinary herbs have been used for centuries to enhance flavor and nutrition in cooking. This guide will delve into the most common herbs, their culinary uses, and health benefits. From basil and thyme to cilantro and rosemary, learning how to grow and use these herbs can elevate any home-cooked meal.', 3);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (17, 'Advent of Food Technology: Changing the Way We Eat', 'Food technology has revolutionized how we produce, preserve, and consume food. Innovations such as freeze-drying, genetic modification, and plant-based alternatives are shaping the future of food. This post examines the impact of technology on food safety, sustainability, and nutritional value, highlighting both advancements and ethical considerations.', 4);


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
VALUES (9, 2, 4, 'The history of chocolate is as rich as its flavor, tracing back to the ancient civilizations of Mesoamerica. The Mayans and Aztecs used cacao in sacred rituals, consumed it as a bitter drink, and even saw it as a form of currency.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (10, 1, 5, 'Ice cream is such a timeless treat. I’ve always wondered where it came from, so it’s cool to learn about its origins. It’s amazing to think that something so simple could have such a long history.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (11, 2, 5, 'I didn’t know ice cream had such debated origins! No matter where it came from, I’m just grateful it exists. It’s interesting to think how it’s evolved from flavored snow to the creamy dessert we all enjoy today.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (12, 2, 5, 'Tea has a rich tradition that goes beyond being just a drink. Its cultural significance spans continents and centuries, playing a central role in social rituals, spiritual practices, and daily routines.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (13, 2, 5, 'Tea carries a long-standing tradition that is far more than just a simple drink. Its cultural importance spans across different continents and centuries, deeply embedded in the social fabric of numerous societies.');


-- Tags Table --


INSERT INTO forum.tags (tag_id, tag_name)
VALUES (1, 'brownie');

INSERT INTO forum.tags (tag_id, tag_name)
VALUES (2, 'soufflé');

INSERT INTO forum.tags (tag_id, tag_name)
VALUES (3, 'foodhistory');

INSERT INTO forum.tags (tag_id, tag_name)
VALUES (4, 'pasta');

INSERT INTO forum.tags (tag_id, tag_name)
VALUES (5, 'desserts');

INSERT INTO forum.tags (tag_id, tag_name)
VALUES (6, 'maincourses');

INSERT INTO forum.tags (tag_id, tag_name)
VALUES (7, 'recipes');

INSERT INTO forum.tags (tag_id, tag_name)
VALUES (8, 'cookingtips');

INSERT INTO forum.tags (tag_id, tag_name)
VALUES (9, 'chocolatedesserts');

INSERT INTO forum.tags (tag_id, tag_name)
VALUES (10, 'italiancuisine');

INSERT INTO forum.tags (tag_id, tag_name)
VALUES (11, 'sweets');

INSERT INTO forum.tags (tag_id, tag_name)
VALUES (12, 'traditionalrecipes');

INSERT INTO forum.tags (tag_id, tag_name)
VALUES (13, 'ingredientstechniques');

INSERT INTO forum.tags (tag_id, tag_name)
VALUES (14, 'cookingadvice');

INSERT INTO forum.tags (tag_id, tag_name)
VALUES (15, 'easyrecipes');

INSERT INTO forum.tags (tag_id, tag_name)
VALUES (16, 'homemadedesserts');

INSERT INTO forum.tags (tag_id, tag_name)
VALUES (17, 'foodfacts');

INSERT INTO forum.tags (tag_id, tag_name)
VALUES (18, 'classicrecipes');


-- Posts_Tags Table --


INSERT INTO forum.posts_tags (post_id, tag_id)
VALUES (1, 1);

INSERT INTO forum.posts_tags (post_id, tag_id)
VALUES (1, 11);

INSERT INTO forum.posts_tags (post_id, tag_id)
VALUES (1, 5);

INSERT INTO forum.posts_tags (post_id, tag_id)
VALUES (2, 2);

INSERT INTO forum.posts_tags (post_id, tag_id)
VALUES (2, 3);

INSERT INTO forum.posts_tags (post_id, tag_id)
VALUES (3, 4);

