# INSERT INTO `elitefle`.`user` (`username`, `password`, `mail`, `name`) VALUES ('Jmmr', '1234', 'Jmmartinezro@gmail.com', 'Juan');
# INSERT INTO `elitefle`.`Teacher` (`idTeacher`) VALUES (LAST_INSERT_ID());

SELECT * FROM elitefle.user WHERE username="Jmmr" AND password="1234";
SELECT * FROM elitefle.teacher;