package BTVN.Exercise06;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final TaskManagement manager = new TaskManagement();

    public static void main(String[] args) {
        while (true) {
            System.out.println("MENU TO-DO LIST");
            System.out.println("1. Thêm công việc");
            System.out.println("2. Hiển thị công việc");
            System.out.println("3. Cập nhật trạng thái");
            System.out.println("4. Xóa công việc");
            System.out.println("5. Tìm kiếm công việc");
            System.out.println("6. Thống kê công việc");
            System.out.println("7. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: addTask();
                    break;
                case 2: listTasks();
                    break;
                case 3: updateStatus();
                    break;
                case 4: deleteTask();
                    break;
                case 5: searchTask();
                    break;
                case 6: manager.taskStatistics();
                    break;
                case 7:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void addTask() {
        String name = inputNotEmpty("Tên công việc: ");
        String status = inputStatus("Trạng thái (chưa hoàn thành / đã hoàn thành): ");
        manager.addTask(name, status);
    }

    private static void listTasks() {
        List<Task> tasks = manager.listTasks();
        if (tasks.isEmpty()) {
            System.out.println("📭 Không có công việc nào.");
        } else {
            tasks.forEach(t -> System.out.printf("ID: %d | Tên: %s | Trạng thái: %s%n",
                    t.getId(), t.getTaskName(), t.getStatus()));
        }
    }

    private static void updateStatus() {
        int id = inputInt("Nhập ID cần cập nhật: ");
        String status = inputStatus("Trạng thái mới (chưa hoàn thành / đã hoàn thành): ");
        manager.updateTaskStatus(id, status);
    }

    private static void deleteTask() {
        int id = inputInt("Nhập ID cần xóa: ");
        manager.deleteTask(id);
    }

    private static void searchTask() {
        String name = inputNotEmpty("Nhập tên công việc cần tìm: ");
        List<Task> tasks = manager.searchTaskByName(name);
        if (tasks.isEmpty()) {
            System.out.println("Không tìm thấy.");
        } else {
            tasks.forEach(t -> System.out.printf("ID: %d | Tên: %s | Trạng thái: %s%n",
                    t.getId(), t.getTaskName(), t.getStatus()));
        }
    }

    //  Hàm nhập có kiểm tra
    private static String inputNotEmpty(String msg) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("Không được để trống.");
        }
    }

    private static int inputInt(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên.");
            }
        }
    }

    private static String inputStatus(String msg) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("chưa hoàn thành") || input.equals("đã hoàn thành")) {
                return input;
            }
            System.out.println("Trạng thái chỉ nhận: 'chưa hoàn thành' hoặc 'đã hoàn thành'.");
        }
    }
}
