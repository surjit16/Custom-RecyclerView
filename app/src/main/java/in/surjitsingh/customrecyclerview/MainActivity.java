package in.surjitsingh.customrecyclerview;

import android.content.ContentValues;
import android.content.Intent;
import android.icu.util.LocaleData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import in.surjitsingh.customrecyclerview.data.MyDBHelper;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Person> arrayList;
    RecyclerView.LayoutManager layoutManager;
    ListView listView;
    Button button;
    RequestQueue requestQueue;
    ArrayList<String> nameList;
    ArrayList<Integer> mobList;

    String url = "https://api.myjson.com/bins/aknt0";

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
    MyAdapter myAdapter;
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
        arrayList = new ArrayList<>();
        findViewById(R.id.addMore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
                final View view1 = LayoutInflater.from(MainActivity.this).inflate(R.layout.alert_item, null);
                dialog.setView(view1);
                dialog.setTitle("Add New Member");
                dialog.setCancelable(false);
                dialog.show();


                view1.findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = ((TextView)view1.findViewById(R.id.name)).getText().toString();
                        String desc = ((TextView)view1.findViewById(R.id.desc)).getText().toString();
                        ContentValues values = new ContentValues();
                        values.put("name", name);
                        values.put("description", desc);
                        Date d = new Date();
                        CharSequence date  = DateFormat.format("MMMM d, yyyy ", d.getTime());
                        CharSequence time  = DateFormat.format("HH:MM ", d.getTime());
                        values.put("time", time.toString());
                        values.put("date", date.toString());
                        values.put("uid", R.drawable.ic_cellphone);
                        MyDBHelper myDBHelper = new MyDBHelper(MainActivity.this);
                        myDBHelper.insertNew(values);
                        dialog.dismiss();
                        Person  p = new Person(name, desc, date.toString(), time.toString(), R.drawable.ic_cellphone);
                        arrayList.add(p);
                        myAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        MyDBHelper myDBHelper = new MyDBHelper(this);
        arrayList = myDBHelper.getData();

        myAdapter = new MyAdapter(this, arrayList, isStaggered);
        requestQueue = Volley.newRequestQueue(this);

        /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("student");
                    String[] name = new String[array.length()];
                    int[] mob = new int[array.length()];
                    Person person;
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        person = new Person(jsonObject.getString("name"), jsonObject.getInt("mob")+"", "", "", img[i]);
                        arrayList.add(person);
                    }
                    myAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, " Eoorrr ", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Erorrrrrrrrr", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(request);*/

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
