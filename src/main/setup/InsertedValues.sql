INSERT INTO `elitefle`.`user` (`username`, `password`, `mail`, `name`) VALUES ('Jmmr', '1234', 'Jmmartinezro@gmail.com', 'Juan');
INSERT INTO `elitefle`.`Teacher` (`idUser`, `idTeacher`, `username`, `password`, `mail`, `name`) VALUES (1, 1, 'Jmmr', '1234', 'Jmmartinezro@gmail.com', 'Juan');

INSERT INTO `elitefle`.`user` (`username`, `password`, `mail`, `name`) VALUES ('Earoj', '1234', 'alexrojas235@gmail.com', 'Edward');
INSERT INTO `elitefle`.`Teacher` (`idUser`, `idTeacher`, `username`, `password`, `mail`, `name`) VALUES (2, 2, 'Earoj', '1234', 'alexrojas235@gmail.com', 'Edward');

INSERT INTO `elitefle`.`user` (`username`, `password`, `mail`, `name`) VALUES ('student', '1234', 'alexrojas235@gmail.com', 'Student');
INSERT INTO `elitefle`.`student` (`idUser`, `idStudent`, `username`, `password`, `mail`, `name`) VALUES (3, 3, 'student', '1234', 'alexrojas235@gmail.com', 'Student');


SELECT * FROM elitefle.user;
SELECT * FROM elitefle.teacher;
SELECT * FROM elitefle.student;