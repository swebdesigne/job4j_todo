package ru.job4j.todo.utils;

public enum TaskActionStatus {
    NOT_UPDATE("Не удалось обновить!"),
    NOT_DELETE("Не удалось удалить!"),
    NOT_COMPLETE("Не удалось обновить статус!");

    private final String status;

    TaskActionStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
