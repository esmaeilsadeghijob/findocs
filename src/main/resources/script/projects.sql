CREATE TABLE projects
(
    id         UUID PRIMARY KEY,
    client_id  UUID REFERENCES clients (id),
    name       VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);
