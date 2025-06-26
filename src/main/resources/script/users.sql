CREATE TABLE users
(
    id         UUID PRIMARY KEY,
    username   VARCHAR(100) UNIQUE NOT NULL,
    password   TEXT                NOT NULL,
    full_name  VARCHAR(255),
    role_id    INT REFERENCES roles (id),
    created_at TIMESTAMP DEFAULT NOW()
);
