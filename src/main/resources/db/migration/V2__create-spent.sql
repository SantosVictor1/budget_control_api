CREATE TABLE IF NOT EXISTS spent (
    spent_id varchar(36) NOT NULL PRIMARY KEY,
    value double NOT NULL,
    spent_date timestamp NOT NULL,
    description varchar(255) NOT NULL,
    user_id varchar(36) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;