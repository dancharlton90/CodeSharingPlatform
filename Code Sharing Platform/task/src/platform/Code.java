package platform;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String code;

    @Column
    private LocalDateTime date;

    @Column
    private int time;
    @Column
    private LocalDateTime expiryTime;

    @Column
    private Integer views;
    @Column
    private boolean hasViewsLimit;

    @Column
    private String uuid;

    @Column
    private boolean isSecret;


    public Code() {
        this.date = LocalDateTime.now();
        this.uuid = UUID.randomUUID().toString();
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        LocalDateTime localDateTime = date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }

    @JsonIgnore
    public LocalDateTime getRawDate() {
        return date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        if (isZeroOrLess(time)) {
            this.time = 0;
            this.isSecret = false;
            this.expiryTime = null;
        } else {
            this.time = time;
            this.isSecret = true;
            this.expiryTime = date.plusSeconds(time);
        }
    }

    public void setRemainingTime(int remainingTime) {
        this.time = remainingTime;
        this.isSecret = true;
    }

    @JsonIgnore
    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        if (isZeroOrLess(views)) {
            this.views = 0;
            this.isSecret = false;
            this.hasViewsLimit = false;
        } else {
            this.views = views;
            this.isSecret = true;
            this.hasViewsLimit = true;
        }
    }

    @JsonIgnore
    public boolean hasViewsLimit() {
        return hasViewsLimit;
    }

    public void setHasViewsLimit(boolean hasViewsLimit) {
        this.hasViewsLimit = hasViewsLimit;
    }

    @JsonIgnore
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @JsonIgnore
    public boolean isSecret() {
        return isSecret;
    }

    private boolean isZeroOrLess(Number number) {
        if (number.longValue() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Code:" + getCode() + " Date:" +getDate() + " Time:" +getTime() + " Views:" +getViews();
    }

    public void decreaseViewCount() {
        this.views--;
    }

}
