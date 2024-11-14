-- Users Table --

INSERT INTO forum.users (user_id, username, password, first_name, last_name, email)
VALUES (1, 'nikolayvoynov', 'password', 'Nikolay', 'Voynov', 'nikolay.voynov@mail.com');

INSERT INTO forum.users (user_id, username, password, first_name, last_name, email, is_admin)
VALUES (2, 'annavulcheva', 'password', 'Anna', 'Vulcheva', 'anna.vulcheva@mail.com', true);

INSERT INTO forum.users (user_id, username, password, first_name, last_name, email)
VALUES (3, 'kirilkirilov', 'password', 'Kiril', 'Kirilov', 'kiril.kirilov@mail.com');

INSERT INTO forum.users (user_id, username, password, first_name, last_name, email)
VALUES (4, 'mihailboychev', 'password', 'Mihail', 'Boychev', 'mihail.boichev@mail.com');

INSERT INTO forum.users (user_id, username, password, first_name, last_name, email)
VALUES (5, 'edisismailov', 'password', 'Edis', 'Ismailov', 'edis.ismailov@mail.com');

INSERT INTO forum.users (user_id, username, password, first_name, last_name, email)
VALUES (6, 'simonankov', 'password', 'Simon', 'Ankov', 'simon.ankov@mail.com');

INSERT INTO forum.users (user_id, username, password, first_name, last_name, email)
VALUES (7, 'vladislavdimitrov', 'password', 'Vladislav', 'Dimitrov', 'vladislav.dimitrov@mail.com');

INSERT INTO forum.users (user_id, username, password, first_name, last_name, email)
VALUES (8, 'emiliyangeshkov', 'password', 'Emiliyan', 'Geshkov', 'emiliyan.geshkov@mail.com');

INSERT INTO forum.users (user_id, username, password, first_name, last_name, email)
VALUES (9, 'elitsasavova', 'password', 'Elitsa', 'Savova', 'elitsa.savova@mail.com');

INSERT INTO forum.users (user_id, username, password, first_name, last_name, email)
VALUES (10, 'boyanhadjiev', 'password', 'Boyan', 'Hadjiev', 'boyan.hadjiev@mail.com');

INSERT INTO forum.users (user_id, username, password, first_name, last_name, email)
VALUES (11, 'petarraykov', 'password', 'Petar', 'Raykov', 'petar.raykov@mail.com');


-- Admins Table --


INSERT INTO forum.admins (admin_id, user_id, phone_number)
VALUES (1, 2, '0887435712');


-- Posts Table --


INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (1, 'The Sweet History of Brownies: A Delicious Journey', 'Brownies, the beloved chocolate dessert cherished around the world, have a fascinating history that traces back to the late 19th century. Widely believed to have originated in the United States, brownies stand as a quintessential American dessert. Their creation is often attributed to a culinary mishap, an accidental innovation that became a delicious discovery. According to popular lore, a chef working in the Palmer House Hotel in Chicago forgot to add baking powder to a chocolate cake recipe, resulting in a dense, fudgy, and slightly chewy treat that would later become known as the brownie. Since then, brownies have evolved into a dessert with various adaptations, a dedicated fanbase, and a lasting cultural impact.
<p>Chicago in the late 19th century was a bustling, innovative city, especially when it came to food and culinary experiments. The Palmer House Hotel, owned by Bertha Palmer, was one of the grandest hotels in the city, attracting wealthy patrons, celebrities, and even royalty. In 1893, in preparation for the World’s Columbian Exposition (Chicago’s World Fair), Bertha Palmer asked the hotel''s pastry chefs to create a portable dessert that could be easily served in boxed lunches and enjoyed on the go. It needed to be smaller than a cake, easier to eat than a pie, and rich in chocolate to satisfy her elite guests.</p>
<blockquote>The result was a dense, rich, chocolatey square that could be picked up and eaten without utensils. This original brownie, made with walnuts and an apricot glaze on top, is still made at the Palmer House Hotel today. It’s a nod to both the city’s culinary history and the hotel’s legacy, and it stands as one of the oldest known versions of what we now recognize as the brownie.</blockquote>'
    '<p>While Bertha Palmer’s brownie is a documented piece of culinary history, another legend suggests that the brownie was created purely by accident. According to this story, a home baker or chef, possibly in the Midwest, mistakenly left out baking powder from a chocolate cake recipe, causing the dessert to lack the rise and fluffiness of a typical cake. Instead, the treat turned out dense and chewy, with a rich chocolate flavor. Rather than discarding it, the baker cut it into squares and served it as a new kind of dessert, and so the brownie was born.</p>',
        1);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (2, 'The Magic of Soufflés: A Culinary Classic',
        'Soufflés are a classic dish that has captivated the hearts of food lovers for centuries, dating back to their creation in 18th-century France. Known for their delicate structure and unique texture, the soufflé is a true testament to both culinary technique and artistry. This airy dish can be prepared in a multitude of ways, showcasing either savory or sweet flavors. Though it has a reputation for being challenging to perfect, mastering a soufflé is a rewarding experience for any cook.

<p>At its core, a soufflé is defined by its lightness, which is achieved through a combination of carefully beaten egg whites and a flavorful base. This base can vary widely, offering nearly endless possibilities for creative experimentation. From sharp, nutty cheeses in a savory soufflé to rich, velvety chocolate or fresh, tart fruit in a dessert soufflé, there is a version of this dish for every palate.</p> <p>The secret to achieving a perfectly risen soufflé lies in the egg whites. When beaten to stiff peaks, they create a fluffy, airy texture that gives the soufflé its distinctive rise. However, this process requires precision and patience, as over- or under-whipping the egg whites can lead to a disappointing result. Once the egg whites are expertly incorporated into the base, the soufflé is baked at a high temperature, causing the air trapped within to expand and produce that beautiful, towering appearance.</p> <blockquote>"The soufflé, with its fragile, cloud-like structure, is a dish that demands both skill and attention. But for those who succeed, it is a magical reward." </blockquote> <p>Though the soufflé’s elegant rise may intimidate some, it’s worth remembering that the soufflé isn’t about perfection; rather, it’s about the experience of creating and sharing a special dish. Soufflés are traditionally served as an appetizer, a main course, or a dessert, allowing for flexibility in a meal. A cheese soufflé, for instance, is a wonderful option for a savory appetizer or even a light, satisfying main course. Conversely, a dessert soufflé—whether chocolate, vanilla, or fruit-flavored—makes a stunning finale, with its warm, creamy interior contrasting against a crisp exterior.</p> <p>Whether served in an upscale restaurant or created in a home kitchen, the soufflé brings an undeniable touch of magic to any dining experience. Each soufflé, regardless of flavor, embodies a blend of technique, patience, and culinary artistry. For those willing to take on the challenge, making a soufflé is more than just a recipe; it’s an opportunity to bring a bit of France''s culinary heritage into one’s own kitchen, one airy bite at a time.</p>',
        2);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (3, 'The Journey of Pasta: From Italy to Your Plate',
        'Pasta is one of the most beloved and versatile foods worldwide, with a rich history spanning thousands of years. Its origins are often traced back to ancient China and the Mediterranean, but it was in Italy that pasta truly flourished. Italians transformed simple ingredients like flour and water into a variety of shapes and forms, each designed for unique culinary purposes. From the long, thin strands of spaghetti to the stuffed delicacy of ravioli, pasta is adaptable to countless sauces and ingredients, making it an enduring staple in kitchens across the globe.

<p>The history of pasta is as diverse as its many forms. Early records suggest that ancient Chinese civilizations made a type of noodle, while the Greeks and Romans were creating dough-based foods that resemble pasta in the Mediterranean. However, it was the Italians who turned pasta-making into an art form, developing specific shapes to hold different sauces, flavors, and ingredients. These various forms of pasta are not only functional but also reflect regional Italian identities and traditions.</p> <p>One of the beauties of pasta is its compatibility with an endless array of sauces and toppings. A classic tomato sauce is perhaps the most iconic pairing, providing a comforting, tangy base that complements pasta''s soft texture. For those who crave a richer, creamier experience, Alfredo or carbonara sauces create a velvety coating over the pasta, offering a satisfying, indulgent meal. Meanwhile, fresh, vegetable-based sauces allow for a lighter, health-conscious option that captures the vibrant flavors of seasonal produce. Pasta’s adaptability makes it a canvas for any culinary inspiration, whether it be Italian, Mediterranean, or even Asian-inspired dishes.</p> <blockquote>"Pasta is more than just food; it’s a universal symbol of comfort and togetherness, bringing people around the table to share in a moment of warmth and satisfaction."</blockquote> <p>Today, pasta is celebrated in countless cultures and enjoyed by people from all walks of life. In Italian families, making pasta from scratch is often a cherished tradition, passed down through generations. In other parts of the world, pasta has been embraced and adapted to fit local flavors and ingredients, blending seamlessly into diverse culinary traditions. For instance, Japanese cuisine features pasta-like noodles such as udon and soba, while American kitchens have popularized fusion dishes that pair pasta with flavors from around the globe.</p> <p>Whether served at a cozy family dinner or as the centerpiece of a gourmet meal, pasta has an unmatched ability to bring people together. Its versatility and comforting nature make it a beloved dish that spans continents and cultures. From simple weeknight meals to elaborate dinner gatherings, pasta’s role in culinary traditions worldwide is a testament to its timeless appeal. Whatever the occasion, a warm bowl of pasta embodies nourishment, togetherness, and endless possibilities for creativity in the kitchen.</p>',
        3);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (4, 'A History of Chocolate: From Mesoamerica to the Modern World',
        'Chocolate has been cherished for thousands of years, dating back to the ancient civilizations of Mesoamerica. The Mayans and Aztecs were among the first to cultivate cocoa, viewing it as a divine gift and often using cocoa beans as currency. To these early civilizations, chocolate was more than a mere treat—it was a revered symbol of life, energy, and prosperity. Fast forward to today, and chocolate has evolved into one of the most universally loved indulgences, enjoyed in a myriad of forms around the world.

<p>Initially consumed as a bitter drink, chocolate has undergone significant transformation over the centuries. Early Mesoamerican chocolate was often mixed with spices, chili, and water, creating a rich, flavorful beverage. When Spanish explorers brought cocoa to Europe in the 16th century, sugar was added, revolutionizing chocolate and sparking its popularity across the continent. Soon, chocolate houses emerged in cities like London and Paris, where people gathered to enjoy this exotic, luxurious drink. Over time, advancements in processing techniques, such as the invention of cocoa powder and milk chocolate, transformed chocolate into the smooth, creamy confection we know and love today.</p> <p>Chocolate’s journey from an ancient beverage to a modern-day staple is a story of innovation and cultural exchange. Today, chocolate comes in a vast range of forms and flavors, from dark, milk, and white chocolate bars to truffles, bonbons, and ganaches. It is celebrated worldwide in numerous culinary traditions, appearing in cakes, cookies, pastries, and even savory dishes. Each form of chocolate has its own unique appeal, whether it’s the intense, earthy flavor of dark chocolate or the sweet, comforting taste of milk chocolate.</p> <blockquote>"Chocolate is more than just a treat; it’s an experience that brings joy, comfort, and indulgence with every bite."</blockquote> <p>One of the reasons for chocolate’s widespread appeal is its ability to evoke pleasure and nostalgia. For many, chocolate is associated with special occasions, holidays, and celebrations. It’s a go-to gift on Valentine’s Day, a key ingredient in birthday cakes, and an essential part of winter holiday traditions. Chocolate’s versatility allows it to pair beautifully with a wide range of flavors, such as mint, fruit, caramel, and spices, making it a beloved ingredient in kitchens and patisseries worldwide.</p> <p>Beyond its role in confections, chocolate also has a significant cultural and economic impact. The global demand for chocolate supports a vast industry that spans countries and continents, particularly in cocoa-producing regions of West Africa, Latin America, and Southeast Asia. In these areas, cocoa farming provides livelihood to millions of small-scale farmers. As consumer awareness grows, there is also an increasing demand for fair-trade and sustainably sourced chocolate, encouraging ethical practices within the industry.</p> <p>In any form, chocolate remains a symbol of joy, indulgence, and connection. Its journey through history, from a sacred Mesoamerican beverage to a global favorite, reflects its enduring power to captivate hearts and taste buds alike. With every bite, chocolate continues to offer a little bit of magic, reminding us of its rich history and the timeless pleasure it brings to our lives.</p>',
        1);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (5, 'The Story Behind the Invention of Ice Cream',
        'Ice cream, a delightful frozen dessert, has been enjoyed for centuries and remains a beloved treat worldwide. Its exact origins are debated, but many credit ancient China with the earliest form of ice cream, a dessert made from snow mixed with fruit and honey. As this chilly indulgence spread through different cultures, it evolved in flavor, texture, and technique, eventually making its way to Europe, where it truly began to resemble the ice cream we know today.

<p>It wasn’t until the 17th century that ice cream gained popularity in Europe. Italian and French chefs experimented with frozen custards and creams, refining recipes and techniques to make the dessert smoother and creamier. This refined dessert quickly captivated the European elite, who considered ice cream a luxury and a mark of sophistication. England’s Charles I was said to be so enchanted by it that he paid his chef to keep the recipe a royal secret. As ice cream spread across Europe, each country developed its own twist on the treat, from Italy’s creamy gelato to France’s frozen custards.</p> <p>The introduction of ice cream to the United States in the 18th century helped transform it from a delicacy for the wealthy into a treat for all. With advancements in refrigeration technology and the invention of the hand-crank ice cream freezer in the 19th century, ice cream became widely accessible. This availability led to the rise of ice cream parlors and soda fountains, making ice cream a beloved part of American culture. By the early 20th century, the invention of the ice cream cone and the development of commercial ice cream production had cemented its place as a popular dessert worldwide.</p> <blockquote>"Ice cream is more than a dessert; it’s a sweet escape that brings joy and refreshment with each creamy, chilled spoonful."</blockquote> <p>One of ice cream’s greatest strengths lies in its versatility. From classic vanilla and chocolate to more adventurous flavors like lavender honey or salted caramel, there is an ice cream flavor for every palate. Regional specialties also contribute to ice cream’s charm; for example, Italy’s gelato is known for its dense, creamy texture, while Japan’s matcha-flavored ice cream provides a unique taste experience. This wide variety of flavors and styles allows people around the world to put their own cultural stamp on the dessert.</p> <p>Beyond its rich history, ice cream continues to hold a special place in both celebrations and everyday life. It’s a staple at birthday parties, a refreshing treat on hot summer days, and a comforting indulgence during cozy nights in. Ice cream''s appeal crosses all ages, making it a universal symbol of joy and nostalgia. Its role in creating happy memories, combined with its adaptability to countless flavors and textures, has helped ice cream remain a timeless dessert enjoyed across generations and cultures.</p> <p>Whether scooped from a tub, served in a cone, or topped with whipped cream and sprinkles, ice cream is a sweet tradition that never loses its charm. Its centuries-long journey from a simple, snow-based treat to the beloved dessert it is today reflects its enduring power to refresh, delight, and bring people together over a shared love for that perfect, frozen spoonful.</p>',
        2);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (6, 'Rich Tradition of Tea and Its Cultural Significance',
        'Tea has been cherished for thousands of years, originating in China as a medicinal drink before evolving into a daily staple. Its cultural significance varies across countries, from the elaborate tea ceremonies in Japan to the traditional British afternoon tea. Today, tea is the second most consumed beverage worldwide, celebrated for its diverse flavors and health benefits.',
        1);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (7, 'Art and Science of Baking: A Journey Through Time',
        'Baking is both an art and a science, with origins that date back to ancient civilizations who used simple ingredients to create bread and pastries. Over the centuries, techniques and recipes evolved, influenced by cultural exchanges and innovations. Today, baking is enjoyed as a popular hobby and profession, bringing joy to many around the globe.',
        2);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (8, 'Evolution of Fast Food: From Convenience to Culture',
        'Fast food has transformed the way we eat, originating in the United States in the early 20th century with the introduction of automats and drive-ins. The concept of quick and affordable meals revolutionized dining habits, leading to the global dominance of fast food chains. Today, it reflects societal trends, health movements, and cultural preferences.',
        3);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (9, 'Secrets of Fermentation: A Culinary Adventure',
        'Fermentation is an ancient technique that has shaped human diets for thousands of years, preserving food and enhancing flavors. From yogurt and sauerkraut to kimchi and kombucha, fermented foods are celebrated for their unique tastes and health benefits. This culinary adventure continues to grow, with innovations and trends making fermentation more popular than ever.',
        4);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (10, 'Influence of Spices on Global Cuisine',
        'Spices have played a crucial role in shaping cuisines around the world, with their origins dating back to ancient trade routes. From the fragrant cinnamon of Sri Lanka to the fiery chili peppers of the Americas, spices not only enhance flavors but also carry cultural significance. Their trade has historically influenced economies and led to exploration and colonization.',
        1);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (11, 'Journey of Olive Oil: From Ancient Times to Modern Use',
        'Olive oil, often referred to as liquid gold, has a rich history that dates back thousands of years, particularly in Mediterranean cultures. It has been used for cooking, medicinal purposes, and religious rituals. Today, olive oil is celebrated for its health benefits and versatility in the kitchen, making it a staple in kitchens around the world.',
        2);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (12, 'Crafting the Perfect Cup of Tea: Tips and Techniques',
        'Brewing the perfect cup of tea is an art that involves understanding various factors such as water temperature, steeping time, and tea variety. Different types of tea, from delicate green to robust black, require unique approaches for optimal flavor. This guide will explore the nuances of tea preparation to help enthusiasts elevate their tea-drinking experience.',
        3);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (13, 'Sustainable Eating: Embracing Local and Seasonal Foods',
        'Sustainable eating focuses on choosing local and seasonal ingredients to reduce environmental impact while supporting local farmers. This approach promotes biodiversity and encourages healthier eating habits. As consumers become more aware of food origins and their ecological footprint, the movement towards sustainability in the food industry continues to grow.',
        4);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (14, 'History of Bread: A Staple Food Across Cultures',
        'Bread is one of the oldest prepared foods, dating back thousands of years to ancient civilizations. Its variety is vast, reflecting local ingredients and cultural practices. From sourdough in Europe to naan in India, bread serves as a symbol of sustenance and community. Understanding its history reveals much about human civilization and culinary evolution.',
        1);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (15, 'Health Benefits of Fermented Foods: Why They Matter',
        'Fermented foods have surged in popularity due to their numerous health benefits, including improved digestion and enhanced immunity. Foods like yogurt, kimchi, and kombucha are rich in probiotics, which support gut health. This post explores the science behind fermentation and offers practical tips on incorporating these foods into daily diets.',
        2);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (16, 'Exploring Culinary Herbs: A Guide for Home Cooks',
        'Culinary herbs have been used for centuries to enhance flavor and nutrition in cooking. This guide will delve into the most common herbs, their culinary uses, and health benefits. From basil and thyme to cilantro and rosemary, learning how to grow and use these herbs can elevate any home-cooked meal.',
        3);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (17, 'Advent of Food Technology: Changing the Way We Eat',
        'Food technology has revolutionized how we produce, preserve, and consume food. Innovations such as freeze-drying, genetic modification, and plant-based alternatives are shaping the future of food. This post examines the impact of technology on food safety, sustainability, and nutritional value, highlighting both advancements and ethical considerations.',
        4);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (18, 'The Future of Cooking: Embracing Renewable Ingredients',
        'As sustainability becomes a key focus in the culinary world, renewable ingredients are making a mark in kitchens. This post explores how using locally sourced, organic, and renewable food ingredients is transforming the way we cook and eat, benefiting both health and the environment.',
        7);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (19, 'AI in the Kitchen: Opportunities for Smarter Cooking',
        'Artificial intelligence is beginning to change the culinary landscape with smart kitchen appliances, meal planning, and recipe recommendations. This post discusses how AI is enhancing the cooking experience, making meal preparation more efficient and personalized.',
        3);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (20, 'Smart Kitchens: The Rise of Connected Cooking Tools',
        'Smart kitchen technology is revolutionizing the way we prepare food. With IoT-enabled appliances and advanced cooking tools, kitchens are becoming more connected, efficient, and versatile. This post explores the advantages and challenges of integrating smart devices into the kitchen.',
        9);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (21, 'Blockchain in the Culinary World: A Taste of Transparency',
        'Blockchain technology is finding its way into the culinary world, offering enhanced transparency in food sourcing and supply chains. This post analyzes the potential impact of blockchain on food traceability, ensuring consumers know where their food comes from and how it was produced.',
        5);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (22, 'Electric Ovens: The Future of Cooking Efficiency',
        'Electric ovens are becoming more popular due to their efficiency, energy-saving features, and innovative cooking functions. This post examines the benefits and challenges of using electric ovens in the home kitchen, including the potential for more precise and sustainable cooking.',
        2);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (23, 'Virtual Reality in Culinary Education: A New Learning Tool',
        'Virtual reality (VR) is making waves in culinary education by offering immersive experiences for students to learn cooking techniques and recipes. This post explores the potential of VR in teaching cooking skills and how it can enhance engagement and understanding in culinary programs.',
        10);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (24, 'Social Media and Food Trends: How Platforms Shape Our Palates',
        'Social media has a powerful influence on food trends, from viral recipes to food challenges. This post discusses the impact of social media on culinary culture, including how it shapes our food preferences and influences dining habits around the world.',
        6);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (25, 'The Role of Biotechnology in Modern Agriculture and Cooking',
        'Agricultural biotechnology is advancing food production, with innovations like genetically modified crops and pest-resistant plants. This post explores how biotech is improving food quality, safety, and sustainability, and its potential role in future culinary trends.',
        8);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (26, 'The Gig Economy in Food Delivery: Convenience or Challenge?',
        'The rise of food delivery services has created a new gig economy for workers in the culinary industry. This post explores the pros and cons of gig-based food delivery, looking at how it affects both workers and consumers and the challenges of ensuring fair treatment and quality service.',
        4);

INSERT INTO forum.posts (post_id, title, content, created_by)
VALUES (27, 'Food Security in the Digital Age: Innovation in Meal Planning',
        'In the digital age, technology is playing a major role in enhancing food security through better meal planning, food delivery systems, and digital tools to combat hunger. This post examines how digital innovations are shaping the future of food distribution and accessibility.',
        11);



-- Replies Table --


INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (1, 4, 1,
        'Brownies are my favorite dessert! I had no idea they were the result of a baking mishap. That just makes them even more special. I wonder how different they would have been with the baking powder.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (2, 3, 1,
        'I love the history behind brownies. The dense, fudgy texture is what makes them perfect in my opinion. Sometimes the best dishes come from the unexpected! Thanks for sharing this delicious story.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (3, 1, 2,
        'Soufflés are definitely a challenge to master, but when they turn out right, it’s so rewarding! I once made a chocolate soufflé, and the texture was out of this world. Your description makes me want to try making one again.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (4, 2, 2,
        'I had no idea soufflés could be both savory and sweet! I’ve always been too intimidated to try making one, but your post makes it sound like a fun challenge. Maybe I’ll start with a cheese soufflé.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (5, 3, 3,
        'Pasta is such a comfort food for me. It’s incredible how many shapes and varieties there are. I recently tried making homemade ravioli, and it’s a whole new level of appreciation for this versatile dish!');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (6, 1, 3,
        'It’s fascinating that pasta has roots in both China and Italy. I’ve always thought of it as purely Italian. This post made me want to explore more pasta dishes from different regions. Great read!');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (7, 2, 4,
        'Chocolate has such a rich history. I can’t believe it’s been enjoyed for thousands of years! It’s amazing how something that started with the Mayans and Aztecs is now a universal favorite across cultures.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (8, 4, 4,
        'I’m always fascinated by the history of chocolate. The way it’s evolved from a bitter drink to the sweet treat we know today is incredible. Thank you for highlighting this delicious journey!');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (9, 2, 4,
        'The history of chocolate is as rich as its flavor, tracing back to the ancient civilizations of Mesoamerica. The Mayans and Aztecs used cacao in sacred rituals, consumed it as a bitter drink, and even saw it as a form of currency.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (10, 1, 5,
        'Ice cream is such a timeless treat. I’ve always wondered where it came from, so it’s cool to learn about its origins. It’s amazing to think that something so simple could have such a long history.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (11, 2, 5,
        'I didn’t know ice cream had such debated origins! No matter where it came from, I’m just grateful it exists. It’s interesting to think how it’s evolved from flavored snow to the creamy dessert we all enjoy today.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (12, 2, 5,
        'Tea has a rich tradition that goes beyond being just a drink. Its cultural significance spans continents and centuries, playing a central role in social rituals, spiritual practices, and daily routines.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (13, 2, 5,
        'Tea carries a long-standing tradition that is far more than just a simple drink. Its cultural importance spans across different continents and centuries, deeply embedded in the social fabric of numerous societies.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (14, 3, 7, 'Food is more than just sustenance; it connects people across cultures and generations.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (15, 1, 12, 'The advancements in food preservation have transformed the way we approach meals, allowing us to enjoy flavors from around the world.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (16, 5, 17, 'I find it fascinating how technology is enabling new plant-based foods that taste just like traditional meat products.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (17, 10, 8, 'There is a beauty in traditional recipes, as they hold stories and traditions that span generations.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (18, 4, 23, 'Cultural diversity is wonderfully reflected in the foods we eat and share across the world.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (19, 8, 21, 'As we innovate in food technology, sustainability should remain a key focus to protect our environment.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (20, 7, 15, 'Food brings people together, creating shared experiences and lasting memories.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (21, 9, 26, 'It’s amazing how new preservation methods make food safer and help reduce waste.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (22, 2, 14, 'Food security is becoming more crucial as the global population grows and resources become limited.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (23, 6, 18, 'The way we grow and consume food has a profound impact on both our health and the planet.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (24, 3, 20, 'Plant-based diets are gaining popularity for both health and environmental benefits.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (25, 1, 17, 'There’s something satisfying about creating a meal from scratch, even with the convenience of modern food tech.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (26, 10, 24, 'Modern food production processes have drastically increased the shelf life of many products we rely on.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (27, 5, 9, 'Traditional foods are an essential part of cultural heritage, passed down through generations.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (28, 11, 22, 'It’s fascinating to see how food innovations are shaping our dining experiences.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (29, 8, 12, 'Food waste reduction is a key area where technology can make a significant impact.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (30, 7, 13, 'It’s incredible how globalization allows us to enjoy foods from every corner of the world.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (31, 4, 16, 'The relationship between food and culture is a reflection of history and regional identity.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (32, 6, 25, 'The food industry has made great strides in creating nutritious options for various dietary needs.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (33, 3, 19, 'The ability to preserve food longer is essential for reducing food insecurity globally.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (34, 1, 11, 'Food trends today are increasingly driven by sustainability and ethical considerations.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (35, 2, 18, 'In many cultures, food preparation is a cherished art passed down through families.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (36, 9, 14, 'It’s amazing how food can evoke memories and emotions across different cultures.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (37, 10, 27, 'Food choices today are influenced by health awareness and environmental responsibility.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (38, 4, 15, 'Preserving local food traditions is essential in a world that’s rapidly changing.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (39, 7, 10, 'Food innovation is not just about taste; it’s also about improving nutrition and accessibility.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (40, 5, 22, 'New food tech solutions offer exciting possibilities for sustainable agriculture.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (41, 8, 6, 'The diversity of food around the world reflects the uniqueness of each culture.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (42, 11, 19, 'Sustainable food practices can help ensure that resources are available for future generations.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (43, 6, 13, 'It’s wonderful how certain foods and recipes can tell a story about a culture’s history.');

INSERT INTO forum.replies (reply_id, created_by, post_id, content)
VALUES (44, 2, 26, 'Today, food innovation is focused on balancing taste, health, and environmental impact.');



-- Tags Table --

INSERT INTO forum.tags (tag_id, tag_name)
VALUES (1, 'healthyeating');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (2, 'sustainablefood');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (3, 'organicproduce');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (4, 'farmtotable');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (5, 'localingredients');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (6, 'foodinnovation');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (7, 'plantbased');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (8, 'veganoptions');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (9, 'vegetariandishes');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (10, 'foodpreservation');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (11, 'foodsafety');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (12, 'globalcuisine');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (13, 'ethnicfood');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (14, 'foodsecurity');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (15, 'gmo');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (16, 'foodallergies');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (17, 'glutenfree');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (18, 'dairyfree');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (19, 'foodtechnology');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (20, 'geneticmodification');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (21, 'foodtrends');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (22, 'mealprep');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (23, 'fermentation');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (24, 'artisanalfoods');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (25, 'culinaryarts');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (26, 'foodtourism');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (27, 'slowfood');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (28, 'streetfood');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (29, 'fusioncuisine');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (30, 'zerowastecooking');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (31, 'superfoods');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (32, 'comfortfood');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (33, 'brunch');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (34, 'detox');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (35, 'seasonalfoods');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (36, 'smallbatch');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (37, 'farmersmarket');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (38, 'pickling');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (39, 'sourdough');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (40, 'nutritionalyeast');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (41, 'mealplanning');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (42, 'rawfood');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (43, 'soup');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (44, 'smoothies');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (45, 'keto');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (46, 'paleo');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (47, 'intermittentfasting');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (48, 'superseed');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (49, 'probiotics');
INSERT INTO forum.tags (tag_id, tag_name)
VALUES (50, 'seafood');

-- Posts_Tags Table --

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (1, 1);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (1, 11);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (1, 5);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (2, 2);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (2, 3);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (3, 4);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (3, 7);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (4, 8);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (4, 16);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (5, 12);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (5, 20);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (5, 30);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (6, 19);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (6, 25);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (7, 6);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (7, 22);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (8, 9);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (8, 18);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (9, 13);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (9, 21);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (10, 14);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (10, 27);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (11, 15);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (11, 17);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (12, 24);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (12, 32);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (13, 34);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (13, 45);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (14, 37);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (14, 48);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (15, 40);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (15, 50);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (16, 33);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (16, 31);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (17, 20);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (17, 38);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (18, 26);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (18, 23);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (19, 39);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (19, 29);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (20, 42);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (20, 8);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (21, 28);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (21, 46);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (22, 47);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (22, 5);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (23, 4);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (23, 10);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (24, 43);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (24, 44);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (25, 1);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (25, 11);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (26, 12);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (26, 13);

INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (27, 2);
INSERT INTO forum.posts_tags (post_id, tag_id) VALUES (27, 9);



-- Likes Table --

INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (1, 1, 2);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (2, 1, 3);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (3, 1, 1);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (4, 2, 1);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (5, 2, 2);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (6, 2, 3);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (7, 3, 4);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (8, 3, 5);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (9, 3, 6);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (10, 4, 7);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (11, 4, 8);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (12, 4, 9);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (13, 5, 10);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (14, 5, 11);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (15, 5, 7);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (16, 6, 1);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (17, 6, 2);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (18, 6, 3);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (19, 7, 4);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (20, 7, 5);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (21, 7, 6);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (22, 8, 7);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (23, 8, 8);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (24, 8, 9);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (25, 9, 10);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (26, 9, 11);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (27, 9, 1);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (28, 10, 2);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (29, 10, 3);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (30, 10, 4);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (31, 11, 5);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (32, 11, 6);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (33, 11, 7);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (34, 12, 8);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (35, 12, 9);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (36, 12, 10);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (37, 13, 11);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (38, 13, 1);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (39, 13, 2);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (40, 14, 3);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (41, 14, 4);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (42, 14, 5);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (43, 15, 6);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (44, 15, 7);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (45, 15, 8);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (46, 16, 9);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (47, 16, 10);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (48, 16, 11);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (49, 17, 1);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (50, 17, 2);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (51, 18, 3);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (52, 18, 4);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (53, 18, 5);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (54, 19, 6);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (55, 19, 7);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (56, 20, 8);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (57, 20, 9);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (58, 21, 10);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (59, 21, 11);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (60, 21, 1);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (61, 22, 10);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (62, 22, 4);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (63, 23, 5);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (64, 23, 6);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (65, 24, 7);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (66, 24, 8);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (67, 24, 9);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (68, 25, 8);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (69, 25, 11);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (70, 26, 7);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (71, 26, 8);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (72, 26, 5);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (73, 27, 4);
INSERT INTO forum.likes (like_id, post_id, user_id) VALUES (74, 27, 3);



