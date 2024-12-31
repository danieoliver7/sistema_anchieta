INSERT INTO users (username, password) 
VALUES ('admin', '123456') 
ON CONFLICT (username) DO NOTHING; 