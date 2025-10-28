USE ev_sales_management;

-- ========== CLEAR EXISTING DATA ==========
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE account;

TRUNCATE TABLE agency;

TRUNCATE TABLE employee;

TRUNCATE TABLE customer;

TRUNCATE TABLE vehicle_category;

TRUNCATE TABLE vehicle_type;

TRUNCATE TABLE vehicle_type_detail;

TRUNCATE TABLE vehicle;

TRUNCATE TABLE orders;

TRUNCATE TABLE order_detail;

TRUNCATE TABLE payment;

TRUNCATE TABLE promotion;

TRUNCATE TABLE promotion_agency;

TRUNCATE TABLE quote;

TRUNCATE TABLE quotation_detail;

TRUNCATE TABLE feedback;

TRUNCATE TABLE test_drive_appointment;

TRUNCATE TABLE vehicle_delivery;

TRUNCATE TABLE warehouse_receipt;

TRUNCATE TABLE warehouse_release_note;

TRUNCATE TABLE import_request;

SET FOREIGN_KEY_CHECKS = 1;

-- ========== INSERT AGENCY ==========
INSERT INTO
    agency (
        create_at,
        update_at,
        address,
        agencyname,
        email,
        phone_number,
        status
    )
VALUES (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        '123 Lê Lợi, Quận 1, TP.HCM',
        'VinFast Quận 1',
        'hcmq1@vinfast.vn',
        '0283-100-0001',
        'Active'
    ),
    (
        '2024-01-02 08:00:00',
        '2024-10-29 10:00:00',
        '456 Nguyễn Huệ, Quận 3, TP.HCM',
        'VinFast Quận 3',
        'hcmq3@vinfast.vn',
        '0283-100-0002',
        'Active'
    ),
    (
        '2024-01-03 08:00:00',
        '2024-10-29 10:00:00',
        '789 Trần Hưng Đạo, Quận 5, TP.HCM',
        'VinFast Quận 5',
        'hcmq5@vinfast.vn',
        '0283-100-0003',
        'Active'
    ),
    (
        '2024-01-04 08:00:00',
        '2024-10-29 10:00:00',
        '321 Phố Huế, Hà Nội',
        'VinFast Hà Nội',
        'hn@vinfast.vn',
        '0243-100-0001',
        'Active'
    );

-- ========== INSERT EMPLOYEE ==========
INSERT INTO
    employee (
        create_at,
        update_at,
        address,
        birth_date,
        email,
        employee_name,
        gender,
        phone_number,
        position,
        agency_id
    )
VALUES (
        '2024-01-05 08:00:00',
        '2024-10-29 10:00:00',
        '100 Phố Huế, Hà Nội',
        '1990-05-15',
        'an.nguyen@vinfast.vn',
        'Nguyễn Văn An',
        'Male',
        '0912-345-678',
        'Manager',
        1
    ),
    (
        '2024-01-06 08:00:00',
        '2024-10-29 10:00:00',
        '50 Nguyễn Huệ, TP.HCM',
        '1992-08-20',
        'bich.tran@vinfast.vn',
        'Trần Thị Bích',
        'Female',
        '0913-456-789',
        'Sales Staff',
        1
    ),
    (
        '2024-01-07 08:00:00',
        '2024-10-29 10:00:00',
        '200 Lê Văn Sỹ, TP.HCM',
        '1988-03-10',
        'tuan.le@vinfast.vn',
        'Lê Minh Tuấn',
        'Male',
        '0914-567-890',
        'Manager',
        2
    ),
    (
        '2024-01-08 08:00:00',
        '2024-10-29 10:00:00',
        '30 Bà Triệu, Hà Nội',
        '1995-11-25',
        'hung.pham@vinfast.vn',
        'Phạm Văn Hùng',
        'Male',
        '0915-678-901',
        'Sales Staff',
        2
    ),
    (
        '2024-01-09 08:00:00',
        '2024-10-29 10:00:00',
        '150 Cộng Hòa, TP.HCM',
        '1993-06-05',
        'huong.vo@vinfast.vn',
        'Võ Thị Hương',
        'Female',
        '0916-789-012',
        'Manager',
        3
    ),
    (
        '2024-01-10 08:00:00',
        '2024-10-29 10:00:00',
        '75 Hai Bà Trưng, Hà Nội',
        '1991-09-12',
        'huy.dang@vinfast.vn',
        'Đặng Quốc Huy',
        'Male',
        '0917-890-123',
        'Sales Staff',
        3
    ),
    (
        '2024-01-11 08:00:00',
        '2024-10-29 10:00:00',
        '111 Sư Vạn Hạnh, TP.HCM',
        '1989-12-01',
        'linh.hoang@vinfast.vn',
        'Hoàng Thị Linh',
        'Female',
        '0918-901-234',
        'Delivery Staff',
        4
    );

-- ========== INSERT ACCOUNT ==========
INSERT INTO
    account (
        create_at,
        update_at,
        last_login_time,
        password,
        role,
        status,
        username,
        employee_id
    )
VALUES (
        '2024-01-05 08:00:00',
        '2024-10-29 10:00:00',
        '2024-10-28 15:30:00',
        'hash_pwd_001',
        'ADMIN',
        'Active',
        'an.nguyen',
        1
    ),
    (
        '2024-01-06 08:00:00',
        '2024-10-29 10:00:00',
        '2024-10-27 14:20:00',
        'hash_pwd_002',
        'STAFF',
        'Active',
        'bich.tran',
        2
    ),
    (
        '2024-01-07 08:00:00',
        '2024-10-29 10:00:00',
        '2024-10-28 16:45:00',
        'hash_pwd_003',
        'MANAGER',
        'Active',
        'tuan.le',
        3
    ),
    (
        '2024-01-08 08:00:00',
        '2024-10-29 10:00:00',
        '2024-10-26 09:15:00',
        'hash_pwd_004',
        'STAFF',
        'Active',
        'hung.pham',
        4
    ),
    (
        '2024-01-09 08:00:00',
        '2024-10-29 10:00:00',
        '2024-10-28 11:00:00',
        'hash_pwd_005',
        'MANAGER',
        'Active',
        'huong.vo',
        5
    ),
    (
        '2024-01-10 08:00:00',
        '2024-10-29 10:00:00',
        '2024-10-25 13:30:00',
        'hash_pwd_006',
        'STAFF',
        'Active',
        'huy.dang',
        6
    ),
    (
        '2024-01-11 08:00:00',
        '2024-10-29 10:00:00',
        '2024-10-28 08:45:00',
        'hash_pwd_007',
        'STAFF',
        'Active',
        'linh.hoang',
        7
    );

-- ========== INSERT CUSTOMER ==========
INSERT INTO
    customer (
        create_at,
        update_at,
        address,
        birth_date,
        customer_name,
        email,
        gender,
        membership_level,
        phone_number
    )
VALUES (
        '2024-02-01 09:00:00',
        '2024-10-29 10:00:00',
        '100 Phố Huế, Hà Nội',
        '1985-04-20',
        'Phan Minh Đức',
        'minh.duc@email.com',
        'Male',
        'VIP',
        '0901-111-2222'
    ),
    (
        '2024-02-02 09:00:00',
        '2024-10-29 10:00:00',
        '50 Nguyễn Huệ, TP.HCM',
        '1988-07-15',
        'Nguyễn Thu Hương',
        'thu.huong@email.com',
        'Female',
        'Regular',
        '0902-333-4444'
    ),
    (
        '2024-02-03 09:00:00',
        '2024-10-29 10:00:00',
        '200 Lê Văn Sỹ, TP.HCM',
        '1992-02-10',
        'Trần Khắc Hùng',
        'khac.hung@email.com',
        'Male',
        'Regular',
        '0903-555-6666'
    ),
    (
        '2024-02-04 09:00:00',
        '2024-10-29 10:00:00',
        '30 Bà Triệu, Hà Nội',
        '1990-11-28',
        'Hoàng Mộng Nhi',
        'mong.nhi@email.com',
        'Female',
        'VIP',
        '0904-777-8888'
    ),
    (
        '2024-02-05 09:00:00',
        '2024-10-29 10:00:00',
        '150 Cộng Hòa, TP.HCM',
        '1987-09-03',
        'Lưu Tấn Tài',
        'tan.tai@email.com',
        'Male',
        'Regular',
        '0905-999-0000'
    ),
    (
        '2024-02-06 09:00:00',
        '2024-10-29 10:00:00',
        '250 Pasteur, TP.HCM',
        '1994-01-12',
        'Đỗ Hữu Phát',
        'huu.phat@email.com',
        'Male',
        'VIP',
        '0906-111-1111'
    ),
    (
        '2024-02-07 09:00:00',
        '2024-10-29 10:00:00',
        '75 Hai Bà Trưng, Hà Nội',
        '1991-03-25',
        'Vũ Thanh Hằng',
        'thanh.hang@email.com',
        'Female',
        'Regular',
        '0907-222-2222'
    );

-- ========== INSERT VEHICLE_CATEGORY ==========
INSERT INTO
    vehicle_category (
        create_at,
        update_at,
        description,
        vehicle_category_name
    )
VALUES (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        'Sedan 4-5 chỗ',
        'Sedan'
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        'SUV dã ngoại',
        'SUV'
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        'Xe gia đình đa dụng',
        'Minivan'
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        'Xe bán tải',
        'Pickup'
    );

-- ========== INSERT VEHICLE_TYPE ==========
INSERT INTO
    vehicle_type (
        create_at,
        update_at,
        description,
        manufacture_year,
        vehicle_type_name
    )
VALUES (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        'SUV điện 5 chỗ',
        2024,
        'VinFast VF e34'
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        'SUV điện cao cấp 5 chỗ',
        2024,
        'VinFast VF 8'
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        'SUV điện hạng sang 7 chỗ',
        2024,
        'VinFast VF 9'
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        'SUV điện nhỏ gọn',
        2024,
        'VinFast VF 5'
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        'Sedan hạng sang',
        2024,
        'VinFast Lux A2.0'
    );

-- ========== INSERT VEHICLE_TYPE_DETAIL ==========
INSERT INTO
    vehicle_type_detail (
        create_at,
        update_at,
        color,
        configuration,
        features,
        price,
        vehicle_image,
        version,
        vehicle_type_id
    )
VALUES (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        'Trắng',
        'Pin 42kWh',
        'ABS, Túi khí, Camera lùi',
        690000000,
        'vf_e34_white.jpg',
        'Eco',
        1
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        'Xanh',
        'Pin 42kWh',
        'ABS, Túi khí x6, Camera 360',
        760000000,
        'vf_e34_blue.jpg',
        'Plus',
        1
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        'Đen',
        'Pin 87.7kWh, 2 motor',
        'ABS, Túi khí x8, Camera 360',
        1200000000,
        'vf8_black.jpg',
        'Eco',
        2
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        'Xám',
        'Pin 87.7kWh, 2 motor',
        'ABS, Túi khí x11, ADAS',
        1450000000,
        'vf8_gray.jpg',
        'Plus',
        2
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        'Đen',
        'Pin 123kWh, 2 motor',
        '7 chỗ, Camera 360',
        1500000000,
        'vf9_black.jpg',
        'Eco',
        3
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        'Trắng',
        'Pin 123kWh, 2 motor',
        '7 chỗ, ADAS',
        1650000000,
        'vf9_white.jpg',
        'Plus',
        3
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        'Đỏ',
        'Pin 37.23kWh',
        'ABS, Túi khí',
        458000000,
        'vf5_red.jpg',
        'Base',
        4
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        'Xanh',
        'Pin 37.23kWh',
        'Camera 360',
        550000000,
        'vf5_blue.jpg',
        'Plus',
        4
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        'Đen',
        '2.0L Turbo',
        'Nội thất da, Điều hòa',
        1080000000,
        'lux_a_black.jpg',
        'Standard',
        5
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        'Bạc',
        '2.0L Turbo',
        'Nội thất da cao cấp',
        1200000000,
        'lux_a_silver.jpg',
        'Premium',
        5
    );

-- ========== INSERT XE (Vehicles) ==========
INSERT INTO
    vehicle (
        create_at,
        update_at,
        chassis_number,
        machine_number,
        status,
        vehicle_condition,
        agency_id,
        vehicle_type_detail_id
    )
VALUES (
        '2024-01-05 08:00:00',
        '2024-10-29 10:00:00',
        'VIN001001',
        'ENG001001',
        'Available',
        'New',
        1,
        1
    ),
    (
        '2024-01-05 08:00:00',
        '2024-10-29 10:00:00',
        'VIN001002',
        'ENG001002',
        'Available',
        'New',
        1,
        2
    ),
    (
        '2024-01-05 08:00:00',
        '2024-10-29 10:00:00',
        'VIN001003',
        'ENG001003',
        'Sold',
        'New',
        1,
        3
    ),
    (
        '2024-01-06 08:00:00',
        '2024-10-29 10:00:00',
        'VIN001004',
        'ENG001004',
        'Available',
        'New',
        1,
        4
    ),
    (
        '2024-01-06 08:00:00',
        '2024-10-29 10:00:00',
        'VIN001005',
        'ENG001005',
        'Available',
        'New',
        2,
        1
    ),
    (
        '2024-01-06 08:00:00',
        '2024-10-29 10:00:00',
        'VIN001006',
        'ENG001006',
        'Sold',
        'New',
        2,
        5
    ),
    (
        '2024-01-07 08:00:00',
        '2024-10-29 10:00:00',
        'VIN001007',
        'ENG001007',
        'Available',
        'New',
        3,
        7
    ),
    (
        '2024-01-07 08:00:00',
        '2024-10-29 10:00:00',
        'VIN001008',
        'ENG001008',
        'Sold',
        'New',
        3,
        8
    ),
    (
        '2024-01-08 08:00:00',
        '2024-10-29 10:00:00',
        'VIN001009',
        'ENG001009',
        'Available',
        'New',
        4,
        9
    ),
    (
        '2024-01-08 08:00:00',
        '2024-10-29 10:00:00',
        'VIN001010',
        'ENG001010',
        'Available',
        'New',
        4,
        10
    );

-- ========== INSERT ORDERS ==========
INSERT INTO
    orders (
        create_at,
        update_at,
        contract_number,
        notes,
        status,
        total_amount,
        agency_id,
        customer_id,
        employee_id
    )
VALUES (
        '2024-02-10 09:30:00',
        '2024-10-29 10:00:00',
        'HĐ001/2024',
        'Khách VIP mua 1 xe VF e34',
        'Completed',
        825000000,
        NULL,
        1,
        1
    ),
    (
        '2024-02-15 10:15:00',
        '2024-10-29 10:00:00',
        'HĐ002/2024',
        'Khách thường mua 1 xe VF 8',
        'Completed',
        1450000000,
        NULL,
        2,
        2
    ),
    (
        '2024-02-20 14:00:00',
        '2024-10-29 10:00:00',
        'HĐ003/2024',
        'Bán sỉ cho đại lý Quận 3',
        'Completed',
        2400000000,
        2,
        NULL,
        1
    ),
    (
        '2024-03-05 11:20:00',
        '2024-10-29 10:00:00',
        'HĐ004/2024',
        'Khách VIP mua 1 xe VF 5',
        'Pending',
        550000000,
        NULL,
        4,
        3
    ),
    (
        '2024-03-10 15:45:00',
        '2024-10-29 10:00:00',
        'HĐ005/2024',
        'Khách thường mua 1 xe Lux A',
        'In Progress',
        1200000000,
        NULL,
        5,
        2
    ),
    (
        '2024-03-15 09:00:00',
        '2024-10-29 10:00:00',
        'HĐ006/2024',
        'Bán sỉ cho đại lý Hà Nội',
        'Pending',
        1650000000,
        4,
        NULL,
        7
    ),
    (
        '2024-03-20 13:30:00',
        '2024-10-29 10:00:00',
        'HĐ007/2024',
        'Khách thường mua 1 xe VF 9',
        'Pending',
        1500000000,
        NULL,
        6,
        5
    );

-- ========== INSERT ORDER_DETAIL ==========
INSERT INTO
    order_detail (
        create_at,
        update_at,
        compulsory_insurance,
        discount,
        discount_percentage,
        license_plate_fee,
        material_insurance,
        quantity,
        registration_tax,
        road_maintenance_mees,
        total_amount,
        vehicle_registration_service_fee,
        wholesale_price,
        order_id,
        vehicle_type_detail_id
    )
VALUES (
        '2024-02-10 09:30:00',
        '2024-10-29 10:00:00',
        555000,
        50000000,
        0,
        1500000,
        8000000,
        1,
        0,
        500000,
        825000000,
        1000000,
        760000000,
        1,
        2
    ),
    (
        '2024-02-15 10:15:00',
        '2024-10-29 10:00:00',
        555000,
        0,
        0,
        1500000,
        12000000,
        1,
        0,
        500000,
        1450000000,
        1000000,
        1450000000,
        2,
        4
    ),
    (
        '2024-02-20 14:00:00',
        '2024-10-29 10:00:00',
        0,
        0,
        5,
        0,
        0,
        2,
        0,
        0,
        2400000000,
        0,
        1200000000,
        3,
        3
    ),
    (
        '2024-03-05 11:20:00',
        '2024-10-29 10:00:00',
        555000,
        0,
        0,
        1500000,
        5000000,
        1,
        0,
        500000,
        550000000,
        1000000,
        458000000,
        4,
        7
    ),
    (
        '2024-03-10 15:45:00',
        '2024-10-29 10:00:00',
        555000,
        0,
        0,
        1500000,
        9000000,
        1,
        0,
        500000,
        1200000000,
        1000000,
        1080000000,
        5,
        9
    ),
    (
        '2024-03-15 09:00:00',
        '2024-10-29 10:00:00',
        0,
        0,
        3,
        0,
        0,
        1,
        0,
        0,
        1650000000,
        0,
        1650000000,
        6,
        6
    ),
    (
        '2024-03-20 13:30:00',
        '2024-10-29 10:00:00',
        555000,
        0,
        0,
        1500000,
        15000000,
        1,
        0,
        500000,
        1500000000,
        1000000,
        1500000000,
        7,
        5
    );

-- ========== INSERT PAYMENT ==========
INSERT INTO
    payment (
        create_at,
        update_at,
        amount,
        due_date,
        number_cycle,
        payment_date,
        payment_form,
        payment_method,
        penalty_amount,
        status,
        vnpaycode,
        order_id
    )
VALUES (
        '2024-02-10 09:30:00',
        '2024-10-29 10:00:00',
        412500000,
        '2024-03-10',
        1,
        '2024-03-08',
        'Two installments',
        'Transfer',
        0,
        'Paid',
        NULL,
        1
    ),
    (
        '2024-02-10 09:30:00',
        '2024-10-29 10:00:00',
        412500000,
        '2024-04-10',
        2,
        NULL,
        'Two installments',
        'Transfer',
        0,
        'Pending',
        NULL,
        1
    ),
    (
        '2024-02-15 10:15:00',
        '2024-10-29 10:00:00',
        1450000000,
        '2024-03-15',
        1,
        '2024-03-14',
        'One time',
        'Transfer',
        0,
        'Paid',
        NULL,
        2
    ),
    (
        '2024-02-20 14:00:00',
        '2024-10-29 10:00:00',
        2400000000,
        '2024-03-20',
        1,
        '2024-03-19',
        'One time',
        'Transfer',
        0,
        'Paid',
        NULL,
        3
    ),
    (
        '2024-03-05 11:20:00',
        '2024-10-29 10:00:00',
        550000000,
        '2024-04-05',
        1,
        NULL,
        'One time',
        'Transfer',
        0,
        'Pending',
        NULL,
        4
    ),
    (
        '2024-03-10 15:45:00',
        '2024-10-29 10:00:00',
        600000000,
        '2024-04-10',
        1,
        NULL,
        'Two installments',
        'Transfer',
        0,
        'Pending',
        NULL,
        5
    ),
    (
        '2024-03-10 15:45:00',
        '2024-10-29 10:00:00',
        600000000,
        '2024-05-10',
        2,
        NULL,
        'Two installments',
        'Transfer',
        0,
        'Pending',
        NULL,
        5
    );

-- ========== INSERT PROMOTION ==========
INSERT INTO
    promotion (
        create_at,
        update_at,
        criteria,
        discount_amount,
        discount_percent,
        end_date,
        promotion_name,
        promotion_type,
        promotion_value,
        start_date,
        status
    )
VALUES (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        'VF e34 purchase',
        50000000,
        NULL,
        '2024-12-31 23:59:59',
        'Ưu đãi VF e34',
        'Amount',
        50000000,
        '2024-01-01 00:00:00',
        'Active'
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        '2 or more vehicles',
        NULL,
        5.00,
        '2024-12-31 23:59:59',
        'Khuyến mãi bán sỉ',
        'Percentage',
        NULL,
        '2024-01-01 00:00:00',
        'Active'
    ),
    (
        '2024-02-01 08:00:00',
        '2024-10-29 10:00:00',
        'VF 5 purchase',
        15000000,
        NULL,
        '2024-08-31 23:59:59',
        'Tặng pin dự phòng',
        'Service',
        15000000,
        '2024-06-01 00:00:00',
        'Active'
    ),
    (
        '2024-03-01 08:00:00',
        '2024-10-29 10:00:00',
        'March promotion',
        NULL,
        3.00,
        '2024-03-31 23:59:59',
        'Khuyến mãi tháng 3',
        'Percentage',
        NULL,
        '2024-03-01 00:00:00',
        'Expired'
    );

-- ========== INSERT QUOTE ==========
INSERT INTO
    quote(
        create_at,
        update_at,
        status,
        total_amount,
        employee_id
    )
VALUES (
        '2024-02-08 10:00:00',
        '2024-10-29 10:00:00',
        'Approved',
        825000000,
        1
    ),
    (
        '2024-02-13 14:30:00',
        '2024-10-29 10:00:00',
        'Approved',
        1450000000,
        2
    ),
    (
        '2024-02-18 11:45:00',
        '2024-10-29 10:00:00',
        'Pending',
        550000000,
        3
    ),
    (
        '2024-03-08 09:15:00',
        '2024-10-29 10:00:00',
        'Approved',
        1200000000,
        2
    );

-- ========== INSERT QUOTATION_DETAIL ==========
INSERT INTO
    quotation_detail (
        create_at,
        update_at,
        compulsory_insurance,
        discount,
        discount_percentage,
        license_plate_fee,
        material_insurance,
        quantity,
        registration_tax,
        road_maintenance_mees,
        total_amount,
        vehicle_registration_service_fee,
        wholesale_price,
        quote_id,
        vehicle_type_detail_id
    )
VALUES (
        '2024-02-08 10:00:00',
        '2024-10-29 10:00:00',
        555000,
        50000000,
        0,
        1500000,
        8000000,
        1,
        0,
        500000,
        825000000,
        1000000,
        760000000,
        1,
        2
    ),
    (
        '2024-02-13 14:30:00',
        '2024-10-29 10:00:00',
        555000,
        0,
        0,
        1500000,
        12000000,
        1,
        0,
        500000,
        1450000000,
        1000000,
        1450000000,
        2,
        4
    ),
    (
        '2024-02-18 11:45:00',
        '2024-10-29 10:00:00',
        555000,
        0,
        0,
        1500000,
        5000000,
        1,
        0,
        500000,
        550000000,
        1000000,
        458000000,
        3,
        7
    ),
    (
        '2024-03-08 09:15:00',
        '2024-10-29 10:00:00',
        555000,
        0,
        0,
        1500000,
        9000000,
        1,
        0,
        500000,
        1200000000,
        1000000,
        1080000000,
        4,
        9
    );

-- ========== INSERT FEEDBACK ==========
INSERT INTO
    feedback (
        create_at,
        update_at,
        feedback_content,
        status,
        feedback_title,
        customer_id
    )
VALUES (
        '2024-02-12 16:00:00',
        '2024-10-29 10:00:00',
        'Xe chạy êm, tiết kiệm pin rất tốt',
        'PROCESSED',
        'Hài lòng với VF e34',
        1
    ),
    (
        '2024-02-17 10:30:00',
        '2024-10-29 10:00:00',
        'Muốn biết thêm về các trạm sạc gần nhà',
        'PROCESSED',
        'Hỏi về trạm sạc',
        2
    ),
    (
        '2024-02-22 14:15:00',
        '2024-10-29 10:00:00',
        'Quan tâm đến xe VF 8, cần tư vấn giá',
        'NOT_YET_PROCESSED',
        'Tư vấn mua xe VF 8',
        4
    ),
    (
        '2024-03-01 11:00:00',
        '2024-10-29 10:00:00',
        'Xe giao có một vài vết xước nhỏ',
        'IN_PROCESSED',
        'Khiếu nại về tình trạng xe',
        5
    ),
    (
        '2024-03-05 09:45:00',
        '2024-10-29 10:00:00',
        'Dịch vụ bảo hành rất tốt và chuyên nghiệp',
        'PROCESSED',
        'Đánh giá dịch vụ bảo hành',
        6
    );

-- ========== INSERT FEEDBACK_HANDLING ==========
INSERT INTO
    feedback_handling (
        create_at,
        update_at,
        feedback_handling_content,
        feedback_handling_method,
        status,
        employee_id,
        feedback_id
    )
VALUES (
        '2024-02-12 16:30:00',
        '2024-10-29 10:00:00',
        'Cảm ơn phản hồi tích cực của khách',
        'PHONE_NUMBER',
        'COMPLETE',
        1,
        1
    ),
    (
        '2024-02-17 11:00:00',
        '2024-10-29 10:00:00',
        'Đã gửi danh sách trạm sạc qua email',
        'EMAIL',
        'COMPLETE',
        2,
        2
    ),
    (
        '2024-03-01 12:00:00',
        '2024-10-29 10:00:00',
        'Nhân viên đang kiểm tra lại xe',
        'PHONE_NUMBER',
        'COMPLETE',
        3,
        4
    ),
    (
        '2024-03-05 10:30:00',
        '2024-10-29 10:00:00',
        'Cảm ơn đánh giá tích cực',
        'EMAIL',
        'COMPLETE',
        5,
        5
    );

-- ========== INSERT TEST_DRIVE_APPOINTMENT ==========
INSERT INTO
    test_drive_appointment (
        create_at,
        update_at,
        date_of_appointment,
        status,
        time_of_appointment,
        customer_id,
        vehicle_id
    )
VALUES (
        '2024-02-05 10:00:00',
        '2024-10-29 10:00:00',
        '2024-02-15',
        'Completed',
        '10:00:00',
        1,
        1
    ),
    (
        '2024-02-12 14:00:00',
        '2024-10-29 10:00:00',
        '2024-02-20',
        'Completed',
        '14:30:00',
        2,
        4
    ),
    (
        '2024-02-18 09:30:00',
        '2024-10-29 10:00:00',
        '2024-02-28',
        'Pending',
        '09:00:00',
        4,
        7
    ),
    (
        '2024-03-01 15:00:00',
        '2024-10-29 10:00:00',
        '2024-03-10',
        'Scheduled',
        '11:00:00',
        6,
        9
    ),
    (
        '2024-03-05 11:00:00',
        '2024-10-29 10:00:00',
        '2024-03-15',
        'Pending',
        '15:00:00',
        7,
        10
    );

-- ========== INSERT VEHICLE_DELIVERY ==========
INSERT INTO
    vehicle_delivery (
        create_at,
        update_at,
        expected_delivery_date,
        delivery_date,
        status,
        employee_id,
        order_id
    )
VALUES (
        '2024-02-10 10:00:00',
        '2024-02-28 15:30:00',
        '2024-02-28 00:00:00',
        '2024-02-28 14:00:00',
        'Completed',
        1,
        1
    ),
    (
        '2024-02-15 11:00:00',
        '2024-03-05 16:45:00',
        '2024-03-05 00:00:00',
        '2024-03-05 15:30:00',
        'Completed',
        2,
        2
    ),
    (
        '2024-02-20 14:00:00',
        '2024-03-10 09:15:00',
        '2024-03-10 00:00:00',
        '2024-03-10 08:45:00',
        'Completed',
        1,
        3
    ),
    (
        '2024-03-05 12:00:00',
        '2024-10-29 10:00:00',
        '2024-04-05 00:00:00',
        NULL,
        'Pending',
        3,
        4
    ),
    (
        '2024-03-10 16:00:00',
        '2024-10-29 10:00:00',
        '2024-04-10 00:00:00',
        NULL,
        'In Progress',
        2,
        5
    );

-- ========== INSERT WAREHOUSE_RECEIPT ==========
INSERT INTO
    warehouse_receipt (
        create_at,
        update_at,
        reason,
        note,
        total_amount,
        warehouse_receipt_date,
        agency_id,
        employee_id
    )
VALUES (
        '2024-01-08 08:00:00',
        '2024-10-29 10:00:00',
        'Import from factory',
        'Nhập 2 xe VF e34 từ nhà máy',
        1450000000,
        '2024-01-08 08:00:00',
        1,
        1
    ),
    (
        '2024-01-10 10:00:00',
        '2024-10-29 10:00:00',
        'Import from factory',
        'Nhập 2 xe VF 8 từ nhà máy',
        2650000000,
        '2024-01-10 10:00:00',
        1,
        1
    ),
    (
        '2024-01-15 09:00:00',
        '2024-10-29 10:00:00',
        'Import from factory',
        'Nhập 2 xe VF 5 từ nhà máy',
        916000000,
        '2024-01-15 09:00:00',
        1,
        2
    ),
    (
        '2024-01-20 11:30:00',
        '2024-10-29 10:00:00',
        'Wholesale purchase',
        'Mua sỉ từ đại lý Quận 1',
        2200000000,
        '2024-01-20 11:30:00',
        2,
        3
    ),
    (
        '2024-02-01 13:00:00',
        '2024-10-29 10:00:00',
        'Transfer from agency',
        'Điều chuyển xe từ đại lý Quận 1',
        760000000,
        '2024-02-01 13:00:00',
        3,
        5
    );

-- ========== INSERT WAREHOUSE_RECEIPT_DETAIL ==========
INSERT INTO
    warehouse_receipt_detail (
        warehouse_receipt_id,
        vehicle_id
    )
VALUES (1, 1),
    (1, 2),
    (2, 3),
    (2, 4),
    (3, 7),
    (3, 8),
    (4, 5),
    (4, 6),
    (5, 9);

-- ========== INSERT WAREHOUSE_RELEASE_NOTE ==========
INSERT INTO
    warehouse_release_note (
        create_at,
        update_at,
        note,
        release_date,
        total_amount,
        employee_id,
        order_id
    )
VALUES (
        '2024-02-10 10:00:00',
        '2024-02-28 15:00:00',
        'Xuất xe VF e34 Plus cho khách lẻ',
        '2024-02-28 14:00:00',
        825000000,
        1,
        1
    ),
    (
        '2024-02-15 11:00:00',
        '2024-03-05 15:00:00',
        'Xuất xe VF 8 cho khách lẻ',
        '2024-03-05 14:30:00',
        1450000000,
        2,
        2
    ),
    (
        '2024-02-20 14:00:00',
        '2024-03-10 08:00:00',
        'Xuất 2 xe VF 8 cho đại lý Quận 3',
        '2024-03-10 08:00:00',
        2400000000,
        1,
        3
    ),
    (
        '2024-03-05 12:00:00',
        '2024-10-29 10:00:00',
        'Chuẩn bị xuất xe VF 5',
        NULL,
        550000000,
        3,
        4
    );

-- ========== INSERT WAREHOUSE_RELEASE_NOTE_DETAIL ==========
INSERT INTO
    warehouse_release_note_detail (
        warehouse_release_note_id,
        vehicle_id
    )
VALUES (1, 2),
    (2, 4),
    (3, 3),
    (3, 5),
    (4, 7);

-- ========== INSERT IMPORT_REQUEST ==========
INSERT INTO
    import_request (
        create_at,
        update_at,
        note,
        status,
        employee_id
    )
VALUES (
        '2024-01-05 09:00:00',
        '2024-10-29 10:00:00',
        'Yêu cầu nhập thêm 3 xe VF e34 để phân phối',
        'Approved',
        1
    ),
    (
        '2024-01-08 10:30:00',
        '2024-10-29 10:00:00',
        'Yêu cầu nhập 2 xe VF 8 cho kho',
        'Approved',
        1
    ),
    (
        '2024-02-15 14:00:00',
        '2024-10-29 10:00:00',
        'Yêu cầu nhập 2 xe VF 9 mới nhất',
        'Pending',
        2
    ),
    (
        '2024-02-20 11:00:00',
        '2024-10-29 10:00:00',
        'Yêu cầu nhập 1 xe Lux A cho khách đặc biệt',
        'Approved',
        3
    );

-- ========== INSERT IMPORT_REQUEST_DETAIL ==========
INSERT INTO
    import_request_detail (
        import_request_id,
        vehicle_type_detail_id,
        create_at,
        update_at,
        quantity
    )
VALUES (
        1,
        1,
        '2024-01-05 09:00:00',
        '2024-10-29 10:00:00',
        3
    ),
    (
        1,
        2,
        '2024-01-05 09:00:00',
        '2024-10-29 10:00:00',
        2
    ),
    (
        2,
        3,
        '2024-01-08 10:30:00',
        '2024-10-29 10:00:00',
        2
    ),
    (
        2,
        4,
        '2024-01-08 10:30:00',
        '2024-10-29 10:00:00',
        1
    ),
    (
        3,
        5,
        '2024-02-15 14:00:00',
        '2024-10-29 10:00:00',
        2
    ),
    (
        3,
        6,
        '2024-02-15 14:00:00',
        '2024-10-29 10:00:00',
        1
    ),
    (
        4,
        9,
        '2024-02-20 11:00:00',
        '2024-10-29 10:00:00',
        1
    );

-- ========== INSERT AGENCY_WHOLESALE_PRICE ==========
INSERT INTO
    agency_wholesale_price (
        create_at,
        update_at,
        end_date,
        minimum_quantity,
        start_date,
        status,
        wholesale_price,
        agency_id,
        vehicle_type_detail_id
    )
VALUES (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        '2024-12-31 23:59:59',
        1,
        '2024-01-01 00:00:00',
        'Active',
        650000000,
        2,
        1
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        '2024-12-31 23:59:59',
        1,
        '2024-01-01 00:00:00',
        'Active',
        720000000,
        2,
        2
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        '2024-12-31 23:59:59',
        1,
        '2024-01-01 00:00:00',
        'Active',
        1100000000,
        2,
        3
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        '2024-12-31 23:59:59',
        1,
        '2024-01-01 00:00:00',
        'Active',
        420000000,
        3,
        7
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        '2024-12-31 23:59:59',
        1,
        '2024-01-01 00:00:00',
        'Active',
        510000000,
        3,
        8
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        '2024-12-31 23:59:59',
        2,
        '2024-01-01 00:00:00',
        'Active',
        1400000000,
        3,
        4
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        '2024-12-31 23:59:59',
        1,
        '2024-01-01 00:00:00',
        'Active',
        1000000000,
        4,
        9
    );

-- ========== INSERT DISCOUNT_POLICY ==========
INSERT INTO
    discount_policy (
        create_at,
        update_at,
        end_date,
        policy_condition,
        policy_type,
        policy_value,
        start_date,
        status,
        agency_id
    )
VALUES (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        '2024-12-31 23:59:59',
        'By quantity',
        'Percentage',
        5.00,
        '2024-01-01 00:00:00',
        'Active',
        2
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        '2024-12-31 23:59:59',
        'By quantity',
        'Percentage',
        7.50,
        '2024-01-01 00:00:00',
        'Active',
        2
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        '2024-12-31 23:59:59',
        'By quantity',
        'Percentage',
        3.00,
        '2024-01-01 00:00:00',
        'Active',
        3
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        '2024-12-31 23:59:59',
        'By sales',
        'Percentage',
        6.00,
        '2024-01-01 00:00:00',
        'Active',
        3
    );

-- ========== INSERT QUANTITY_DISCOUNT_LEVEL ==========
INSERT INTO
    quantity_discount_level (
        create_at,
        update_at,
        discount_percentage,
        quantity_from,
        quantity_to,
        policy_id
    )
VALUES (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        5.00,
        2,
        5,
        1
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        7.50,
        6,
        10,
        1
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        10.00,
        11,
        20,
        1
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        3.00,
        2,
        4,
        3
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        5.00,
        5,
        10,
        3
    );

-- ========== INSERT SALE_DISCOUNT_LEVEL ==========
INSERT INTO
    sale_discount_level (
        create_at,
        update_at,
        discount_percentage,
        sales_from,
        sales_to,
        policy_id
    )
VALUES (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        6.00,
        5,
        10,
        4
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        8.00,
        11,
        20,
        4
    ),
    (
        '2024-01-01 08:00:00',
        '2024-10-29 10:00:00',
        10.00,
        21,
        50,
        4
    );

-- ========== INSERT PROMOTION_AGENCY ==========
INSERT INTO
    promotion_agency (promotion_id, agency_id)
VALUES (1, 1),
    (1, 2),
    (2, 1),
    (2, 2),
    (2, 3),
    (3, 2),
    (3, 3),
    (4, 1),
    (4, 2),
    (4, 3);

-- ========== INSERT PROMOTION_VEHICLE_DETAIL ==========
INSERT INTO
    promotion_vehicle_detail (
        promotion_id,
        vehicle_detail_id
    )
VALUES (1, 1),
    (1, 2),
    (2, 1),
    (2, 2),
    (2, 3),
    (2, 4),
    (3, 7),
    (3, 8),
    (4, 3),
    (4, 4),
    (4, 5);

-- ========== INSERT VEHICLE_TYPE_VEHICLE_CATEGORY ==========
INSERT INTO
    vehicle_type_vehicle_category (
        vehicle_category_id,
        vehicle_type_id
    )
VALUES (2, 1),
    (2, 2),
    (2, 3),
    (2, 4),
    (1, 5);