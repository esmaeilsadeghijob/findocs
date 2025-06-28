# findocs



### db
````
INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_USER');



INSERT INTO users (
    id,
    username,
    password,
    full_name,
    role_id,
    created_at,
    approved
)
VALUES (
    gen_random_uuid(),
    'admin',
    '$2a$10$ycEOG4yvTmt2P4eIaISXqeU6ds4ODPeikDTydXu6mrCu3ts2t7Ydm',
    'مدیر سیستم',
    1,
    NOW(),
    true
);


UPDATE users
SET password = '$2a$10$ycEOG4yvTmt2P4eIaISXqeU6ds4ODPeikDTydXu6mrCu3ts2t7Ydm',
    approved = true
WHERE LOWER(username) = 'admin';

````

````
-- ایجاد مشتری نمونه
INSERT INTO client (id, name, created_at)
VALUES ('a815cd56-098e-4b76-98fb-3456eafed920', 'شرکت نمونه', NOW());

-- ایجاد پروژه نمونه
INSERT INTO project (id, name, client_id, created_at)
VALUES ('1b9fc7c4-cfc4-455e-bd50-9e76aa2d441e', 'پروژه نمونه', 'a815cd56-098e-4b76-98fb-3456eafed920', NOW());

````
````
mongo

use findocs_mongo
db.attachments.find().pretty()

````

````
customer   

1 اداره کل نوسازی مدارس استان قم   cid

units   uid
اسناد ذیحسابی
بایگانی امور قراردادها

periods   pid
1401
1402
1403
1404

services   sid
بایگانی اسناد مالی


[همه جداول اصلی  دارای
sid
cid
uid
pid
باشند

````