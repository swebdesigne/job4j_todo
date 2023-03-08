package ru.job4j.todo.utils;

public enum UserStatus {
    USER_ALREADY_EXISTS("Пользователь с таким именем уже существует!");
    private String status;

    UserStatus(String status) {
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
