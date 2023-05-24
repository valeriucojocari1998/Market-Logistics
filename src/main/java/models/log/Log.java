package models.log;

import enums.LogType;

import java.util.UUID;

public class Log {
    private String id;
    private LogType type;
    private String parentId;
    private String message;

    public Log(LogType type, String parentId, String message) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.parentId = parentId;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public LogType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public String getParentId() {
        return parentId;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
