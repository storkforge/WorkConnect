CREATE TABLE IF NOT EXISTS job_opportunity_entity (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    company_name VARCHAR(255),
    job_title VARCHAR(255),
    job_description VARCHAR(255),
    location VARCHAR(255),
    terms_of_employment VARCHAR(255),
    description VARCHAR(255),
    event_id BIGINT
    );