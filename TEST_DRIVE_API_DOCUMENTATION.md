# 📋 Test Drive Appointment API Documentation

## Tổng quan
API quản lý lịch hẹn lái thử xe (Test Drive Appointment) hỗ trợ đầy đủ các thao tác CRUD.

---

## 🔐 Phân quyền
Tất cả các endpoint yêu cầu phân quyền: **DEALER_MANAGER** hoặc **DEALER_STAFF**

---

## 📌 Endpoints

### 1. CREATE - Tạo lịch hẹn lái thử mới

```http
POST /test-drive-appointments
```

**Headers:**
```
Authorization: Bearer {token}
Content-Type: application/json
```

**Request Body:**
```json
{
  "customerId": 1,
  "vehicleId": 5,
  "dateOfAppointment": "2025-11-30",
  "timeOfAppointment": "14:30:00"
}
```

**Response Success (200 OK):**
```json
{
  "success": true,
  "message": "Tạo lịch hẹn lái thử thành công",
  "data": {
    "testDriveAppointmentId": 10,
    "dateOfAppointment": "2025-11-30",
    "timeOfAppointment": "14:30:00",
    "status": "PENDING",
    "customerName": "Nguyễn Văn A",
    "vehicleId": 5
  }
}
```

**Validation:**
- `customerId`: Bắt buộc, phải tồn tại trong hệ thống
- `vehicleId`: Bắt buộc, phải tồn tại trong hệ thống
- `dateOfAppointment`: Bắt buộc, định dạng YYYY-MM-DD
- `timeOfAppointment`: Bắt buộc, định dạng HH:mm:ss

---

### 2. READ - Lấy danh sách lịch hẹn

```http
GET /test-drive-appointments?page=1&size=10&status=PENDING
```

**Headers:**
```
Authorization: Bearer {token}
```

**Query Parameters:**
| Tham số | Kiểu | Mặc định | Bắt buộc | Mô tả |
|---------|------|----------|----------|-------|
| page | Integer | 1 | Không | Số trang |
| size | Integer | 10 | Không | Số bản ghi mỗi trang |
| status | String | null | Không | Lọc theo trạng thái (PENDING, SCHEDULED, ARRIVED, CANCELLED) |

**Response Success (200 OK):**
```json
{
  "success": true,
  "message": "Lấy danh sách lịch hẹn lái thử thành công",
  "data": [
    {
      "testDriveAppointmentId": 1,
      "dateOfAppointment": "2025-11-25",
      "timeOfAppointment": "10:00:00",
      "status": "PENDING",
      "customerName": "Nguyễn Văn A",
      "vehicleId": 5
    },
    {
      "testDriveAppointmentId": 2,
      "dateOfAppointment": "2025-11-26",
      "timeOfAppointment": "15:30:00",
      "status": "SCHEDULED",
      "customerName": "Trần Thị B",
      "vehicleId": 8
    }
  ]
}
```

---

### 3. READ - Lấy chi tiết lịch hẹn theo ID

```http
GET /test-drive-appointments/{testDriveAppointmentId}
```

**Headers:**
```
Authorization: Bearer {token}
```

**Response Success (200 OK):**
```json
{
  "success": true,
  "message": "Lấy thông tin lịch hẹn lái thử thành công",
  "data": {
    "testDriveAppointmentId": 1,
    "dateOfAppointment": "2025-11-25",
    "timeOfAppointment": "10:00:00",
    "status": "PENDING",
    "customerId": 1,
    "customerName": "Nguyễn Văn A",
    "email": "nguyenvana@email.com",
    "phoneNumber": "0123456789",
    "vehicleId": 5,
    "chassicNumber": "VIN123456789",
    "machineNumber": "ENG987654321",
    "color": "Trắng",
    "version": "Plus",
    "features": "Tự động, Camera 360",
    "vehicleTypeName": "VinFast VF e34",
    "manufactureYear": 2024
  }
}
```

---

### 4. UPDATE - Cập nhật thông tin lịch hẹn

```http
PUT /test-drive-appointments/{id}
```

**Headers:**
```
Authorization: Bearer {token}
Content-Type: application/json
```

**Request Body:**
```json
{
  "customerId": 2,
  "vehicleId": 10,
  "dateOfAppointment": "2025-12-01",
  "timeOfAppointment": "16:00:00"
}
```

**Lưu ý:** Tất cả các field đều là tùy chọn. Chỉ cần gửi các field cần cập nhật.

**Response Success (200 OK):**
```json
{
  "success": true,
  "message": "Cập nhật lịch hẹn lái thử thành công",
  "data": {
    "testDriveAppointmentId": 1,
    "dateOfAppointment": "2025-12-01",
    "timeOfAppointment": "16:00:00",
    "status": "PENDING",
    "customerName": "Trần Thị B",
    "vehicleId": 10
  }
}
```

---

### 5. PATCH - Cập nhật trạng thái lịch hẹn

```http
PATCH /test-drive-appointments/{id}?status=SCHEDULED
```

**Headers:**
```
Authorization: Bearer {token}
```

**Query Parameters:**
| Tham số | Kiểu | Bắt buộc | Giá trị hợp lệ |
|---------|------|----------|----------------|
| status | String | Có | PENDING, SCHEDULED, ARRIVED, CANCELLED |

**Response Success (200 OK):**
```json
{
  "success": true,
  "message": null,
  "data": {
    "testDriveAppointmentId": 1,
    "dateOfAppointment": "2025-11-25",
    "timeOfAppointment": "10:00:00",
    "status": "SCHEDULED",
    "customerName": "Nguyễn Văn A",
    "vehicleId": 5
  }
}
```

**Business Rules:**
- Không thể chuyển từ trạng thái `ARRIVED` sang `CANCELLED`

---

### 6. DELETE - Xóa lịch hẹn

```http
DELETE /test-drive-appointments/{id}
```

**Headers:**
```
Authorization: Bearer {token}
```

**Response Success (200 OK):**
```json
{
  "success": true,
  "message": "Xóa lịch hẹn lái thử thành công",
  "data": null
}
```

**Business Rules:**
- Chỉ có thể xóa lịch hẹn có trạng thái `PENDING` hoặc `CANCELLED`
- Không thể xóa lịch hẹn có trạng thái `SCHEDULED` hoặc `ARRIVED`

---

## 🔄 Trạng thái (Status Enum)

| Trạng thái | Mô tả |
|------------|-------|
| PENDING | Chờ xác nhận |
| SCHEDULED | Đã xác nhận |
| ARRIVED | Đã đến |
| CANCELLED | Đã hủy |

---

## ❌ Error Responses

### 400 Bad Request
```json
{
  "success": false,
  "message": "Ngày hẹn không được để trống",
  "data": null
}
```

### 404 Not Found
```json
{
  "success": false,
  "message": "Không tìm thấy lịch hẹn lái thử với id: 999",
  "data": null
}
```

### 403 Forbidden
```json
{
  "success": false,
  "message": "Access Denied",
  "data": null
}
```

---

## 📝 Use Cases

### Use Case 1: Tạo lịch hẹn mới
1. Frontend gọi API để lấy danh sách khách hàng
2. Frontend gọi API để lấy danh sách xe có sẵn
3. Người dùng chọn khách hàng, xe, ngày giờ
4. Gọi `POST /test-drive-appointments`
5. Hệ thống tạo lịch hẹn với trạng thái mặc định là `PENDING`

### Use Case 2: Xác nhận lịch hẹn
1. Nhân viên xem danh sách lịch hẹn `PENDING`
2. Nhấn nút "Xác nhận"
3. Gọi `PATCH /test-drive-appointments/{id}?status=SCHEDULED`

### Use Case 3: Khách hàng đến lái thử
1. Khách hàng đến đại lý
2. Nhân viên tìm lịch hẹn theo tên/số điện thoại
3. Nhấn nút "Đã đến"
4. Gọi `PATCH /test-drive-appointments/{id}?status=ARRIVED`

### Use Case 4: Hủy lịch hẹn
1. Khách hàng gọi yêu cầu hủy
2. Nhân viên tìm lịch hẹn
3. Gọi `PATCH /test-drive-appointments/{id}?status=CANCELLED`

### Use Case 5: Xóa lịch hẹn không hợp lệ
1. Nhân viên xem lịch hẹn đã hủy
2. Chọn lịch hẹn cần xóa (chỉ PENDING hoặc CANCELLED)
3. Gọi `DELETE /test-drive-appointments/{id}`

---

## 🧪 Testing với Postman/Curl

### Tạo lịch hẹn mới
```bash
curl -X POST http://localhost:8080/test-drive-appointments \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "customerId": 1,
    "vehicleId": 5,
    "dateOfAppointment": "2025-11-30",
    "timeOfAppointment": "14:30:00"
  }'
```

### Lấy danh sách
```bash
curl -X GET "http://localhost:8080/test-drive-appointments?page=1&size=10&status=PENDING" \
  -H "Authorization: Bearer YOUR_TOKEN"
```

### Cập nhật trạng thái
```bash
curl -X PATCH "http://localhost:8080/test-drive-appointments/1?status=SCHEDULED" \
  -H "Authorization: Bearer YOUR_TOKEN"
```

### Xóa lịch hẹn
```bash
curl -X DELETE http://localhost:8080/test-drive-appointments/1 \
  -H "Authorization: Bearer YOUR_TOKEN"
```

---

## 🎯 Best Practices

1. **Luôn kiểm tra phân quyền:** Đảm bảo user có role `DEALER_MANAGER` hoặc `DEALER_STAFF`
2. **Validate dữ liệu đầu vào:** Kiểm tra customerId và vehicleId trước khi gửi request
3. **Xử lý error:** Hiển thị thông báo lỗi rõ ràng cho người dùng
4. **Cache:** API GET by ID đã được cache, tận dụng cache để giảm load
5. **Pagination:** Luôn sử dụng pagination khi lấy danh sách để tránh quá tải

---

## ✅ Checklist hoàn thiện CRUD

- [x] **CREATE:** Tạo lịch hẹn mới
- [x] **READ:** Lấy danh sách lịch hẹn (có phân trang, lọc)
- [x] **READ:** Lấy chi tiết lịch hẹn theo ID
- [x] **UPDATE:** Cập nhật toàn bộ thông tin lịch hẹn
- [x] **PATCH:** Cập nhật trạng thái lịch hẹn
- [x] **DELETE:** Xóa lịch hẹn
- [x] **Validation:** Kiểm tra dữ liệu đầu vào
- [x] **Authorization:** Phân quyền cho từng endpoint
- [x] **Error Handling:** Xử lý lỗi đầy đủ
- [x] **Business Logic:** Áp dụng quy tắc nghiệp vụ

---

**Hoàn tất!** 🎉
