package in.surjitsingh.customrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    Switch reverse_me;
    RadioGroup divider_group, manager_group, orientation_group;
    MyPreference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        preference = new MyPreference(getApplicationContext());
        reverse_me = findViewById(R.id.reverse_me);
        divider_group = findViewById(R.id.divider_group);
        manager_group = findViewById(R.id.manager_group);
        orientation_group = findViewById(R.id.orientation_group);

        reverse_me.setChecked(preference.isReverse());
        ((RadioButton) divider_group.getChildAt(preference.getDivider())).setChecked(true);
        ((RadioButton) manager_group.getChildAt(preference.getManager())).setChecked(true);
        Log.e("ERR", "preference.getOrientation() "+preference.getOrientation());
        ((RadioButton) orientation_group.getChildAt(preference.getOrientation())).setChecked(true);


        reverse_me.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                preference.setIsReverse(isChecked);
            }
        });

        divider_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = group.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked)
                {
                    preference.setDivider(Integer.parseInt((String) checkedRadioButton.getTag()));
                }
            }
        });

        manager_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = group.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked)
                {
                    preference.setManager(Integer.parseInt((String) checkedRadioButton.getTag()));
                }
            }
        });

        orientation_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = group.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked)
                {
                    preference.setOrientation(Integer.parseInt((String) checkedRadioButton.getTag()));
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            setResult(101);
            Toast.makeText(this, "aaaaaaaaaa", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        setResult(101);
        Toast.makeText(this, "bbb", Toast.LENGTH_SHORT).show();

        super.onBackPressed();
    }
}
