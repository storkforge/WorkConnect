CREATE TABLE saved_job (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    job_id BIGINT REFERENCES job_opportunity_entity(id),
    saved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);