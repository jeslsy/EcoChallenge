package com.example.monthlychallenge;

public class Challenger {
    private String id;
    private String count;
    private String goal;
    private String success;

    public Challenger(){};

    public Challenger(String id, String count, String goal, String success){
        this.id = id;
        this.count = count;
        this.goal = goal;
        this.success = success;
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
