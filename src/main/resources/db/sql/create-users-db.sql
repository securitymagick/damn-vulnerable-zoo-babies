CREATE TABLE users (
  id         INTEGER PRIMARY KEY,
  username VARCHAR(32),
  password  VARCHAR(32),
  favorite VARCHAR(32),
  isUser INTEGER,
  isAdmin INTEGER
);