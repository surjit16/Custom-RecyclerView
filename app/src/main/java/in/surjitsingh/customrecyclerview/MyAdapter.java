package in.surjitsingh.customrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Person> list;
    private Context context;
    private boolean isStaggered;

    MyAdapter(Context context, ArrayList<Person> list, boolean isStaggered) {
        this.context = context;
        this.list = list;
        this.isStaggered = isStaggered;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v;
        if (isStaggered)
            v = inflater.inflate(R.layout.staggered_list_item, viewGroup, false);
        else v = inflater.inflate(R.layout.list_item, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        if (!isStaggered) {
            myViewHolder.desc.setText(list.get(i).getDesc());
            myViewHolder.date.setText(list.get(i).getDate());
            myViewHolder.time.setText(list.get(i).getTime());
        }
        myViewHolder.uid.setImageResource(list.get(i).getUid());
        myViewHolder.name.setText(list.get(i).getName());
        myViewHolder.itemView.setTag(list.get(i).getName());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Name : " + v.getTag() + "\n", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, desc, date, time;
        ImageView uid;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            uid = itemView.findViewById(R.id.uid);
            if (!isStaggered) {
                desc = itemView.findViewById(R.id.desc);
                date = itemView.findViewById(R.id.date);
                time = itemView.findViewById(R.id.time);
            }
        }

    }
}
