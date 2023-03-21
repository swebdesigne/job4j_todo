package ru.job4j.todo.utils;

import ru.job4j.todo.model.Task;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;

public final class TaskTimezoneSetter {
    private TaskTimezoneSetter() {
    }

    public static void setTimezone(Task task) {
        var user = task.getUser();
        var zoneId = user.getTimezone() != null ? ZoneId.of(user.getTimezone()) : TimeZone.getDefault().toZoneId();
        task.setCreated(task.getCreated()
                .atZone(ZoneOffset.systemDefault())
                .withZoneSameInstant(zoneId).toLocalDateTime()
        );
    }
}
