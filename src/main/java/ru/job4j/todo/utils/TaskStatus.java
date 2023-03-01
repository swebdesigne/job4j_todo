package ru.job4j.todo.utils;

public enum TaskStatus {
    NO_TASK("У вас нет задач!"),
    NO_COMPLETED_TASK("У вас нет завершенных задач!"),
    NO_NEW_TASK("У вас нет новых задач!");
    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
