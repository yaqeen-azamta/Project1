package com.example.project1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity2 extends AppCompatActivity {
    private Button StartB;
    public static final String Email = "email";
    public static final String PASS = "PASS";
    public static final String FLAG = "FLAG";
    private boolean flag = false;
    private EditText editEmail;
    private EditText edtPassword;
    private CheckBox chk;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.loginactivity);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.loginMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupViews();
        setupSharedPrefs();
        checkPrefs();

        StartB = findViewById(R.id.StartB);
        StartB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLoginOnClick(view);
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }

    private void checkPrefs() {
        flag = prefs.getBoolean(FLAG, false);
        if (flag) {
            String name = prefs.getString(Email, "");
            String password = prefs.getString(PASS, "");
            editEmail.setText(name);
            edtPassword.setText(password);
            chk.setChecked(true);
        }
    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    private void setupViews() {
        editEmail = findViewById(R.id.editEmail);
        edtPassword = findViewById(R.id.edtPassword);
        chk = findViewById(R.id.chk);
    }

    public void btnLoginOnClick(View view) {
        String name = editEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if (chk.isChecked()) {
            if (!flag) {
                editor.putString(Email, name);
                editor.putString(PASS, password);
                editor.putBoolean(FLAG, true);
                editor.apply();
            }
        }
    }
}
