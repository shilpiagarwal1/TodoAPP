package todolist.project.com.todolistproject;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Pankaj on 9/30/2016.
 */
public class CodeListAdapter extends BaseAdapter{

    Context context;
    ArrayList<ListBean> lBean;

    public CodeListAdapter(Context context, ArrayList<ListBean> list) {

        this.context = context;
        lBean = list;
    }
    @Override

    public int getCount() {
        // TODO Auto-generated method stub
        return lBean.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return lBean.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int position, View arg1, ViewGroup arg2) {
        final ListBean Lists = lBean.get(position);

        if(arg1==null)
        {
            Context context = arg2.getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            arg1 = inflater.inflate(R.layout.listitem, arg2,false);
        }

        TextView Title = (TextView)arg1.findViewById(R.id.textView1);

        TextView desc = (TextView)arg1.findViewById(R.id.textView2);

        TextView showdate = (TextView)arg1.findViewById(R.id.textview4);



        Title.setText(Lists.getTitle());
        desc.setText(Lists.getDesc());
        showdate.setText(Lists.getDate());


        final ImageView img = (ImageView)arg1.findViewById(R.id.image);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                NSqliteHelper db = new NSqliteHelper(context);                // your code here
                // Toast.makeText(context,"on click image id is"+Lists.getId(),Toast.LENGTH_LONG).show();
                int listid = Lists.getId();
                int setstatus = 1;
                int checkstatus = db.getStatus(Lists);
                //   img.setImageResource(R.drawable.redsmall);
                int x = db.updateStatus(listid, setstatus);

                if (checkstatus == 1 && x == 1) {

                    img.setImageResource(R.drawable.greensmall1);

                }

            }

        });

        return arg1;
    }


}
