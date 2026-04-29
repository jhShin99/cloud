CREATE TABLE board_like
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    board_id   BIGINT      NOT NULL,
    user_id    VARCHAR(50) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (board_id, user_id)
);