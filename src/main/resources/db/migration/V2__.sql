ALTER TABLE users
    ADD provider VARCHAR(255);

ALTER TABLE users
    ADD provider_id VARCHAR(255);

ALTER TABLE users
    ALTER COLUMN provider SET DEFAULT 'LOCAL';

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);
