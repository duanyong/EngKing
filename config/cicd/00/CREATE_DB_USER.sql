CREATE SCHEMA IF NOT EXISTS `EngKing` DEFAULT CHARACTER SET 'utf8mb4' ;
CREATE USER 'engking'@'localhost' IDENTIFIED BY 'B3dAs4@a-xl/:k3kus[-=1dsD-[a';
GRANT ALL PRIVILEGES ON EngKing.* TO 'engking'@'localhost' IDENTIFIED BY 'B3dAs4@a-xl/:k3kus[-=1dsD-[a' WITH GRANT OPTION;
FLUSH PRIVILEGES;


CREATE SCHEMA IF NOT EXISTS `EngKing` DEFAULT CHARACTER SET 'utf8mb4' ;
CREATE USER 'engking'@'*' IDENTIFIED BY 'B3dAs4@a-xl/:k3kus[-=1dsD-[a';
GRANT ALL PRIVILEGES ON EngKing.* TO 'engking'@'*' IDENTIFIED BY 'B3dAs4@a-xl/:k3kus[-=1dsD-[a' WITH GRANT OPTION;
FLUSH PRIVILEGES;