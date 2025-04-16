DO $$
    BEGIN
        IF NOT EXISTS (
            SELECT 1 FROM users WHERE email = 'admin@workconnect.com'
        ) THEN
            INSERT INTO users (name, email, password, provider, provider_id, role)
            VALUES (
                       'admin',
                       'admin@workconnect.com',
                       '$2a$10$ZgN93h4uIsbk1UF1fwZt/OwNRMi1LbYpbUoJ.zZuPtGT.5qpHhWhC',
                       'LOCAL',
                       NULL,
                       'ADMIN'
                   );
        END IF;
    END $$;
