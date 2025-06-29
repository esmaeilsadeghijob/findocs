-- اجرای این بخش باید خارج از دیتابیسی که وجود داره انجام بشه (مثلاً از postgres یا template1)
CREATE DATABASE findocsdb
    WITH OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TEMPLATE template0;


-----------
\c findocsdb;

