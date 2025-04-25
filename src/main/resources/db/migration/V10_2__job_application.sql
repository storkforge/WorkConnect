CREATE TABLE job_application (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    job_id BIGINT REFERENCES job_opportunity_entity(id),
    status VARCHAR(50) DEFAULT 'APPLIED',
    applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_application_user ON job_application(user_id);
CREATE INDEX idx_application_job ON job_application(job_id);