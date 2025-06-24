CREATE DATABASE todo_app;
USE todo_app;

CREATE TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    task_name VARCHAR(255) NOT NULL,
    status ENUM('chưa hoàn thành', 'đã hoàn thành') NOT NULL
);

-- Stored Procedure
-- Thêm công việc
DELIMITER //
CREATE PROCEDURE add_task(IN p_name VARCHAR(255), IN p_status VARCHAR(50))
BEGIN
    INSERT INTO tasks(task_name, status)
    VALUES (p_name, p_status);
END //
DELIMITER ;

-- Liệt kê công việc
DELIMITER //
CREATE PROCEDURE list_tasks()
BEGIN
    SELECT * FROM tasks;
END //
DELIMITER ;

-- Cập nhật trạng thái
DELIMITER //
CREATE PROCEDURE update_task_status(IN p_id INT, IN p_status VARCHAR(50))
BEGIN
    UPDATE tasks SET status = p_status WHERE id = p_id;
END //
DELIMITER ;

-- Xóa công việc
DELIMITER //
CREATE PROCEDURE delete_task(IN p_id INT)
BEGIN
    DELETE FROM tasks WHERE id = p_id;
END //
DELIMITER ;

-- Tìm kiếm theo tên
DELIMITER //
CREATE PROCEDURE search_task_by_name(IN p_name VARCHAR(255))
BEGIN
    SELECT * FROM tasks
    WHERE task_name LIKE CONCAT('%', p_name, '%');
END //
DELIMITER ;

-- Thống kê công việc
DELIMITER //
CREATE PROCEDURE task_statistics()
BEGIN
    SELECT status, COUNT(*) AS total
    FROM tasks
    GROUP BY status;
END //
DELIMITER ;
