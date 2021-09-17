package com.example.monthlychallenge;

public class Challenge {
    private String period;
    private String count;
    private String item;
    private String success;

    public Challenge(){};

    public Challenge(String period, String count, String item, String success){
        period = this.period;
        count = this.count;
        item = this.item;
        success = this.success;
    };

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
