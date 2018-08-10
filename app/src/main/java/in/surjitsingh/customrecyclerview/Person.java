package in.surjitsingh.customrecyclerview;

public class Person {

    private String name, desc, date, time;
    private int uid;

    public Person(String name, String desc, String date, String time, int uid) {
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.time = time;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
