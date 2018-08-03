package in.surjitsingh.customrecyclerview;

public class Person {

    String name, desc;
    int uid;

    public Person(String name, String desc, int uid) {
        this.name = name;
        this.desc = desc;
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

    public int getUid() {
        return uid;
    }

    public void setUid(int id) {
        this.uid = uid;
    }
}
