CREATE TABLE documents
(
    id              UUID PRIMARY KEY,
    client_id       UUID REFERENCES clients (id),
    project_id      UUID REFERENCES projects (id),
    user_id         UUID REFERENCES users (id),
    document_number VARCHAR(50),
    fiscal_year     VARCHAR(10),
    document_date   DATE,
    description     TEXT,
    nature          VARCHAR(100),
    created_at      TIMESTAMP DEFAULT NOW()
);
