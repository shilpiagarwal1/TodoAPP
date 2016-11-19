package todolist.project.com.todolistproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private static final int MENU_ID_01 = 100;
    private static final int MENU_ID_02 = 101;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://todolist.project.com.todolistproject/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://todolist.project.com.todolistproject/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public class codeLearnList {
        String title;
        String Desc;
    }

    Cursor c;


    EditText title, description;
    Button btnSubmit,btnupdate;
    Button btnCancel,btnedit_cancel;
    Dialog dialog,dialog1;
    List<ListBean> list;
    ArrayAdapter<String> myAdapter;
    DatePicker datePicker,datePicker1;
    Context context;
     EditText tv1,tv2;

    NSqliteHelper db = new NSqliteHelper(this);

    ArrayList<ListBean> AllList;
    ListView listView;
    CodeListAdapter ListAdapter;
    ListBean lBean;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AllList = db.getAllLists1();
       //  Collections.sort(AllList);

        ListAdapter = new CodeListAdapter(MainActivity.this, AllList);
        ListView listView = (ListView) findViewById(R.id.ListText);
        listView.setAdapter(ListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {


                                            int id=AllList.get(position).getId();


                                            dialog1=new Dialog(MainActivity.this,android.R.style.Theme_Translucent);
                                            dialog1.setContentView(R.layout.update_dialog);
                                            dialog1.show();
                                            tv1 = (EditText) dialog1.findViewById(R.id.edit_title);
                                            tv2 = (EditText) dialog1.findViewById(R.id.edit_description);
                                            btnupdate = (Button) dialog1.findViewById(R.id.update);
                                            btnedit_cancel = (Button) dialog1.findViewById(R.id.edit_cancel);

                                          datePicker1 = (DatePicker) dialog1.findViewById(R.id.edit_date);



                                             //    Toast.makeText(getApplicationContext(),"on click"+id,Toast.LENGTH_LONG).show(
                                            lBean=  db.readList(id);
                                         String dt=  lBean.getDate();
           //                             Toast.makeText(getApplicationContext(),"on click date"+dt,Toast.LENGTH_LONG).show();

                                            int day = Integer.valueOf(dt.substring(0,2));
                                            int  month =Integer.valueOf( dt.substring(3,5));
                                            int  year = Integer.valueOf(dt.substring(6,10));
                                          //  Toast.makeText(getApplicationContext(),"on click date"+day+month+year,Toast.LENGTH_LONG).show();
                                            tv1.setText(lBean.getTitle());
                                            tv2.setText(lBean.getDesc());
                                            datePicker1.init(year,month-1,day,null);

                                                      btnupdate.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {

                                                                        String edittitle=tv1.getText().toString();
                                                                        String editdesc=tv2.getText().toString();

                                                                        if (edittitle.isEmpty()) {
                                                                            Toast.makeText(MainActivity.this, "Title cannot be blank", Toast.LENGTH_LONG).show();
                                                                            tv1.setError("Invalid Title");
                                                                        } else if (editdesc.isEmpty()) {
                                                                            Toast.makeText(MainActivity.this, "Description cannot be blank", Toast.LENGTH_LONG).show();
                                                                            tv2.setError("Invalid Description");
                                                                        }


                                                                        long dateTime = datePicker1.getCalendarView().getDate();
                                                                        Date d1 = new Date(dateTime);
                                                                        DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
                                                                        String editformattedDate = dateFormat1.format(d1);
                                                                        lBean.setTitle(edittitle);
                                                                        lBean.setDesc(editdesc);
                                                                        lBean.setDate(editformattedDate);

                                                                       // Toast.makeText(getApplicationContext()," edit date "+editformattedDate ,Toast.LENGTH_LONG).show();

                                                                        if (edittitle.toString().length() != 0 && editdesc.toString().length() != 0) {
                                                                            int check = db.updateList(lBean);

                                                                        if (check==1)
                                                                                    Toast.makeText(getApplicationContext(),"Update Successfully",Toast.LENGTH_LONG).show();
                                                                                    getAllLists();
                                                                                    dialog1.dismiss();
                                                                    }}
                                                                            });
                                                                            btnedit_cancel.setOnClickListener(new View.OnClickListener() {
                                                                                @Override
                                                                                public void onClick(View v) {
                                                                                    dialog1.dismiss();
                                                                                }
                                                                            });








            }
        });




        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {


            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, int i, long l) {

                    final int position=i;
                    AlertDialog.Builder alert = new AlertDialog.Builder(
                            MainActivity.this);
                    alert.setTitle("Delete!!");
                    alert.setMessage("Are you sure to delete this task?");
                    alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            db.deleteList(AllList.get(position).getId());
                            getAllLists();
                            ListAdapter.notifyDataSetChanged();
                            dialog.dismiss();

                        }
                    });
                    alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this,String.valueOf(AllList.get(position).getId()),Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });

                    alert.show();

                    return true;
            }
        });



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

                        int id = item.getItemId();

                        //noinspection SimplifiableIfStatement
                        if (id == R.id.openForm) {
                           // Toast.makeText(getApplicationContext(), "clicked on open Form", Toast.LENGTH_LONG).show();
                            showCustomDialog();
                            return true;
                        }




                        return super.onOptionsItemSelected(item);

    }







    protected void showCustomDialog() {


        dialog = new Dialog(MainActivity.this,
                android.R.style.Theme_Translucent);

        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);


        dialog.setContentView(R.layout.dialog);

        title = (EditText) dialog.findViewById(R.id.title);
        description = (EditText) dialog.findViewById(R.id.description);
        btnSubmit = (Button) dialog.findViewById(R.id.submit);
        btnCancel = (Button) dialog.findViewById(R.id.cancel);
        datePicker = (DatePicker) dialog.findViewById(R.id.date);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (title.getText().toString().length() == 0) {
                    Toast.makeText(MainActivity.this, "Title cannot be blank", Toast.LENGTH_LONG).show();
                    title.setError("Invalid Title");
                } else if (description.getText().toString().length() == 0) {
                    Toast.makeText(MainActivity.this, "Description cannot be blank", Toast.LENGTH_LONG).show();
                    description.setError("Invalid Description");
                }

                long dateTime = datePicker.getCalendarView().getDate();
                Date d = new Date(dateTime);
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String formattedDate = dateFormat.format(d);
                int status = 0;

                if (title.getText().toString().length() != 0 && description.getText().toString().length() != 0)
                {
                    ListBean lists = new ListBean(title.getText().toString(), description.getText().toString(), formattedDate, status);
                    db.createEmployee(lists);
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, "Add Successfully", Toast.LENGTH_LONG).show();
                }


                getAllLists();


            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }


    public void getAllLists() {
       AllList = new ArrayList<ListBean>();


        // select book query
        String query = "SELECT  * FROM  lists  ORDER BY  date   ASC";

                Cursor cursor = db.selectQuery(query);

        // parse all results

        if (cursor.moveToFirst()) {
            do {
                ListBean listBean = new ListBean();
                listBean.setTitle(cursor.getString(1));
                listBean.setDesc(cursor.getString(2));
                listBean.setId(cursor.getInt(0));
                listBean.setDate(cursor.getString(3));



                AllList.add(listBean);

                // Add book to books
            } while (cursor.moveToNext());
        }


        cursor.close();

        CodeListAdapter contactListAdapter = new CodeListAdapter(MainActivity.this, AllList);
        ListView listView = (ListView) findViewById(R.id.ListText);
        listView.setAdapter(contactListAdapter);


    }



}
