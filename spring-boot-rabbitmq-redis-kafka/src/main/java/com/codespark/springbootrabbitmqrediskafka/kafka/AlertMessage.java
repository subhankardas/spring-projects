package com.codespark.springbootrabbitmqrediskafka.kafka;

enum AlertType {
    NOTIFICATION, AUDIT, CLEANUP;
}

public class AlertMessage<T> {

    private long id;
    private AlertType type;
    private T content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AlertType getType() {
        return type;
    }

    public void setType(AlertType type) {
        this.type = type;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "(id=" + id + ", type=" + type + ", content=" + content + ")";
    }

}
