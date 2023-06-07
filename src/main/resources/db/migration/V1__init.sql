CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(20) UNIQUE,
                       status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
                       created_by VARCHAR(250),
                       updated_by VARCHAR(250),
                       created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_on TIMESTAMP,
                       is_deleted BOOLEAN NOT NULL DEFAULT false
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       email VARCHAR(50),
                       password VARCHAR(120),
                       name VARCHAR(120),
                       username VARCHAR(50) UNIQUE,
                       mobile_number VARCHAR(50),
                       created_by VARCHAR(250),
                       updated_by VARCHAR(250),
                       created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_on TIMESTAMP,
                       is_deleted BOOLEAN NOT NULL DEFAULT false
);

CREATE TABLE user_roles (
                            id BIGSERIAL PRIMARY KEY,
                            user_id INT NOT NULL,
                            role_id INT NOT NULL,
                            status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
                            created_by VARCHAR(250),
                            updated_by VARCHAR(250),
                            created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_on TIMESTAMP,
                            is_deleted BOOLEAN NOT NULL DEFAULT false,
                            FOREIGN KEY (role_id) REFERENCES roles(id),
                            FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_USER');
