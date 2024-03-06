DELETE FROM scriptdb.public.scripts;

INSERT INTO scriptdb.public.scripts (id, created_at, updated_at, language_id, name, user_username, key) VALUES
('6a79e600-4d6f-4e1f-894c-1866f2aa6a5d', '2023-04-03 12:00:00', '2023-04-03 12:00:00', 'd318ab48-ea92-4c3d-95b2-1df9cbbf52eb', 'script_python', 'Juan', 'd60289c5-0a1e-43cb-a6bb-3e291c7e69e3'),
('ea429efd-5423-4353-9cb9-ae51c7d2e58f', '2023-04-03 12:00:00', '2023-04-03 12:00:00', 'd22b17a8-277e-40c7-819a-2f1c9c68da67', 'script_java', 'Ana', 'c14b43f6-1c3a-48b3-a789-b868e9caac33'),
('40d2dd43-54b7-48b1-85e7-2b13ee60c7aa', '2023-04-03 12:00:00', '2023-04-03 12:00:00', 'd5ee2b6e-cb52-4b6e-82f4-46f4b4e9e0d1', 'script_nodejs', 'Pedro', 'dcefd501-19a2-4b21-8b13-63ce9d27f1a8'),
('a8e1af98-2127-484e-a53e-c7bfe8a9b9a3', '2023-04-03 12:00:00', '2023-04-03 12:00:00', 'd318ab48-ea92-4c3d-95b2-1df9cbbf52eb', 'script_python', 'María', 'a44a7917-17d5-45c3-a6db-fb6e5c6d5f6d'),
('5e5ca6ba-16d4-4a72-98b1-ef60e47af98a', '2023-04-03 12:00:00', '2023-04-03 12:00:00', 'd22b17a8-277e-40c7-819a-2f1c9c68da67', 'script_java', 'Luis', 'b7759c34-2fc3-435f-8fde-e8f189bb39a3'),
('87033228-ba0d-4809-b3d2-3f1d12a2dc8a', '2023-04-03 12:00:00', '2023-04-03 12:00:00', 'd5ee2b6e-cb52-4b6e-82f4-46f4b4e9e0d1', 'script_nodejs', 'Carlos', 'e92f87ed-6b5d-4e18-bf08-4d4d4c920ad9'),
('ebe8341e-6c0d-485d-bf63-6d7b6d18b6ec', '2023-04-03 10:25:13', '2023-04-03 10:25:13', 'd5ee2b6e-cb52-4b6e-82f4-46f4b4e9e0d1', 'script1', 'Lucía', '96ab576d-92de-4c29-a630-4f4d4ef4ecda'),
('a4f16b49-7a96-4a23-ae3c-48eb1d2e50a2', '2023-04-03 10:25:13', '2023-04-03 10:25:13', 'd318ab48-ea92-4c3d-95b2-1df9cbbf52eb', 'script2', 'Miguel', '9b64a8b3-5b5e-43d1-aa5f-8fca0b57ec5b'),
('9f9f8d53-b10f-407d-831d-7fcb8f31003b', '2023-04-03 10:25:13', '2023-04-03 10:25:13', 'd22b17a8-277e-40c7-819a-2f1c9c68da67', 'script3', 'Elena', '4a4b4f89-ec9f-4df7-929f-6c23122cf2a7'),
('85ec6c32-f6df-464a-9b36-8b16e2ba5681', '2023-04-03 10:25:13', '2023-04-03 10:25:13', 'd5ee2b6e-cb52-4b6e-82f4-46f4b4e9e0d1', 'script1', 'Javier', '7e8e88d5-7d5a-4ab6-8c2b-ff6327d0d3c3'),
('a8f2e67f-cd69-4a3c-8daa-ea22b077aafb', '2023-04-03 10:25:13', '2023-04-03 10:25:13', 'd318ab48-ea92-4c3d-95b2-1df9cbbf52eb', 'script2', 'Sofía', '17d9de4c-af4f-4a4b-8621-4a8b94fc13f4'),
('15e9f9d1-4807-4a05-a24a-07308b2f8b7e', '2023-04-03 10:25:13', '2023-04-03 10:25:13', 'd22b17a8-277e-40c7-819a-2f1c9c68da67', 'script3', 'Alejandro', '908119c1-fa3f-4838-99c5-9446c50f6e08'),
('f93f8c3a-6135-469a-9ee2-c6c2b0646186', '2023-04-03 15:02:16.251', '2023-04-03 15:02:16.251', 'd318ab48-ea92-4c3d-95b2-1df9cbbf52eb', 'Script 2', 'Paula', 'ae0b15c2-eb9d-4889-a7f1-16e3e8b1cc30'),
('d7af067a-8784-4ca4-9d4d-4fa689b4c96b', '2023-04-03 15:02:16.251', '2023-04-03 15:02:16.251', 'd5ee2b6e-cb52-4b6e-82f4-46f4b4e9e0d1', 'Script 3', 'Daniel', 'b3cda3d5-5cdd-4226-8dd4-fdc6c5d6a5f5'),
('87a11e61-1860-46de-bdb6-8e0ab4461b1e', '2023-04-03 15:02:16.251', '2023-04-03 15:02:16.251', 'd318ab48-ea92-4c3d-95b2-1df9cbbf52eb', 'Script 4', 'David', '79020a7a-868c-46ea-9e36-8cc34e614f54'),
('91d7f67f-8f13-4250-914c-cf7d3f3b3d0b', '2023-04-03 15:02:16.251', '2023-04-03 15:02:16.251', 'd22b17a8-277e-40c7-819a-2f1c9c68da67', 'Script 5', 'Cristina', '1c89f72a-5e18-479d-9d31-8c76a86a83f3'),
('37d6fdd3-67b2-4274-b6ad-f374a3304f6c', '2023-04-03 15:02:16.251', '2023-04-03 15:02:16.251', 'd318ab48-ea92-4c3d-95b2-1df9cbbf52eb', 'Script 6', 'Mario', '121e7d87-3908-49cb-b09e-8593071d7e9b'),
('ebe8341e-6c0d-485d-bf63-6d7b6d18b6ed', '2022-12-01 10:30:00', '2022-12-02 11:45:00', 'd5ee2b6e-cb52-4b6e-82f4-46f4b4e9e0d1', 'My NodeJS Script', 'Ana María', '7f0b6f9e-ff85-4df3-b3d3-162743a22b36'),
('ee5b25bb-68f3-412e-ae05-0bdc332e17a8', '2022-12-02 14:30:00', '2022-12-03 15:45:00', 'd318ab48-ea92-4c3d-95b2-1df9cbbf52eb', 'My Python Script', 'Diego', 'd55cf5a5-5f5d-43d3-89c9-9b529af87b3f'),
('fb986d8b-61f6-4ef6-b03d-4a8e16a45b9e', '2022-12-03 10:30:00', '2022-12-04 11:45:00', 'd22b17a8-277e-40c7-819a-2f1c9c68da67', 'My Java Script', 'Laura', 'e5fa5f6a-8b29-4d4f-95a4-43de87c3f3e3');