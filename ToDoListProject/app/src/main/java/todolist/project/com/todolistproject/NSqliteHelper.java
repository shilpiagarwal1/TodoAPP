package todolist.project.com.todolistproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class NSqliteHelper extends SQLiteOpenHelper {

  /*  public class codeLearnList {
        String title;
        String Desc;
    }*/

    private static final int database_VERSION=1;
    // database name
    private static final String database_NAME = "ListDB";

    private static final String table_list = "lists";


    private static final String list_ID = "id";
    private static final String list_title = "title";
    private static final String List_Desc = "desc";
    private static final String List_date = "date";
    private static final String List_status = "status";


    private static final String[] COLUMNS = { list_ID, list_title, List_Desc,List_date,List_status };



    public NSqliteHelper(Context context) {

        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE lists("+"id INTEGER PRIMARY KEY AUTOINCREMENT,"+"title TEXT ,"+"desc TEXT,"+"date TEXT,"+"status INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS lists");
        this.onCreate(db);

    }

    public void createEmployee(ListBean lists) {
        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(list_title, lists.getTitle());
        values.put(List_Desc, lists.getDesc());

        values.put(List_date, lists.getDate());
        values.put(List_status, lists.getStatus());

        // insert book
        long x=db.insert(table_list, null, values);
        System.out.println("value of----x ---equals"+x);
        // close database transaction
        db.close();
    }

    public Cursor selectQuery(String query) {
        Cursor cursor=null;
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            cursor = db.rawQuery(query, null);



        } catch (Exception e) {

            System.out.println("DATABASE ERROR " + e);

        }
        return cursor;

    }

    public void deleteList(int id) {

        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // delete book
        db.delete(table_list, list_ID + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }
    public void delete(int anInt) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(table_list,list_ID+"=?",new String[]{String.valueOf(anInt)});
        db.close();
    }


    public int updateList(ListBean listBean) {

        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put("title", listBean.getTitle()); // get title
      values.put("desc", listBean.getDesc()); // get author
        values.put("date",listBean.getDate());

        // update
        int i = db.update(table_list, values, list_ID + " = ?", new String[] { String.valueOf(listBean.getId()) });

        db.close();
        return i;
    }

    public ListBean readList(int id) {
        // get reference of the BookDB database
        SQLiteDatabase db = this.getReadableDatabase();

    // get book query
        Cursor cursor = db.query(table_list,
                COLUMNS, " id = ?", new String[] { String.valueOf(id) }, null, null, null, null);

        // if results !=null, parse the first one
        if (cursor != null)
            cursor.moveToFirst();

        ListBean listBean = new ListBean();
        listBean.setId(Integer.parseInt(cursor.getString(0)));
        listBean.setTitle(cursor.getString(1));
        listBean.setDesc(cursor.getString(2));
       listBean.setDate(cursor.getString(3));
        return listBean;
    }

  public ArrayList<ListBean>  showCompletedTask() {
      ArrayList<ListBean> completeBean = new ArrayList<ListBean>();
      SQLiteDatabase db = this.getReadableDatabase();
      int x=1;
      // get book query
      Cursor cursor = db.query(table_list,
              COLUMNS, " status = ?", new String[] { String.valueOf(x) }, null, null, null, null);

      // if results !=null, parse the first one



      if (cursor.moveToFirst()) {
          do {
              ListBean listBean = new ListBean();
              listBean.setTitle(cursor.getString(1));
              listBean.setDesc(cursor.getString(2));
              listBean.setDate(cursor.getString(3));
              listBean.setId(cursor.getInt(0));

              completeBean.add(listBean);

              // Add book to books
          } while (cursor.moveToNext());
      }


      return completeBean;

  }

   public int getStatus(ListBean listBean)
   {   SQLiteDatabase db = this.getReadableDatabase();
        int lid=listBean.getId();
       Cursor cursor = db.query(table_list,
               COLUMNS, " id = ?", new String[] { String.valueOf(lid) }, null, null, null, null);
       if (cursor != null)
           cursor.moveToFirst();
       int check=Integer.valueOf(cursor.getString(4));

       return check;

   }

    public int updateStatus(int id,int status) {

        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();

        values.put("status", status); // get title

        // update
        int i = db.update(table_list, values, list_ID + " = ?", new String[] { String.valueOf(id) });

        db.close();
        return i;
    }



    public ArrayList<ListBean> getAllLists1() {
        ArrayList<ListBean> AllList = new ArrayList<ListBean>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(true, table_list, null, null, null, null, null, "date ASC", null);
        // select book query
       // String query = "SELECT  * FROM  lists ORDER BY  date  ASC ";


        // get reference of the BookDB database
        //    SQLiteDatabase db = this.getWritabl

        //  Cursor cursor = db.rawQuery(query, null);
        //Cursor cursor = db.Se==Query(query);
        // parse all results

        if (cursor.moveToFirst()) {
            do {
                ListBean listBean = new ListBean();
                listBean.setTitle(cursor.getString(1));
                listBean.setDesc(cursor.getString(2));
                listBean.setDate(cursor.getString(3));
                listBean.setId(cursor.getInt(0));

                AllList.add(listBean);

                // Add book to books
            } while (cursor.moveToNext());
        }


        cursor.close();


        return AllList;

    }

}
