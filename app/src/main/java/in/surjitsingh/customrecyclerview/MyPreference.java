package in.surjitsingh.customrecyclerview;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreference {
    SharedPreferences preferences;

    MyPreference(Context context) {
        preferences = context.getSharedPreferences("setting", Context.MODE_PRIVATE);
    }

    boolean isReverse() {
        return preferences.getBoolean("reverse_me", false);
    }

    void setIsReverse(boolean isReverse) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("reverse_me", isReverse);
        editor.apply();
    }

    void setDivider(int tag) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("divider_group", tag);
        editor.apply();
    }

    int getDivider() {
        return preferences.getInt("divider_group", MyConst.DIVIDER_NONE);
    }

    void setManager(int tag) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("manager_group", tag);
        editor.apply();
    }

    int getManager() {
        return preferences.getInt("manager_group", MyConst.MANAGER_LINEAR);
    }

    void setOrientation(int tag) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("orientation_group", tag);
        editor.apply();
    }

    int getOrientation() {
        return preferences.getInt("orientation_group", MyConst.ORI_VER);
    }

}
