package com.uetravel.DTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestMySQLConnection {
    public TestMySQLConnection() {
    }

    public static void main(String[] args) {
        try {
            // Lấy kết nối từ MySQLConnection
            Connection connection = MySQLConnection.getConnection();

            // Tạo Statement để thực thi truy vấn
            Statement statement = connection.createStatement();

            // Truy vấn SQL để lấy thông tin từ bảng customers.html
            String query = "SELECT * FROM customers";

            // Thực thi truy vấn và lưu kết quả vào ResultSet
            ResultSet resultSet = statement.executeQuery(query);

            // Duyệt qua các kết quả trả về và in thông tin khách hàng
            while (resultSet.next()) {
                // In thông tin từng khách hàng (giả sử các cột là customerId, customerName, ...)
                int customerId = resultSet.getInt("CustomerID");
                String customerName = resultSet.getString("CustomerName");
                String email = resultSet.getString("Email");
                String phoneNumber = resultSet.getString("PhoneNumber");
                String address = resultSet.getString("Address");

                System.out.println("ID: " + customerId + ", Name: " + customerName +
                        ", Email: " + email + ", Phone: " + phoneNumber +
                        ", Address: " + address);
            }

            // Đóng kết nối
            MySQLConnection.closeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
