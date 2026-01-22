package com.example.taxi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editPhone, editName, editSurname;
    Button buttonRegistration;
    SharedPreferences prefs;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d(TAG, "onCreate");

        // считываем введенные данные
        editPhone = findViewById(R.id.Phone);
        editName = findViewById(R.id.Name);
        editSurname = findViewById(R.id.Surname);
        buttonRegistration = findViewById(R.id.ButtonReg);

        prefs = getSharedPreferences("TaxiUser", MODE_PRIVATE);

        // Проверяем, есть ли сохранённые данные
        String savedPhone = prefs.getString("phone", null);
        String savedName = prefs.getString("name", null);
        String savedSurname = prefs.getString("surname", null);

        if (savedPhone != null && savedName != null && savedSurname != null) {
            // автозагрузка
            editPhone.setText(savedPhone);
            editName.setText(savedName);
            editSurname.setText(savedSurname);

            // Меняем текст кнопки
            buttonRegistration.setText("Log in");
        }

        buttonRegistration.setOnClickListener(v -> {
            String phone = editPhone.getText().toString();
            String name = editName.getText().toString();
            String surname = editSurname.getText().toString();

            if (phone.isEmpty() || name.isEmpty() || surname.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }

            // Сохраняем регистрационные данные
            prefs.edit()
                    .putString("phone", phone)
                    .putString("name", name)
                    .putString("surname", surname)
                    .apply();

            // Переходим во 2 Activity
            Intent intent = new Intent(MainActivity.this, SecondPage.class);
            intent.putExtra("phone", phone);
            intent.putExtra("name", name);
            intent.putExtra("surname", surname);
            startActivity(intent);
        });
    }

    @Override protected void onStart() { super.onStart(); Log.d(TAG, "onStart"); }
    @Override protected void onResume() { super.onResume(); Log.d(TAG, "onResume"); }
    @Override protected void onPause() { super.onPause(); Log.d(TAG, "onPause"); }
    @Override protected void onStop() { super.onStop(); Log.d(TAG, "onStop"); }
    @Override protected void onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy"); }
}
