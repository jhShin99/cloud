CREATE TABLE board_comment
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    board_id   BIGINT      NOT NULL,
    user_id    VARCHAR(50) NOT NULL,
    content    TEXT        NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);