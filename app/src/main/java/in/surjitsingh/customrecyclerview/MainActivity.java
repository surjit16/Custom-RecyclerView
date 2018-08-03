package in.surjitsingh.customrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Person> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rc);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);

        arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(new Person("Hello " + i, "Buy " + i, "Jan 5", "5:0" + i + " PM", i));
        }
        MyAdapter myAdapter = new MyAdapter(this, arrayList);

        recyclerView.setAdapter(myAdapter);

    }
}
