CREATE TABLE internships
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title       VARCHAR(255),
    company     VARCHAR(255),
    location    VARCHAR(255),
    description VARCHAR(255),
    start_date  VARCHAR(255),
    end_date    VARCHAR(255),
    CONSTRAINT pk_internships PRIMARY KEY (id)
);