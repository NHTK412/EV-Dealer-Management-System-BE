DROP TABLE IF EXISTS `agency`;

CREATE TABLE `agency` (
    `agency_id` int NOT NULL AUTO_INCREMENT,
    `create_at` datetime(6) NOT NULL,
    `update_at` datetime(6) DEFAULT NULL,
    `address` varchar(255) DEFAULT NULL,
    `agencyname` varchar(255) NOT NULL,
    `email` varchar(255) DEFAULT NULL,
    `phone_number` varchar(255) DEFAULT NULL,
    `status` enum('ACTIVE', 'INACTIVE') DEFAULT NULL,
    `type` enum('DEALER', 'MANUFACTURER') DEFAULT NULL,
    PRIMARY KEY (`agency_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 4 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO
    agency (
        create_at,
        address,
        agencyname,
        email,
        phone_number,
        status,
        type
    )
VALUES (
        NOW(6),
        'Ho Chi Minh',
        'Hang Xe',
        'hang@gmail.com',
        '0901234567',
        'ACTIVE',
        'MANUFACTURER'
    );

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
    `employee_id` int NOT NULL AUTO_INCREMENT,
    `create_at` datetime(6) NOT NULL,
    `update_at` datetime(6) DEFAULT NULL,
    `address` varchar(255) DEFAULT NULL,
    `birth_date` date DEFAULT NULL,
    `email` varchar(255) DEFAULT NULL,
    `employee_name` varchar(255) NOT NULL,
    `gender` enum('FEMALE', 'MALE') DEFAULT NULL,
    `password` varchar(255) NOT NULL,
    `phone_number` varchar(255) DEFAULT NULL,
    `role` enum(
        'ROLE_ADMIN',
        'ROLE_DEALER_MANAGER',
        'ROLE_DEALER_STAFF',
        'ROLE_EVM_STAFF'
    ) DEFAULT NULL,
    `status` enum('ACTIVE', 'INACTIVE') DEFAULT NULL,
    `username` varchar(255) NOT NULL,
    `agency_id` int DEFAULT NULL,
    PRIMARY KEY (`employee_id`),
    UNIQUE KEY `UKim8flsuftl52etbhgnr62d6wh` (`username`),
    KEY `FK96didl4v6ohf06sfn07v0iue3` (`agency_id`),
    CONSTRAINT `FK96didl4v6ohf06sfn07v0iue3` FOREIGN KEY (`agency_id`) REFERENCES `agency` (`agency_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 5 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO
    employee (
        create_at,
        address,
        birth_date,
        email,
        employee_name,
        gender,
        password,
        phone_number,
        role,
        status,
        username,
        agency_id
    )
VALUES (
        NOW(6),
        'Binh Duong',
        '2005-12-04',
        'nguyenhuutuankhang412@gmail.com',
        'Nguyen Huu Tuan Khang',
        'MALE',
        '$2a$10$vflCAziHNB5w6URI2NMlxOj4lHcRkwZRwZa1DQRmLYZCRuJzQs6HG', -- Mật khẩu: Evm123@
        '0366408260',
        'ROLE_ADMIN',
        'ACTIVE',
        'admin_user',
        (
            SELECT agency_id
            FROM agency
            WHERE
                agencyname = 'Hang Xe'
        )
    );