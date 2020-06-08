package uts.isd.group30.model;

import java.time.LocalDateTime;

public class AccessLog {

    private int logId;
    private Integer customerId;
    private Integer staffId;
    private String eventType;
    private LocalDateTime timeStamp;

    AccessLog() {
    }

    public AccessLog(int logId, Integer customerId, Integer staffId, String eventType, LocalDateTime timeStamp) {
        this.logId = logId;
        this.customerId = customerId;
        this.staffId = staffId;
        this.eventType = eventType;
        this.timeStamp = timeStamp;
    }

    public AccessLog(Integer customerId, Integer staffId, String eventType, LocalDateTime timeStamp) {
        this.customerId = customerId;
        this.staffId = staffId;
        this.eventType = eventType;
        this.timeStamp = timeStamp;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

}
