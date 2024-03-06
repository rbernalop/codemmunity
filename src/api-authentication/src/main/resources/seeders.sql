DELETE FROM authenticationdb.public.users;

INSERT INTO authenticationdb.public.users (id, created_at, updated_at, birth_date, email, name, password_encrypted, surname, username) VALUES
(1, '2022-03-30 14:35:22', '2022-03-30 14:35:22', '1990-01-01', 'usuario1@example.com', 'Juan', 'sjdl92j3slkd', 'Pérez', 'juancito'),
(2, '2022-03-31 10:15:01', '2022-03-31 10:15:01', '1985-05-12', 'usuario2@example.com', 'Ana', 'dfn93k21lnk', 'García', 'anitag'),
(3, '2022-04-01 16:42:55', '2022-04-01 16:42:55', '2000-09-20', 'usuario3@example.com', 'Pedro', 'slkdn3i2sd', 'Rodríguez', 'pedrito'),
(4, '2022-04-02 08:12:42', '2022-04-02 08:12:42', '1995-07-10', 'usuario4@example.com', 'María', 'sdp82nsl39d', 'López', 'marylu'),
(5, '2022-04-02 18:30:11', '2022-04-02 18:30:11', '1988-03-18', 'usuario5@example.com', 'Luis', 'dn2msl39ksd', 'González', 'luisito'),
(6, '2022-04-03 09:05:27', '2022-04-03 09:05:27', '1997-12-25', 'usuario6@example.com', 'Carlos', 'ms3n2slkd9', 'Martínez', 'carlitos'),
(7, '2022-04-03 14:20:59', '2022-04-03 14:20:59', '1992-09-03', 'usuario7@example.com', 'Lucía', 'kd9s2msl33', 'Fernández', 'lucilu'),
(8, '2022-04-04 11:45:12', '2022-04-04 11:45:12', '1984-06-29', 'usuario8@example.com', 'Miguel', 'ks23ndl9sl', 'Ruiz', 'migue'),
(9, '2022-04-04 16:55:23', '2022-04-04 16:55:23', '1993-02-14', 'usuario9@example.com', 'Elena', 'ld9sn23lksd', 'Gutiérrez', 'elenita'),
(10, '2022-04-05 08:07:36', '2022-04-05 08:07:36', '1991-11-22', 'usuario10@example.com', 'Javier', 'sd9ms23lksd', 'Sánchez', 'javi'),
(11, '2022-04-06 14:25:08', '2022-04-06 14:25:08', '1987-04-07', 'usuario11@example.com', 'Sofía', 'ksd39l2msl', 'Díaz', 'sofi'),
(12, '2022-04-07 09:35:41', '2022-04-07 09:35:41', '1982-10-15', 'usuario12@example.com', 'Alejandro', 'slkd29n3sl', 'Jiménez', 'ale'),
(13, '2022-04-07 16:40:59', '2022-04-07 16:40:59', '1996-08-02', 'usuario13@example.com', 'Paula', 'slkd9n23lks', 'Moreno', 'paulita'),
(14, '2022-04-08 12:15:10', '2022-04-08 12:15:10', '1989-01-30', 'usuario14@example.com', 'Daniel', 'slkd9n23lks', 'Álvarez', 'dani'),
(15, '2022-04-08 19:25:33', '2022-04-08 19:25:33', '1998-05-09', 'usuario15@example.com', 'David', 'lskd9n2s3l', 'Pérez', 'davido'),
(16, '2022-04-09 10:45:02', '2022-04-09 10:45:02', '1990-12-11', 'usuario16@example.com', 'Cristina', 'd92msl33lk', 'Gómez', 'cristi'),
(17, '2022-04-09 16:55:22', '2022-04-09 16:55:22', '1994-02-22', 'usuario17@example.com', 'Mario', 'kd93nslk32', 'Romero', 'marioo'),
(18, '2022-04-10 08:10:05', '2022-04-10 08:10:05', '1986-09-12', 'usuario18@example.com', 'Ana María', 'ksn32lk3sd', 'Álvarez', 'anam'),
(19, '2022-04-10 18:30:42', '2022-04-10 18:30:42', '1999-11-01', 'usuario19@example.com', 'Diego', 'ldk32l9snd', 'Hernández', 'diegui'),
(20, '2022-04-11 09:15:16', '2022-04-11 09:15:16', '1983-08-08', 'usuario20@example.com', 'Laura', 'sdn32ksl33', 'Torres', 'lau');