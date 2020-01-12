CREATE TABLE IF NOT EXISTS spent (
    spent_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    value double NOT NULL,
    spent_date timestamp NOT NULL,
    description varchar(255) NOT NULL,
    user_id bigint NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;