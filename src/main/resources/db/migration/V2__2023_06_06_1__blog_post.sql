CREATE TABLE blog_post (
                           id BIGSERIAL PRIMARY KEY,
                           parent_id BIGINT,
                           user_id INT,
                           lft BIGINT,
                           rgt BIGINT,
                           mptt_value BIGINT,
                           title VARCHAR(255),
                           content TEXT,
                           slug VARCHAR(255),
                           created_by VARCHAR(250),
                           updated_by VARCHAR(250),
                           created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           updated_on TIMESTAMP,
                           is_deleted BOOLEAN NOT NULL DEFAULT false,
                           FOREIGN KEY (parent_id) REFERENCES blog_post(id),
                           FOREIGN KEY (user_id) REFERENCES users(id)
);
