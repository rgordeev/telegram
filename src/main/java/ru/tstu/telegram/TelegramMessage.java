package ru.tstu.telegram;

public class TelegramMessage {
    private Long id;
    private String userId;
    private String payload;

    public TelegramMessage(String userId, String payload) {
        this.userId = userId;
        this.payload = payload;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
