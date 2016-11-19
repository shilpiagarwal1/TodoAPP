package todolist.project.com.todolistproject;

/**
 * Created by Pankaj on 9/24/2016.
 */
public class ListBean{
    private int id;
    private  String title;
    private  String desc;
    private String date;

    public ListBean() {
    }

    public ListBean(int id,String title, String desc, String date, int status) {

        this.title = title;
        this.desc = desc;
        this.date = date;
          this.id=id;
        this.status = status;
    }

    public ListBean(String title, String desc, String date, int status) {

        this.title = title;
        this.desc = desc;
        this.date = date;
        this.id=id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private  int status;

}
