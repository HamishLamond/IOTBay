package uts.isd.group30.model;

import java.util.Date;

public class AccessLog {
    private int logId;
    private Integer customerId;
    private Integer staffId;
    private String eventType;
    private Date timeStamp;
    
    AccessLog() {}
    
    public AccessLog(int logId, Integer customerId, Integer staffId, String eventType, Date timeStamp) {
        this.logId = logId;
        this.customerId = customerId;
        this.staffId = staffId;
        this.eventType = eventType;
        this.timeStamp = timeStamp;
    }
    
    public AccessLog(Integer customerId, Integer staffId, String eventType, Date timeStamp) {
        this.customerId = customerId;
        this.staffId = staffId;
        this.eventType = eventType;
        this.timeStamp = timeStamp;
    }
    
    public int getLogId()
    {
        return logId;
    }
    
    public void setLogId(int logId)
    {
        this.logId = logId;
    }
    
    public int getCustomerId()
    {
        return customerId;
    }
    
    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
    }
    
    public int getStaffId()
    {
        return staffId;
    }
    
    public void setStaffId(int staffId)
    {
        this.staffId = staffId;
    }
    
    public String getEventType()
    {
        return eventType;
    }
    
    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }
    
    public Date getTimeStamp()
    {
        return timeStamp;
    }
    
    public void setTimeStamp(Date timeStamp)
    {
        this.timeStamp = timeStamp;
    }
    
    
            
}
