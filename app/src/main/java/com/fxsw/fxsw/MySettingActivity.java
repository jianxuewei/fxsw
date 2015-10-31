package com.fxsw.fxsw;


import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.prefs.Preferences;


public class MySettingActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_name,etPassword;
    private CheckBox rememberMe,rememberPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("d_setting","oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_setting);
        findViewById(R.id.button_submit).setOnClickListener(this);
        findViewById(R.id.button_show_settings).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences preferences= (SharedPreferences) getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        et_name= (EditText) findViewById(R.id.et_input_name);
        etPassword= (EditText) findViewById(R.id.et_input_password);
        rememberMe= (CheckBox) findViewById(R.id.checkbox_remember_me);
        rememberPassword= (CheckBox) findViewById(R.id.checkbox_remember_password);

        switch (v.getId()){
            case R.id.button_submit:
                Log.d("d_setting", "submit");
                editor.putString("name",et_name.getText().toString());
                editor.putString("password",etPassword.getText().toString());
                editor.putBoolean("isRememberMe",rememberMe.isChecked());
                editor.putBoolean("isRememberPassword",rememberPassword.isChecked());
                editor.commit();
                break;
            case R.id.button_show_settings:
                Log.d("d_setting","show");
                String name,password;
                name=preferences.getString("name","your name");
                password=preferences.getString("password","your password");
                Toast.makeText(this,preferences.getString("name","your name"),Toast.LENGTH_SHORT).show();
                et_name.setText(name);
                etPassword.setText(password);
                rememberMe.setChecked(preferences.getBoolean("isRememberMe", false));
                rememberPassword.setChecked(preferences.getBoolean("isRememberPassword",false));
                break;
            default:
                break;
        }
    }
}
