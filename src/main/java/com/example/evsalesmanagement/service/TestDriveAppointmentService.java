package com.example.evsalesmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.evsalesmanagement.repository.TestDriveAppointmentRepository;

@Service
public class TestDriveAppointmentService {

    @Autowired
    private TestDriveAppointmentRepository testDriveAppointmentRepository;

    /**
     * Lấy tất cả lịch hẹn lái thử với thông tin khách hàng
     * @return Danh sách tất cả lịch hẹn
     */
    public List<Object[]>getAllTestDriveAppointment() {
        return testDriveAppointmentRepository.getAllTestDriveAppointment();
    }

    /**
     * Lấy danh sách lịch hẹn có trạng thái "Chờ xác nhận"
     * @return Danh sách lịch hẹn chờ xác nhận
     */
    public List<Object[]> layLichHenChoXacNhan() {
        return testDriveAppointmentRepository.layLichHenChoXacNhan();
    }

    /**
     * Lấy danh sách lịch hẹn có trạng thái "Đã xác nhận"
     * @return Danh sách lịch hẹn đã xác nhận
     */
    public List<Object[]> layLichHenDaXacNhan() {
        return lichHenRepository.layLichHenDaXacNhan();
    }
}