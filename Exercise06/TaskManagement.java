package BTVN.Exercise06;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManagement {
    public void addTask(String taskName, String status) {
        try (Connection conn = DriverManager.getConnection(Database.URL, Database.USER, Database.PASSWORD);
             CallableStatement stmt = conn.prepareCall("{CALL add_task(?, ?)}")) {
            stmt.setString(1, taskName);
            stmt.setString(2, status);
            stmt.execute();
            System.out.println("Thêm công việc thành công.");
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm công việc: " + e.getMessage());
        }
    }

    public List<Task> listTasks() {
        List<Task> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Database.URL, Database.USER, Database.PASSWORD);
             CallableStatement stmt = conn.prepareCall("{CALL list_tasks()}")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Task(
                        rs.getInt("id"),
                        rs.getString("task_name"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách công việc: " + e.getMessage());
        }
        return list;
    }

    public void updateTaskStatus(int taskId, String status) {
        try (Connection conn = DriverManager.getConnection(Database.URL, Database.USER, Database.PASSWORD);
             CallableStatement stmt = conn.prepareCall("{CALL update_task_status(?, ?)}")) {
            stmt.setInt(1, taskId);
            stmt.setString(2, status);
            stmt.execute();
            System.out.println("Cập nhật trạng thái thành công.");
        } catch (SQLException e) {
            System.out.println("Lỗi cập nhật: " + e.getMessage());
        }
    }

    public void deleteTask(int taskId) {
        try (Connection conn = DriverManager.getConnection(Database.URL, Database.USER, Database.PASSWORD);
             CallableStatement stmt = conn.prepareCall("{CALL delete_task(?)}")) {
            stmt.setInt(1, taskId);
            stmt.execute();
            System.out.println("Xóa thành công.");
        } catch (SQLException e) {
            System.out.println("Lỗi xóa: " + e.getMessage());
        }
    }

    public List<Task> searchTaskByName(String name) {
        List<Task> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Database.URL, Database.USER, Database.PASSWORD);
             CallableStatement stmt = conn.prepareCall("{CALL search_task_by_name(?)}")) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Task(
                        rs.getInt("id"),
                        rs.getString("task_name"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi tìm kiếm: " + e.getMessage());
        }
        return list;
    }

    public void taskStatistics() {
        try (Connection conn = DriverManager.getConnection(Database.URL, Database.USER, Database.PASSWORD);
             CallableStatement stmt = conn.prepareCall("{CALL task_statistics()}")) {
            ResultSet rs = stmt.executeQuery();
            System.out.println("Thống kê công việc:");
            while (rs.next()) {
                System.out.printf("- %s: %d%n", rs.getString("status"), rs.getInt("total"));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi thống kê: " + e.getMessage());
        }
    }
}
