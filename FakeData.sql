-- Insert sample data for ev_sales_management_test database

-- Vehicle Category
INSERT INTO
    vehicle_category (
        create_at,
        update_at,
        description,
        vehicle_category_name
    )
VALUES (
        NOW(),
        NOW(),
        'Xe sedan hạng sang',
        'Sedan Premium'
    ),
    (
        NOW(),
        NOW(),
        'Xe SUV điện',
        'SUV Electric'
    );

-- Vehicle Type
INSERT INTO
    vehicle_type (
        create_at,
        update_at,
        description,
        manufacture_year,
        vehicle_type_name
    )
VALUES (
        NOW(),
        NOW(),
        'Xe điện 4 chỗ cao cấp',
        2025,
        'Tesla Model S'
    ),
    (
        NOW(),
        NOW(),
        'Xe điện SUV 5 chỗ',
        2025,
        'BYD Yuan Plus'
    );

-- Vehicle Type Detail
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
        NOW(),
        NOW(),
        'Pearl White',
        '4 seats',
        'Autopilot, Premium Audio',
        95000.00,
        'tesla_s_white.jpg',
        'Plaid',
        1
    ),
    (
        NOW(),
        NOW(),
        'Midnight Black',
        '5 seats',
        'All-Wheel Drive, Panoramic Roof',
        45000.00,
        'byd_yuan_black.jpg',
        'EV 480',
        2
    );

-- Vehicle Type Vehicle Category (M-M relationship)
INSERT INTO
    vehicle_type_vehicle_category (
        vehicle_category_id,
        vehicle_type_id
    )
VALUES (1, 1),
    (2, 2);

-- Agency
INSERT INTO
    agency (
        create_at,
        update_at,
        address,
        agencyname,
        email,
        phone_number,
        status,
        type
    )
VALUES (
        NOW(),
        NOW(),
        '123 Nguyen Hue, Ho Chi Minh',
        'Tesla Vietnam Dealer',
        'contact@teslavn.com',
        '0901234567',
        'ACTIVE',
        'DEALER'
    ),
    (
        NOW(),
        NOW(),
        '456 Tran Hung Dao, Hanoi',
        'BYD EV Center',
        'info@bydvn.com',
        '0987654321',
        'ACTIVE',
        'DEALER'
    );

-- Employee
INSERT INTO
    employee (
        create_at,
        update_at,
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
        NOW(),
        NOW(),
        '789 Le Loi Street',
        '1990-05-15',
        'admin@ev.com',
        'Nguyen Van A',
        'MALE',
        'hashed_password_123',
        '0912345678',
        'ROLE_ADMIN',
        'ACTIVE',
        'admin_user',
        1
    ),
    (
        NOW(),
        NOW(),
        '321 Vo Van Tan Street',
        '1995-08-20',
        'manager@ev.com',
        'Tran Thi B',
        'FEMALE',
        'hashed_password_456',
        '0923456789',
        'ROLE_DEALER_MANAGER',
        'ACTIVE',
        'manager_user',
        2
    );

-- Customer
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
        NOW(),
        NOW(),
        '999 Nguyen Trai, District 1',
        '1985-03-10',
        'Pham Minh C',
        'pham.minh@email.com',
        'MALE',
        'GOLD',
        '0934567890'
    ),
    (
        NOW(),
        NOW(),
        '555 Cach Mang Thang 8, District 3',
        '1992-07-25',
        'Le Thi D',
        'le.thi@email.com',
        'FEMALE',
        'SILVER',
        '0945678901'
    );

-- Vehicle
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
        NOW(),
        NOW(),
        'CHASSIS001',
        'ENGINE001',
        0,
        'New',
        1,
        1
    ),
    (
        NOW(),
        NOW(),
        'CHASSIS002',
        'ENGINE002',
        0,
        'New',
        2,
        2
    );

-- Quote
INSERT INTO
    quote(
        create_at,
        update_at,
        status,
        total_amount,
        employee_id
    )
VALUES (
        NOW(),
        NOW(),
        'CREATE',
        95000.00,
        1
    ),
    (
        NOW(),
        NOW(),
        'CREATE',
        45000.00,
        2
    );

-- Quotation Detail
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
        `registration fee`,
        registration_tax,
        road_maintenance_mees,
        total_amount,
        vehicle_registration_service_fee,
        wholesale_price,
        quote_id,
        vehicle_type_detail_id
    )
VALUES (
        NOW(),
        NOW(),
        500.00,
        1000.00,
        1.05,
        200.00,
        800.00,
        1,
        150.00,
        5000.00,
        300.00,
        95000.00,
        250.00,
        85000.00,
        1,
        1
    ),
    (
        NOW(),
        NOW(),
        400.00,
        500.00,
        1.11,
        180.00,
        600.00,
        1,
        120.00,
        3000.00,
        250.00,
        45000.00,
        200.00,
        40000.00,
        2,
        2
    );

-- Orders
INSERT INTO
    orders (
        create_at,
        update_at,
        contract_number,
        notes,
        status,
        total_amount,
        type,
        agency_id,
        customer_id,
        employee_id
    )
VALUES (
        NOW(),
        NOW(),
        'ORD-001-2025',
        'First time customer',
        'PENDING',
        95000.00,
        'RETAIL_CUSTOMER',
        1,
        1,
        1
    ),
    (
        NOW(),
        NOW(),
        'ORD-002-2025',
        'Bulk purchase',
        'PENDING',
        180000.00,
        'WHOLESALE_AGENCY',
        2,
        NULL,
        2
    );

-- Order Detail
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
        `registration fee`,
        registration_tax,
        road_maintenance_mees,
        total_amount,
        vehicle_registration_service_fee,
        wholesale_price,
        order_id,
        vehicle_type_detail_id
    )
VALUES (
        NOW(),
        NOW(),
        500.00,
        1000.00,
        1.05,
        200.00,
        800.00,
        1,
        150.00,
        5000.00,
        300.00,
        95000.00,
        250.00,
        85000.00,
        1,
        1
    ),
    (
        NOW(),
        NOW(),
        400.00,
        1000.00,
        2.22,
        180.00,
        600.00,
        2,
        120.00,
        6000.00,
        500.00,
        180000.00,
        400.00,
        40000.00,
        2,
        2
    );

-- Payment
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
        NOW(),
        NOW(),
        95000.00,
        DATE_ADD(NOW(), INTERVAL 30 DAY),
        1,
        NULL,
        'Full Payment',
        'CASH',
        0.00,
        'UNPAID',
        NULL,
        1
    ),
    (
        NOW(),
        NOW(),
        90000.00,
        DATE_ADD(NOW(), INTERVAL 30 DAY),
        1,
        NOW(),
        'Installment',
        'VNPAY',
        0.00,
        'PAID',
        'VNPAY123456',
        2
    );

-- Discount Policy
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
        NOW(),
        NOW(),
        DATE_ADD(NOW(), INTERVAL 90 DAY),
        'Minimum 5 units',
        'QUANTITY',
        5.00,
        NOW(),
        'ACTIVE',
        1
    ),
    (
        NOW(),
        NOW(),
        DATE_ADD(NOW(), INTERVAL 90 DAY),
        'Sales over 500k',
        'SALES',
        500000.00,
        NOW(),
        'ACTIVE',
        2
    );

-- Quantity Discount Level
INSERT INTO
    quantity_discount_level (
        create_at,
        update_at,
        discount_percentage,
        quantity_from,
        quantity_to,
        policy_id
    )
VALUES (NOW(), NOW(), 5.00, 5, 10, 1),
    (
        NOW(),
        NOW(),
        10.00,
        11,
        20,
        1
    );

-- Sale Discount Level
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
        NOW(),
        NOW(),
        3.00,
        500000.00,
        1000000.00,
        2
    ),
    (
        NOW(),
        NOW(),
        5.00,
        1000000.00,
        5000000.00,
        2
    );

-- Agency Wholesale Price
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
        NOW(),
        NOW(),
        DATE_ADD(NOW(), INTERVAL 180 DAY),
        5,
        NOW(),
        'ACTIVE',
        85000.00,
        1,
        1
    ),
    (
        NOW(),
        NOW(),
        DATE_ADD(NOW(), INTERVAL 180 DAY),
        10,
        NOW(),
        'ACTIVE',
        40000.00,
        2,
        2
    );

-- Promotion
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
        NOW(),
        NOW(),
        'New year sale',
        5000.00,
        NULL,
        DATE_ADD(NOW(), INTERVAL 30 DAY),
        'New Year Special 2025',
        'AMOUNT',
        5000.00,
        NOW(),
        'ACTIVE'
    ),
    (
        NOW(),
        NOW(),
        'Year-end clearance',
        NULL,
        10.00,
        DATE_ADD(NOW(), INTERVAL 60 DAY),
        'Discount 10%',
        'PERCENTAGE',
        10.00,
        NOW(),
        'ACTIVE'
    );

-- Promotion Agency
INSERT INTO
    promotion_agency (promotion_id, agency_id)
VALUES (1, 1),
    (2, 2);

-- Promotion Vehicle Detail
INSERT INTO
    promotion_vehicle_detail (
        promotion_id,
        vehicle_detail_id
    )
VALUES (1, 1),
    (2, 2);

-- Test Drive Appointment
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
        NOW(),
        NOW(),
        DATE_ADD(CURDATE(), INTERVAL 7 DAY),
        'SCHEDULED',
        '10:00:00',
        1,
        1
    ),
    (
        NOW(),
        NOW(),
        DATE_ADD(CURDATE(), INTERVAL 3 DAY),
        'SCHEDULED',
        '14:30:00',
        2,
        2
    );

-- Warehouse Receipt
INSERT INTO
    warehouse_receipt (
        create_at,
        update_at,
        note,
        reason,
        status,
        total_amount,
        warehouse_receipt_date,
        agency_id,
        employee_id
    )
VALUES (
        NOW(),
        NOW(),
        'Received new inventory',
        'Import from manufacturer',
        'Completed',
        500000.00,
        NOW(),
        1,
        1
    ),
    (
        NOW(),
        NOW(),
        'Stock replenishment',
        'New shipment arrival',
        'Completed',
        320000.00,
        NOW(),
        2,
        2
    );

-- Warehouse Receipt Detail
INSERT INTO
    warehouse_receipt_detail (
        warehouse_receipt_id,
        vehicle_id
    )
VALUES (1, 1),
    (2, 2);

-- Warehouse Release Note
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
        NOW(),
        NOW(),
        'Released for delivery',
        NOW(),
        95000.00,
        1,
        1
    ),
    (
        NOW(),
        NOW(),
        'Batch release',
        NOW(),
        180000.00,
        2,
        2
    );

-- Warehouse Release Note Detail
INSERT INTO
    warehouse_release_note_detail (
        warehouse_release_note_id,
        vehicle_id
    )
VALUES (1, 1),
    (2, 2);

-- Vehicle Delivery
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
        NOW(),
        NOW(),
        DATE_ADD(NOW(), INTERVAL 7 DAY),
        NULL,
        'PREPARING',
        1,
        1
    ),
    (
        NOW(),
        NOW(),
        DATE_ADD(NOW(), INTERVAL 5 DAY),
        NULL,
        'PREPARING',
        2,
        2
    );

-- Import Request
INSERT INTO
    import_request (
        create_at,
        update_at,
        note,
        status,
        employee_id
    )
VALUES (
        NOW(),
        NOW(),
        'Request for 10 units Model S',
        'REQUESTED',
        1
    ),
    (
        NOW(),
        NOW(),
        'Request for 20 units Yuan Plus',
        'REQUESTED',
        2
    );

-- Import Request Detail
INSERT INTO
    import_request_detail (
        create_at,
        update_at,
        quantity,
        import_request_id,
        vehicle_type_detail_id
    )
VALUES (NOW(), NOW(), 10, 1, 1),
    (NOW(), NOW(), 20, 2, 2);

-- Monthly Sales
INSERT INTO
    monthly_sales (
        create_at,
        update_at,
        sales_amount,
        sales_month,
        agency_id
    )
VALUES (
        NOW(),
        NOW(),
        285000.00,
        '2025-11-01',
        1
    ),
    (
        NOW(),
        NOW(),
        540000.00,
        '2025-11-01',
        2
    );

-- Feedback
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
        NOW(),
        NOW(),
        'Great service and quality vehicle',
        'PROCESSED',
        'Excellent Experience',
        1
    ),
    (
        NOW(),
        NOW(),
        'Delivery was delayed but good support',
        'IN_PROCESSED',
        'Good Overall',
        2
    );

-- Feedback Handling
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
        NOW(),
        NOW(),
        'Thank you for your feedback, will continue improving',
        'EMAIL',
        'COMPLETE',
        1,
        1
    ),
    (
        NOW(),
        NOW(),
        'We will improve delivery time',
        'PHONE_NUMBER',
        'COMPLETE',
        2,
        2
    );