-- Insert dữ liệu giả cho cơ sở dữ liệu EV Sales Management

-- 1. Vehicle Category
INSERT INTO vehicle_category (create_at, update_at, description, vehicle_category_name) VALUES
(NOW(), NOW(), 'Xe điện cỡ nhỏ, phù hợp đi trong thành phố', 'Sedan'),
(NOW(), NOW(), 'Xe SUV điện cao cấp, phù hợp gia đình', 'SUV'),
(NOW(), NOW(), 'Xe thể thao điện hiệu năng cao', 'Sports'),
(NOW(), NOW(), 'Xe bán tải điện', 'Pickup Truck');

-- 2. Vehicle Type
INSERT INTO vehicle_type (create_at, update_at, description, manufacture_year, vehicle_type_name) VALUES
(NOW(), NOW(), 'Xe điện Tesla Model 3 thế hệ mới', 2024, 'Tesla Model 3'),
(NOW(), NOW(), 'SUV điện VinFast VF8', 2024, 'VinFast VF8'),
(NOW(), NOW(), 'SUV điện VinFast VF9', 2024, 'VinFast VF9'),
(NOW(), NOW(), 'Xe điện BYD Seal', 2024, 'BYD Seal'),
(NOW(), NOW(), 'SUV điện Hyundai IONIQ 5', 2024, 'Hyundai IONIQ 5');

-- 3. Vehicle Type - Vehicle Category (Many-to-Many)
INSERT INTO vehicle_type_vehicle_category (vehicle_category_id, vehicle_type_id) VALUES
(1, 1), -- Tesla Model 3 - Sedan
(2, 2), -- VinFast VF8 - SUV
(2, 3), -- VinFast VF9 - SUV
(1, 4), -- BYD Seal - Sedan
(2, 5); -- Hyundai IONIQ 5 - SUV

-- 4. Vehicle Type Detail
INSERT INTO vehicle_type_detail (create_at, update_at, color, configuration, features, price, vehicle_image, version, vehicle_type_id) VALUES
(NOW(), NOW(), 'Trắng', 'Pin 60kWh, tầm hoạt động 420km', 'Autopilot, Camera 360, Sạc nhanh', 1200000000.00, 'tesla_model3_white.jpg', 'Standard Range Plus', 1),
(NOW(), NOW(), 'Đen', 'Pin 82kWh, tầm hoạt động 580km', 'Autopilot nâng cao, FSD, Premium sound', 1500000000.00, 'tesla_model3_black.jpg', 'Long Range AWD', 1),
(NOW(), NOW(), 'Xanh', 'Pin 87.7kWh, tầm hoạt động 447km', 'ADAS Level 2, Màn hình 15.6 inch', 1291000000.00, 'vf8_eco.jpg', 'VF8 Eco', 2),
(NOW(), NOW(), 'Đỏ', 'Pin 87.7kWh, tầm hoạt động 447km', 'ADAS Level 2+, Nội thất da cao cấp', 1491000000.00, 'vf8_plus.jpg', 'VF8 Plus', 2),
(NOW(), NOW(), 'Bạc', 'Pin 123kWh, tầm hoạt động 594km', 'ADAS Level 2+, 7 chỗ ngồi', 1691000000.00, 'vf9_eco.jpg', 'VF9 Eco', 3),
(NOW(), NOW(), 'Xanh Navy', 'Pin 123kWh, tầm hoạt động 594km', 'ADAS Level 2+, Nội thất cao cấp, 7 chỗ', 1891000000.00, 'vf9_plus.jpg', 'VF9 Plus', 3),
(NOW(), NOW(), 'Xám', 'Pin 82.5kWh, tầm hoạt động 570km', 'Hệ thống lái tự động Level 2', 1350000000.00, 'byd_seal.jpg', 'Premium', 4),
(NOW(), NOW(), 'Trắng Ngọc Trai', 'Pin 72.6kWh, tầm hoạt động 481km', 'V2L, Sạc nhanh 800V', 1399000000.00, 'ioniq5.jpg', 'Exclusive', 5);

-- 5. Agency
INSERT INTO agency (create_at, update_at, address, agencyname, email, phone_number, status) VALUES
(NOW(), NOW(), '123 Đường Lê Lợi, Q.1, TP.HCM', 'Đại Lý EV Sài Gòn', 'saigon@evdealer.vn', '0281234567', 'ACTIVE'),
(NOW(), NOW(), '456 Đường Trần Hưng Đạo, Hoàn Kiếm, Hà Nội', 'Đại Lý EV Hà Nội', 'hanoi@evdealer.vn', '0241234567', 'ACTIVE'),
(NOW(), NOW(), '789 Đường Nguyễn Văn Linh, Q.Thanh Khê, Đà Nẵng', 'Đại Lý EV Đà Nẵng', 'danang@evdealer.vn', '0361234567', 'ACTIVE'),
(NOW(), NOW(), '321 Đường 30/4, TP.Biên Hòa, Đồng Nai', 'Đại Lý EV Đồng Nai', 'dongnai@evdealer.vn', '0251234567', 'ACTIVE'),
(NOW(), NOW(), '654 Đường Võ Văn Kiệt, TP.Cần Thơ', 'Đại Lý EV Cần Thơ', 'cantho@evdealer.vn', '0291234567', 'INACTIVE');

-- 6. Employee
INSERT INTO employee (create_at, update_at, address, birth_date, email, employee_name, gender, phone_number, position, agency_id) VALUES
(NOW(), NOW(), 'Q.1, TP.HCM', '1990-05-15', 'admin@evm.vn', 'Nguyễn Văn Admin', 'MALE', '0901234567', 'ADMIN', NULL),
(NOW(), NOW(), 'Q.Tân Bình, TP.HCM', '1988-03-20', 'manager.sg@evdealer.vn', 'Trần Thị Mai', 'FEMALE', '0902234567', 'DEALER_MANAGER', 1),
(NOW(), NOW(), 'Q.3, TP.HCM', '1995-07-10', 'staff1.sg@evdealer.vn', 'Lê Văn Bình', 'MALE', '0903234567', 'DEALER_STAFF', 1),
(NOW(), NOW(), 'Q.10, TP.HCM', '1992-11-25', 'staff2.sg@evdealer.vn', 'Phạm Thị Lan', 'FEMALE', '0904234567', 'DEALER_STAFF', 1),
(NOW(), NOW(), 'Hoàn Kiếm, Hà Nội', '1987-02-14', 'manager.hn@evdealer.vn', 'Hoàng Văn Hùng', 'MALE', '0905234567', 'DEALER_MANAGER', 2),
(NOW(), NOW(), 'Đống Đa, Hà Nội', '1994-09-08', 'staff1.hn@evdealer.vn', 'Vũ Thị Hương', 'FEMALE', '0906234567', 'DEALER_STAFF', 2),
(NOW(), NOW(), 'Q.Hải Châu, Đà Nẵng', '1991-06-30', 'manager.dn@evdealer.vn', 'Đặng Văn Minh', 'MALE', '0907234567', 'DEALER_MANAGER', 3),
(NOW(), NOW(), 'Q.1, TP.HCM', '1993-04-12', 'staff.evm1@evm.vn', 'Ngô Thị Thu', 'FEMALE', '0908234567', 'EVM_STAFF', NULL),
(NOW(), NOW(), 'Hoàn Kiếm, Hà Nội', '1989-12-05', 'staff.evm2@evm.vn', 'Bùi Văn Đức', 'MALE', '0909234567', 'EVM_STAFF', NULL);

-- 7. Account
INSERT INTO account (create_at, update_at, last_login_time, password, role, status, username, employee_id) VALUES
(NOW(), NOW(), NOW(), '$2a$10$abcdefghijklmnopqrstuv', 'ROLE_ADMIN', 'ACTIVE', 'admin', 1),
(NOW(), NOW(), NOW(), '$2a$10$abcdefghijklmnopqrstuv', 'ROLE_DEALER_MANAGER', 'ACTIVE', 'manager_sg', 2),
(NOW(), NOW(), NOW(), '$2a$10$abcdefghijklmnopqrstuv', 'ROLE_DEALER_STAFF', 'ACTIVE', 'staff1_sg', 3),
(NOW(), NOW(), NOW(), '$2a$10$abcdefghijklmnopqrstuv', 'ROLE_DEALER_STAFF', 'ACTIVE', 'staff2_sg', 4),
(NOW(), NOW(), NOW(), '$2a$10$abcdefghijklmnopqrstuv', 'ROLE_DEALER_MANAGER', 'ACTIVE', 'manager_hn', 5),
(NOW(), NOW(), NOW(), '$2a$10$abcdefghijklmnopqrstuv', 'ROLE_DEALER_STAFF', 'ACTIVE', 'staff1_hn', 6),
(NOW(), NOW(), NOW(), '$2a$10$abcdefghijklmnopqrstuv', 'ROLE_DEALER_MANAGER', 'ACTIVE', 'manager_dn', 7),
(NOW(), NOW(), NOW(), '$2a$10$abcdefghijklmnopqrstuv', 'ROLE_EVM_STAFF', 'ACTIVE', 'evm_staff1', 8),
(NOW(), NOW(), NOW(), '$2a$10$abcdefghijklmnopqrstuv', 'ROLE_EVM_STAFF', 'ACTIVE', 'evm_staff2', 9);

-- 8. Agency Wholesale Price
INSERT INTO agency_wholesale_price (create_at, update_at, end_date, minimum_quantity, start_date, status, wholesale_price, agency_id, vehicle_type_detail_id) VALUES
(NOW(), NOW(), '2025-12-31', 5, '2024-01-01', 'ACTIVE', 1150000000.00, 1, 1),
(NOW(), NOW(), '2025-12-31', 5, '2024-01-01', 'ACTIVE', 1450000000.00, 1, 2),
(NOW(), NOW(), '2025-12-31', 3, '2024-01-01', 'ACTIVE', 1250000000.00, 2, 3),
(NOW(), NOW(), '2025-12-31', 3, '2024-01-01', 'ACTIVE', 1450000000.00, 2, 4),
(NOW(), NOW(), '2025-12-31', 2, '2024-01-01', 'ACTIVE', 1650000000.00, 3, 5);

-- 9. Customer
INSERT INTO customer (create_at, update_at, address, birth_date, customer_name, email, gender, membership_level, phone_number) VALUES
(NOW(), NOW(), 'Q.Bình Thạnh, TP.HCM', '1985-03-15', 'Nguyễn Hoàng Anh', 'nhanh@gmail.com', 'MALE', 'GOLD', '0911111111'),
(NOW(), NOW(), 'Q.7, TP.HCM', '1990-07-22', 'Trần Thị Bích', 'ttbich@gmail.com', 'FEMALE', 'SILVER', '0922222222'),
(NOW(), NOW(), 'Q.2, TP.HCM', '1988-11-30', 'Lê Văn Cường', 'lvcuong@gmail.com', 'MALE', 'DIAMOND', '0933333333'),
(NOW(), NOW(), 'Ba Đình, Hà Nội', '1992-05-18', 'Phạm Thị Dung', 'ptdung@gmail.com', 'FEMALE', 'COPPER', '0944444444'),
(NOW(), NOW(), 'Cầu Giấy, Hà Nội', '1987-09-25', 'Hoàng Văn Em', 'hvem@gmail.com', 'MALE', 'SILVER', '0955555555'),
(NOW(), NOW(), 'Thanh Xuân, Hà Nội', '1995-02-14', 'Vũ Thị Phương', 'vtphuong@gmail.com', 'FEMALE', 'GOLD', '0966666666'),
(NOW(), NOW(), 'Q.Hải Châu, Đà Nẵng', '1989-12-08', 'Đặng Văn Giang', 'dvgiang@gmail.com', 'MALE', 'SILVER', '0977777777'),
(NOW(), NOW(), 'Q.Thanh Khê, Đà Nẵng', '1993-06-20', 'Ngô Thị Hà', 'ntha@gmail.com', 'FEMALE', 'COPPER', '0988888888');

-- 10. Vehicle
INSERT INTO vehicle (create_at, update_at, chassis_number, machine_number, status, vehicle_condition, agency_id, vehicle_type_detail_id) VALUES
(NOW(), NOW(), 'TESLA3001', 'TM3001', 1, 'NEW', 1, 1),
(NOW(), NOW(), 'TESLA3002', 'TM3002', 1, 'NEW', 1, 1),
(NOW(), NOW(), 'TESLA3003', 'TM3003', 1, 'NEW', 1, 2),
(NOW(), NOW(), 'VF8001', 'VF8M001', 1, 'NEW', 1, 3),
(NOW(), NOW(), 'VF8002', 'VF8M002', 1, 'NEW', 2, 3),
(NOW(), NOW(), 'VF8003', 'VF8M003', 1, 'NEW', 2, 4),
(NOW(), NOW(), 'VF9001', 'VF9M001', 1, 'NEW', 2, 5),
(NOW(), NOW(), 'VF9002', 'VF9M002', 1, 'NEW', 3, 5),
(NOW(), NOW(), 'BYD001', 'BYDM001', 1, 'NEW', 3, 7),
(NOW(), NOW(), 'ION001', 'IONM001', 1, 'NEW', 3, 8);

-- 11. Promotion
INSERT INTO promotion (create_at, update_at, criteria, discount_amount, discount_percent, end_date, promotion_name, promotion_type, promotion_value, start_date, status) VALUES
(NOW(), NOW(), 'Khuyến mãi tết 2024', 50000000.00, NULL, '2024-02-29', 'Khuyến Mãi Tết 2024', 'AMOUNT', 50000000.00, '2024-01-15', 'ACTIVE'),
(NOW(), NOW(), 'Giảm giá hè', NULL, 10.00, '2024-08-31', 'Khuyến Mãi Hè 2024', 'PERCENTAGE', 10.00, '2024-06-01', 'ACTIVE'),
(NOW(), NOW(), 'Black Friday', NULL, 15.00, '2024-11-30', 'Black Friday Sale', 'PERCENTAGE', 15.00, '2024-11-01', 'NOT_ACTIVE'),
(NOW(), NOW(), 'Khách hàng thân thiết', 30000000.00, NULL, '2025-12-31', 'Ưu Đãi VIP', 'AMOUNT', 30000000.00, '2024-01-01', 'ACTIVE');

-- 12. Promotion Agency (Many-to-Many)
INSERT INTO promotion_agency (promotion_id, agency_id) VALUES
(1, 1), (1, 2), (1, 3),
(2, 1), (2, 2),
(4, 1), (4, 2), (4, 3), (4, 4);

-- 13. Promotion Vehicle Detail (Many-to-Many)
INSERT INTO promotion_vehicle_detail (promotion_id, vehicle_detail_id) VALUES
(1, 1), (1, 2), (1, 3),
(2, 3), (2, 4), (2, 5),
(4, 1), (4, 2), (4, 3), (4, 4), (4, 5), (4, 6), (4, 7), (4, 8);

-- 14. Discount Policy
INSERT INTO discount_policy (create_at, update_at, end_date, policy_condition, policy_type, policy_value, start_date, status, agency_id) VALUES
(NOW(), NOW(), '2025-12-31', 'Chiết khấu theo số lượng mua', 'QUANTITY', 10.00, '2024-01-01', 'ACTIVE', 1),
(NOW(), NOW(), '2025-12-31', 'Chiết khấu theo doanh số', 'SALES', 5.00, '2024-01-01', 'ACTIVE', 2);

-- 15. Quantity Discount Level
INSERT INTO quantity_discount_level (create_at, update_at, discount_percentage, quantity_from, quantity_to, policy_id) VALUES
(NOW(), NOW(), 5.00, 5, 9, 1),
(NOW(), NOW(), 8.00, 10, 19, 1),
(NOW(), NOW(), 12.00, 20, 999, 1);

-- 16. Sale Discount Level
INSERT INTO sale_discount_level (create_at, update_at, discount_percentage, sales_from, sales_to, policy_id) VALUES
(NOW(), NOW(), 3.00, 1000000000.00, 4999999999.99, 2),
(NOW(), NOW(), 5.00, 5000000000.00, 9999999999.99, 2),
(NOW(), NOW(), 8.00, 10000000000.00, 999999999999.99, 2);

-- 17. Orders
INSERT INTO orders (create_at, update_at, contract_number, notes, status, total_amount, type, agency_id, customer_id, employee_id) VALUES
(NOW(), NOW(), 'HD001-2024', 'Khách hàng VIP', 'DELIVERED', 1200000000.00, 'RETAIL_CUSTOMER', NULL, 1, 3),
(NOW(), NOW(), 'HD002-2024', 'Thanh toán trả góp', 'INSTALLMENT', 1500000000.00, 'RETAIL_CUSTOMER', NULL, 2, 3),
(NOW(), NOW(), 'HD003-2024', 'Đơn hàng sỉ', 'PAID', 3873000000.00, 'WHOLESALE_AGENCY', 4, NULL, 6),
(NOW(), NOW(), 'HD004-2024', NULL, 'PENDING', 1291000000.00, 'RETAIL_CUSTOMER', NULL, 4, 6),
(NOW(), NOW(), 'HD005-2024', 'Giao hàng tận nơi', 'PENDING_DELIVERY', 1891000000.00, 'RETAIL_CUSTOMER', NULL, 5, 6);

-- 18. Order Detail
INSERT INTO order_detail (create_at, update_at, compulsory_insurance, discount, discount_percentage, license_plate_fee, material_insurance, quantity, `registration fee`, registration_tax, road_maintenance_mees, total_amount, vehicle_registration_service_fee, wholesale_price, order_id, vehicle_type_detail_id) VALUES
(NOW(), NOW(), 5000000.00, 0.00, 0.00, 2000000.00, 8000000.00, 1, 10000000.00, 120000000.00, 3000000.00, 1200000000.00, 1500000.00, 1200000000.00, 1, 1),
(NOW(), NOW(), 5000000.00, 50000000.00, 5.00, 2000000.00, 10000000.00, 1, 10000000.00, 150000000.00, 3000000.00, 1500000000.00, 1500000.00, 1500000000.00, 2, 2),
(NOW(), NOW(), 15000000.00, 150000000.00, 5.00, 6000000.00, 24000000.00, 3, 30000000.00, 387300000.00, 9000000.00, 3873000000.00, 4500000.00, 1291000000.00, 3, 3),
(NOW(), NOW(), 5000000.00, 0.00, 0.00, 2000000.00, 8000000.00, 1, 10000000.00, 129100000.00, 3000000.00, 1291000000.00, 1500000.00, 1291000000.00, 4, 3),
(NOW(), NOW(), 5000000.00, 30000000.00, 3.00, 2000000.00, 10000000.00, 1, 10000000.00, 189100000.00, 3000000.00, 1891000000.00, 1500000.00, 1891000000.00, 5, 6);

-- 19. Payment
INSERT INTO payment (create_at, update_at, amount, due_date, number_cycle, payment_date, payment_form, payment_method, penalty_amount, status, vnpaycode, order_id) VALUES
(NOW(), NOW(), 1200000000.00, NOW(), 1, NOW(), 'Thanh toán một lần', 'CASH', 0.00, 'PAID', NULL, 1),
(NOW(), NOW(), 300000000.00, NOW(), 1, NOW(), 'Trả góp 5 kỳ', 'VNPAY', 0.00, 'PAID', 'VNP20240115001', 2),
(NOW(), NOW(), 300000000.00, DATE_ADD(NOW(), INTERVAL 3 MONTH), 2, NULL, 'Trả góp 5 kỳ', 'VNPAY', 0.00, 'UNPAID', NULL, 2),
(NOW(), NOW(), 300000000.00, DATE_ADD(NOW(), INTERVAL 6 MONTH), 3, NULL, 'Trả góp 5 kỳ', 'VNPAY', 0.00, 'UNPAID', NULL, 2),
(NOW(), NOW(), 3873000000.00, NOW(), 1, NOW(), 'Thanh toán chuyển khoản', 'VNPAY', 0.00, 'PAID', 'VNP20240116001', 3);

-- 20. Feedback
INSERT INTO feedback (create_at, update_at, feedback_content, status, feedback_title, customer_id) VALUES
(NOW(), NOW(), 'Xe chạy êm, pin trâu, rất hài lòng!', 'PROCESSED', 'Đánh giá Tesla Model 3', 1),
(NOW(), NOW(), 'Dịch vụ tư vấn nhiệt tình, giao xe đúng hẹn', 'PROCESSED', 'Hài lòng về dịch vụ', 2),
(NOW(), NOW(), 'Muốn hỏi về quy trình bảo dưỡng định kỳ', 'IN_PROCESSED', 'Hỏi về bảo dưỡng', 3),
(NOW(), NOW(), 'Xe VF8 có khuyến mãi gì trong tháng này không?', 'NOT_YET_PROCESSED', 'Hỏi về khuyến mãi', 4);

-- 21. Feedback Handling
INSERT INTO feedback_handling (create_at, update_at, feedback_handling_content, feedback_handling_method, status, employee_id, feedback_id) VALUES
(NOW(), NOW(), 'Cảm ơn quý khách đã tin tưởng sử dụng sản phẩm', 'EMAIL', 'COMPLETE', 3, 1),
(NOW(), NOW(), 'Đã liên hệ và giải đáp thắc mắc khách hàng', 'PHONE_NUMBER', 'COMPLETE', 3, 2);

-- 22. Test Drive Appointment
INSERT INTO test_drive_appointment (create_at, update_at, date_of_appointment, status, time_of_appointment, customer_id, vehicle_id) VALUES
(NOW(), NOW(), '2024-12-15', 'ARRIVED', '10:00:00', 3, 1),
(NOW(), NOW(), '2024-12-20', 'SCHEDULED', '14:00:00', 4, 5),
(NOW(), NOW(), '2024-12-18', 'CANCELLED', '09:00:00', 5, 7);

-- 23. Quote
INSERT INTO quote (create_at, update_at, status, total_amount, employee_id) VALUES
(NOW(), NOW(), 'ORDERED', 1200000000.00, 3),
(NOW(), NOW(), 'PROCESSING', 1500000000.00, 6),
(NOW(), NOW(), 'REJECTED', 1350000000.00, 7);

-- 24. Quotation Detail
INSERT INTO quotation_detail (create_at, update_at, compulsory_insurance, discount, discount_percentage, license_plate_fee, material_insurance, quantity, `registration fee`, registration_tax, road_maintenance_mees, total_amount, vehicle_registration_service_fee, wholesale_price, quote_id, vehicle_type_detail_id) VALUES
(NOW(), NOW(), 5000000.00, 0.00, 0.00, 2000000.00, 8000000.00, 1, 10000000.00, 120000000.00, 3000000.00, 1200000000.00, 1500000.00, 1200000000.00, 1, 1),
(NOW(), NOW(), 5000000.00, 50000000.00, 5.00, 2000000.00, 10000000.00, 1, 10000000.00, 150000000.00, 3000000.00, 1500000000.00, 1500000.00, 1500000000.00, 2, 2),
(NOW(), NOW(), 5000000.00, 0.00, 0.00, 2000000.00, 9000000.00, 1, 10000000.00, 135000000.00, 3000000.00, 1350000000.00, 1500000.00, 1350000000.00, 3, 7);

-- 25. Import Request
INSERT INTO import_request (create_at, update_at, note, status, employee_id) VALUES
(NOW(), NOW(), 'Nhập kho quý 1/2024', 'APPROVED', 2),
(NOW(), NOW(), 'Nhập kho bổ sung', 'APPROVED', 5),
(NOW(), NOW(), 'Yêu cầu nhập khẩn', 'REQUESTED', 7);

-- 26. Import Request Detail
INSERT INTO import_request_detail (create_at, update_at, quantity, import_request_id, vehicle_type_detail_id) VALUES
(NOW(), NOW(), 5, 1, 1),
(NOW(), NOW(), 3, 1, 3),
(NOW(), NOW(), 4, 2, 5),
(NOW(), NOW(), 2, 3, 7);

-- 27. Warehouse Receipt
INSERT INTO warehouse_receipt (create_at, update_at, note, reason, status, total_amount, warehouse_receipt_date, agency_id, employee_id) VALUES
(NOW(), NOW(), 'Nhập kho đợt 1', 'Nhập hàng mới', 'COMPLETED', 6000000000.00, NOW(), 1, 2),
(NOW(), NOW(), 'Nhập kho đợt 2', 'Bổ sung hàng', 'COMPLETED', 5164000000.00, NOW(), 2, 5);

-- 28. Warehouse Receipt Detail
INSERT INTO warehouse_receipt_detail (warehouse_receipt_id, vehicle_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4),
(2, 5), (2, 6), (2, 7);

-- 29. Warehouse Release Note
INSERT INTO warehouse_release_note (create_at, update_at, note, release_date, total_amount, employee_id, order_id) VALUES
(NOW(), NOW(), 'Xuất kho giao xe cho khách', NOW(), 1200000000.00, 3, 1),
(NOW(), NOW(), 'Xuất kho cho đại lý', NOW(), 3873000000.00, 6, 3);

-- 30. Warehouse Release Note Detail
INSERT INTO warehouse_release_note_detail (warehouse_release_note_id, vehicle_id) VALUES
(1, 1),
(2, 4), (2, 5), (2, 6);

-- 31. Vehicle Delivery
INSERT INTO vehicle_delivery (create_at, update_at, expected_delivery_date, delivery_date, status, employee_id, order_id) VALUES
(NOW(), NOW(), '2024-11-20', '2024-11-20', 'DELIVERED', 3, 1),
(NOW(), NOW(), '2024-12-25', NULL, 'PREPARING', 3, 2),
(NOW(), NOW(), '2024-11-25', '2024-11-25', 'DELIVERED', 6, 3),
(NOW(), NOW(), '2024-12-30', NULL, 'PREPARING', 6, 4),
(NOW(), NOW(), '2024-12-28', NULL, 'DELIVERING', 6, 5);

-- 32. Monthly Sales
INSERT INTO monthly_sales (create_at, update_at, sales_amount, sales_month, agency_id) VALUES
(NOW(), NOW(), 2500000000.00, '2024-01-01', 1),
(NOW(), NOW(), 3200000000.00, '2024-02-01', 1),
(NOW(), NOW(), 2800000000.00, '2024-03-01', 1),
(NOW(), NOW(), 3500000000.00, '2024-04-01', 1),
(NOW(), NOW(), 4100000000.00, '2024-05-01', 1),
(NOW(), NOW(), 3800000000.00, '2024-06-01', 1),
(NOW(), NOW(), 4500000000.00, '2024-07-01', 1),
(NOW(), NOW(), 4200000000.00, '2024-08-01', 1),
(NOW(), NOW(), 3900000000.00, '2024-09-01', 1),
(NOW(), NOW(), 5200000000.00, '2024-10-01', 1),
(NOW(), NOW(), 1800000000.00, '2024-01-01', 2),
(NOW(), NOW(), 2200000000.00, '2024-02-01', 2),
(NOW(), NOW(), 2500000000.00, '2024-03-01', 2),
(NOW(), NOW(), 2800000000.00, '2024-04-01', 2),
(NOW(), NOW(), 3200000000.00, '2024-05-01', 2),
(NOW(), NOW(), 3500000000.00, '2024-06-01', 2),
(NOW(), NOW(), 3800000000.00, '2024-07-01', 2),
(NOW(), NOW(), 4100000000.00, '2024-08-01', 2),
(NOW(), NOW(), 3700000000.00, '2024-09-01', 2),
(NOW(), NOW(), 4500000000.00, '2024-10-01', 2),
(NOW(), NOW(), 1500000000.00, '2024-01-01', 3),
(NOW(), NOW(), 1800000000.00, '2024-02-01', 3),
(NOW(), NOW(), 2100000000.00, '2024-03-01', 3),
(NOW(), NOW(), 2400000000.00, '2024-04-01', 3),
(NOW(), NOW(), 2700000000.00, '2024-05-01', 3),
(NOW(), NOW(), 3000000000.00, '2024-06-01', 3),
(NOW(), NOW(), 3300000000.00, '2024-07-01', 3),
(NOW(), NOW(), 3600000000.00, '2024-08-01', 3),
(NOW(), NOW(), 3200000000.00, '2024-09-01', 3),
(NOW(), NOW(), 3900000000.00, '2024-10-01', 3);

-- Thêm dữ liệu bổ sung cho các bảng khác

-- 33. Thêm xe vào kho (các xe chưa được gán)
INSERT INTO vehicle (create_at, update_at, chassis_number, machine_number, status, vehicle_condition, agency_id, vehicle_type_detail_id) VALUES
(NOW(), NOW(), 'TESLA3004', 'TM3004', 1, 'NEW', 1, 1),
(NOW(), NOW(), 'TESLA3005', 'TM3005', 1, 'NEW', 1, 2),
(NOW(), NOW(), 'VF8004', 'VF8M004', 1, 'NEW', 2, 3),
(NOW(), NOW(), 'VF8005', 'VF8M005', 1, 'NEW', 2, 4),
(NOW(), NOW(), 'VF9003', 'VF9M003', 1, 'NEW', 3, 5),
(NOW(), NOW(), 'VF9004', 'VF9M004', 1, 'NEW', 3, 6),
(NOW(), NOW(), 'BYD002', 'BYDM002', 1, 'NEW', 1, 7),
(NOW(), NOW(), 'BYD003', 'BYDM003', 1, 'NEW', 2, 7),
(NOW(), NOW(), 'ION002', 'IONM002', 1, 'NEW', 3, 8),
(NOW(), NOW(), 'ION003', 'IONM003', 1, 'NEW', 3, 8);

-- 34. Thêm khách hàng
INSERT INTO customer (create_at, update_at, address, birth_date, customer_name, email, gender, membership_level, phone_number) VALUES
(NOW(), NOW(), 'Q.Tân Phú, TP.HCM', '1991-04-12', 'Đinh Văn Hải', 'dvhai@gmail.com', 'MALE', 'SILVER', '0910101010'),
(NOW(), NOW(), 'Q.Phú Nhuận, TP.HCM', '1986-08-28', 'Mai Thị Kim', 'mtkim@gmail.com', 'FEMALE', 'GOLD', '0920202020'),
(NOW(), NOW(), 'Long Biên, Hà Nội', '1994-01-05', 'Trương Văn Long', 'tvlong@gmail.com', 'MALE', 'COPPER', '0930303030'),
(NOW(), NOW(), 'Hai Bà Trưng, Hà Nội', '1990-10-17', 'Lý Thị Mai', 'ltmai@gmail.com', 'FEMALE', 'DIAMOND', '0940404040'),
(NOW(), NOW(), 'Q.Sơn Trà, Đà Nẵng', '1988-03-22', 'Phan Văn Nam', 'pvnam@gmail.com', 'MALE', 'GOLD', '0950505050');

-- 35. Thêm đơn hàng mới
INSERT INTO orders (create_at, update_at, contract_number, notes, status, total_amount, type, agency_id, customer_id, employee_id) VALUES
(NOW(), NOW(), 'HD006-2024', 'Khách hàng doanh nghiệp', 'PAID', 2700000000.00, 'RETAIL_CUSTOMER', NULL, 6, 3),
(NOW(), NOW(), 'HD007-2024', 'Mua 2 xe cùng lúc', 'PENDING_DELIVERY', 2982000000.00, 'RETAIL_CUSTOMER', NULL, 7, 6),
(NOW(), NOW(), 'HD008-2024', 'Đơn sỉ cho đại lý Đồng Nai', 'DELIVERED', 4950000000.00, 'WHOLESALE_AGENCY', 4, NULL, 8),
(NOW(), NOW(), 'HD009-2024', NULL, 'PENDING', 1350000000.00, 'RETAIL_CUSTOMER', NULL, 9, 7),
(NOW(), NOW(), 'HD010-2024', 'Khách hàng VIP Diamond', 'INSTALLMENT', 1399000000.00, 'RETAIL_CUSTOMER', NULL, 11, 3);

-- 36. Thêm chi tiết đơn hàng mới
INSERT INTO order_detail (create_at, update_at, compulsory_insurance, discount, discount_percentage, license_plate_fee, material_insurance, quantity, `registration fee`, registration_tax, road_maintenance_mees, total_amount, vehicle_registration_service_fee, wholesale_price, order_id, vehicle_type_detail_id) VALUES
(NOW(), NOW(), 10000000.00, 100000000.00, 5.00, 4000000.00, 18000000.00, 2, 20000000.00, 270000000.00, 6000000.00, 2700000000.00, 3000000.00, 1350000000.00, 6, 7),
(NOW(), NOW(), 10000000.00, 80000000.00, 4.00, 4000000.00, 20000000.00, 2, 20000000.00, 298200000.00, 6000000.00, 2982000000.00, 3000000.00, 1491000000.00, 7, 4),
(NOW(), NOW(), 15000000.00, 200000000.00, 5.00, 6000000.00, 30000000.00, 3, 30000000.00, 495000000.00, 9000000.00, 4950000000.00, 4500000.00, 1650000000.00, 8, 5),
(NOW(), NOW(), 5000000.00, 0.00, 0.00, 2000000.00, 9000000.00, 1, 10000000.00, 135000000.00, 3000000.00, 1350000000.00, 1500000.00, 1350000000.00, 9, 7),
(NOW(), NOW(), 5000000.00, 50000000.00, 5.00, 2000000.00, 9500000.00, 1, 10000000.00, 139900000.00, 3000000.00, 1399000000.00, 1500000.00, 1399000000.00, 10, 8);

-- 37. Thêm thanh toán cho đơn hàng mới
INSERT INTO payment (create_at, update_at, amount, due_date, number_cycle, payment_date, payment_form, payment_method, penalty_amount, status, vnpaycode, order_id) VALUES
(NOW(), NOW(), 2700000000.00, NOW(), 1, NOW(), 'Chuyển khoản ngân hàng', 'VNPAY', 0.00, 'PAID', 'VNP20241120001', 6),
(NOW(), NOW(), 2982000000.00, NOW(), 1, NOW(), 'Thanh toán qua VNPay', 'VNPAY', 0.00, 'PAID', 'VNP20241121001', 7),
(NOW(), NOW(), 4950000000.00, NOW(), 1, NOW(), 'Chuyển khoản doanh nghiệp', 'VNPAY', 0.00, 'PAID', 'VNP20241122001', 8),
(NOW(), NOW(), 350000000.00, NOW(), 1, NOW(), 'Trả góp 4 kỳ', 'CASH', 0.00, 'PAID', NULL, 10),
(NOW(), NOW(), 350000000.00, DATE_ADD(NOW(), INTERVAL 3 MONTH), 2, NULL, 'Trả góp 4 kỳ', 'VNPAY', 0.00, 'UNPAID', NULL, 10),
(NOW(), NOW(), 350000000.00, DATE_ADD(NOW(), INTERVAL 6 MONTH), 3, NULL, 'Trả góp 4 kỳ', 'VNPAY', 0.00, 'UNPAID', NULL, 10),
(NOW(), NOW(), 349000000.00, DATE_ADD(NOW(), INTERVAL 9 MONTH), 4, NULL, 'Trả góp 4 kỳ', 'VNPAY', 0.00, 'UNPAID', NULL, 10);

-- 38. Thêm phản hồi
INSERT INTO feedback (create_at, update_at, feedback_content, status, feedback_title, customer_id) VALUES
(NOW(), NOW(), 'Xe VF9 rộng rãi, phù hợp gia đình đông người', 'PROCESSED', 'Review VF9', 5),
(NOW(), NOW(), 'Thắc mắc về chính sách bảo hành pin', 'IN_PROCESSED', 'Hỏi về bảo hành', 6),
(NOW(), NOW(), 'Muốn biết thêm về gói dịch vụ sạc điện', 'NOT_YET_PROCESSED', 'Dịch vụ sạc điện', 7),
(NOW(), NOW(), 'Xe BYD Seal có màu nào đẹp nhất?', 'NOT_YET_PROCESSED', 'Tư vấn màu xe', 9);

-- 39. Xử lý phản hồi
INSERT INTO feedback_handling (create_at, update_at, feedback_handling_content, feedback_handling_method, status, employee_id, feedback_id) VALUES
(NOW(), NOW(), 'Đã ghi nhận đánh giá tích cực của khách hàng', 'EMAIL', 'COMPLETE', 6, 5);

-- 40. Lịch hẹn lái thử
INSERT INTO test_drive_appointment (create_at, update_at, date_of_appointment, status, time_of_appointment, customer_id, vehicle_id) VALUES
(NOW(), NOW(), '2024-12-22', 'SCHEDULED', '10:30:00', 9, 9),
(NOW(), NOW(), '2024-12-23', 'SCHEDULED', '15:00:00', 10, 11),
(NOW(), NOW(), '2024-12-21', 'ARRIVED', '11:00:00', 11, 13),
(NOW(), NOW(), '2024-12-19', 'CANCELLED', '14:30:00', 12, 14);

-- 41. Báo giá
INSERT INTO quote (create_at, update_at, status, total_amount, employee_id) VALUES
(NOW(), NOW(), 'PROCESSING', 2700000000.00, 3),
(NOW(), NOW(), 'CREATE', 1891000000.00, 6),
(NOW(), NOW(), 'PROCESSING', 1399000000.00, 7);

-- 42. Chi tiết báo giá
INSERT INTO quotation_detail (create_at, update_at, compulsory_insurance, discount, discount_percentage, license_plate_fee, material_insurance, quantity, `registration fee`, registration_tax, road_maintenance_mees, total_amount, vehicle_registration_service_fee, wholesale_price, quote_id, vehicle_type_detail_id) VALUES
(NOW(), NOW(), 10000000.00, 100000000.00, 5.00, 4000000.00, 18000000.00, 2, 20000000.00, 270000000.00, 6000000.00, 2700000000.00, 3000000.00, 1350000000.00, 4, 7),
(NOW(), NOW(), 5000000.00, 30000000.00, 3.00, 2000000.00, 10000000.00, 1, 10000000.00, 189100000.00, 3000000.00, 1891000000.00, 1500000.00, 1891000000.00, 5, 6),
(NOW(), NOW(), 5000000.00, 50000000.00, 5.00, 2000000.00, 9500000.00, 1, 10000000.00, 139900000.00, 3000000.00, 1399000000.00, 1500000.00, 1399000000.00, 6, 8);

-- 43. Yêu cầu nhập kho
INSERT INTO import_request (create_at, update_at, note, status, employee_id) VALUES
(NOW(), NOW(), 'Nhập kho cuối năm', 'APPROVED', 2),
(NOW(), NOW(), 'Bổ sung hàng hot', 'REQUESTED', 5);

-- 44. Chi tiết yêu cầu nhập kho
INSERT INTO import_request_detail (create_at, update_at, quantity, import_request_id, vehicle_type_detail_id) VALUES
(NOW(), NOW(), 10, 4, 1),
(NOW(), NOW(), 8, 4, 2),
(NOW(), NOW(), 5, 5, 3),
(NOW(), NOW(), 5, 5, 4);

-- 45. Phiếu nhập kho bổ sung
INSERT INTO warehouse_receipt (create_at, update_at, note, reason, status, total_amount, warehouse_receipt_date, agency_id, employee_id) VALUES
(NOW(), NOW(), 'Nhập kho đợt 3', 'Nhập hàng cuối năm', 'COMPLETED', 4050000000.00, NOW(), 3, 7);

-- 46. Chi tiết phiếu nhập kho
INSERT INTO warehouse_receipt_detail (warehouse_receipt_id, vehicle_id) VALUES
(3, 8), (3, 9), (3, 10);

-- 47. Phiếu xuất kho
INSERT INTO warehouse_release_note (create_at, update_at, note, release_date, total_amount, employee_id, order_id) VALUES
(NOW(), NOW(), 'Xuất kho giao cho khách VIP', NOW(), 2700000000.00, 3, 6),
(NOW(), NOW(), 'Xuất kho giao cho đại lý', NOW(), 4950000000.00, 8, 8);

-- 48. Chi tiết phiếu xuất kho
INSERT INTO warehouse_release_note_detail (warehouse_release_note_id, vehicle_id) VALUES
(3, 11), (3, 12),
(4, 13), (4, 14), (4, 15);

-- 49. Giao hàng
INSERT INTO vehicle_delivery (create_at, update_at, expected_delivery_date, delivery_date, status, employee_id, order_id) VALUES
(NOW(), NOW(), '2024-11-28', '2024-11-28', 'DELIVERED', 3, 6),
(NOW(), NOW(), '2024-12-05', NULL, 'DELIVERING', 6, 7),
(NOW(), NOW(), '2024-11-30', '2024-11-30', 'DELIVERED', 8, 8),
(NOW(), NOW(), '2025-01-10', NULL, 'PREPARING', 3, 10);

-- 50. Doanh số tháng 11/2024
INSERT INTO monthly_sales (create_at, update_at, sales_amount, sales_month, agency_id) VALUES
(NOW(), NOW(), 5800000000.00, '2024-11-01', 1),
(NOW(), NOW(), 4900000000.00, '2024-11-01', 2),
(NOW(), NOW(), 4200000000.00, '2024-11-01', 3),
(NOW(), NOW(), 1500000000.00, '2024-11-01', 4);

-- KẾT THÚC SCRIPT
-- Tổng cộng đã insert dữ liệu cho 33 bảng trong hệ thống EV Sales Management