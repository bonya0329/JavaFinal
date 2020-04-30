insert into t_categories(id,name) values (1,'Accessories');
insert into t_categories(id,name) values (2,'Beauty');
insert into t_categories(id,name) values (3,'Decor');
insert into t_categories(id,name) values (4,'Kids Toy');

insert into t_roles(id, role, description) values (1, 'ROLE_USER', 'User');
insert into t_roles(id, role, description) values (2, 'ROLE_ADMIN', 'Administrator');
insert into t_roles(id, role, description) values (3, 'ROLE_MODERATOR', 'Moderator');

insert into t_users(id, email, password, is_active, name, surname) values (1, 'bonya@gmail.com', '$2y$12$O4jFs4IyQtup9JZOUgln6uFHyirTc6vGYsufS4yabVCdjE15u.zEu',1,'Bayan','Akhmetova');
insert into t_users(id, email, password, is_active, name, surname) values (2, 'toka@gmail.com', '$2y$12$O4jFs4IyQtup9JZOUgln6uFHyirTc6vGYsufS4yabVCdjE15u.zEu',1,'Tokzhan','Bazarbayeva');
insert into t_users(id, email, password, is_active, name, surname) values (3, 'bon@gmail.com', '$2y$12$O4jFs4IyQtup9JZOUgln6uFHyirTc6vGYsufS4yabVCdjE15u.zEu',1,'Bayan','Akhmetova');

insert into t_users_roles(users_id, roles_id) values (1, 1);
insert into t_users_roles(users_id, roles_id) values (2, 2);
insert into t_users_roles(users_id, roles_id) values (3, 3);

insert into t_products(content, image_path, name, price, category_id) values ('<p>hello</p>','product_images/1_sweets/1_kitkat.jpg','Quartz Belt Watch', 250, 1);
insert into t_products(content, image_path, name, price, category_id) values ('<p>hello</p>','product_images/3_Beauty/2_Women Freshwash.jpg','Women Freshwash', 1500, 2);
insert into t_products(content, image_path, name, price, category_id) values ('<p>hello</p>','product_images/4_Decor/3_Room Flash Light.jpg','Room Flash Light', 2500, 3);