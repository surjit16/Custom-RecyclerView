package in.surjitsingh.customrecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Person> arrayList;
    RecyclerView.LayoutManager layoutManager;

    int[] img = {
            R.drawable.ic_arrow_back_black,
            R.drawable.ic_close,
            R.drawable.ic_s,
            R.drawable.ic_p,
            R.drawable.ic_per,
            R.drawable.ic_cellphone,
            R.drawable.ic_settings_black_24dp,
            R.drawable.ic_binary_code,
            R.drawable.ic_launcher_background,
            R.drawable.ic_adb_black_24dp
    };

    MyPreference preference;
    boolean reverse;
    int divider;
    int manager;
    int orientation;
    boolean isStaggered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rc);
        preference = new MyPreference(getApplicationContext());

        reverse = preference.isReverse();
        divider = preference.getDivider();
        manager = preference.getManager();
        orientation = preference.getOrientation();
        switch (manager) {
            case 1:
                layoutManager = new GridLayoutManager(this, 3, orientation == MyConst.ORI_HOR ? GridLayoutManager.HORIZONTAL : GridLayoutManager.VERTICAL, reverse);
                break;
            case 2:
                isStaggered = true;
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, orientation == MyConst.ORI_HOR ? StaggeredGridLayoutManager.HORIZONTAL : StaggeredGridLayoutManager.VERTICAL);
                staggeredGridLayoutManager.setReverseLayout(reverse);
                layoutManager = staggeredGridLayoutManager;
                break;
            default:
                layoutManager = new LinearLayoutManager(this, orientation == MyConst.ORI_HOR ? LinearLayoutManager.HORIZONTAL : LinearLayoutManager.VERTICAL, reverse);
                break;
        }
        recyclerView.setLayoutManager(layoutManager);

        arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(new Person("Hello " + i, "Buy " + i, "Jan 5", "5:0" + i + " PM", img[i]));
        }
        MyAdapter myAdapter = new MyAdapter(this, arrayList, isStaggered);

        recyclerView.setAdapter(myAdapter);

        if (divider != MyConst.DIVIDER_NONE) {
            recyclerView.addItemDecoration(new DividerItemDecoration(this, divider == MyConst.DIVIDER_HOR ? LinearLayoutCompat.HORIZONTAL : LinearLayoutCompat.VERTICAL));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.setting) {
            startActivityForResult(new Intent(this, SettingActivity.class), 100);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
