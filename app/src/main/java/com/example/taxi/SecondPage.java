package com.example.taxi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondPage extends AppCompatActivity {

    private static final String TAG = "SecondPage";
    private static final int REQUEST_PATH = 100;

    TextView tvNameSurname, tvPhone, tvOrderTaxi;
    Button btnSetPath, btnCallTaxi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.second_page);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d(TAG, "onCreate");

        // Инициализация элементов
        tvNameSurname = findViewById(R.id.WrittenSNP);
        tvPhone = findViewById(R.id.WrittenPhone);
        tvOrderTaxi = findViewById(R.id.OrderTaxi);
        btnSetPath = findViewById(R.id.ButtonSetPath);
        btnCallTaxi = findViewById(R.id.buttonCallTaxi);

        // Получение данных из MainActivity
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String surname = intent.getStringExtra("surname");
        String phone = intent.getStringExtra("phone");

        tvNameSurname.setText(name + " " + surname);
        tvPhone.setText(phone);

        // Кнопка Set Path → ThirdPage
        btnSetPath.setOnClickListener(v -> {
            Intent pathIntent = new Intent(SecondPage.this, ThirdPage.class);
            startActivityForResult(pathIntent, REQUEST_PATH);
        });

        // Кнопка Call Taxi
        btnCallTaxi.setOnClickListener(v -> {
            Toast.makeText(SecondPage.this, "Taxi has been sent!", Toast.LENGTH_SHORT).show();
        });
    }

    // Получение результата от ThirdPage
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PATH && resultCode == RESULT_OK && data != null) {
            String streetFrom = data.getStringExtra("streetFrom");
            String houseFrom = data.getStringExtra("houseFrom");
            String flatFrom = data.getStringExtra("flatFrom");
            String streetTo = data.getStringExtra("streetTo");
            String houseTo = data.getStringExtra("houseTo");
            String flatTo = data.getStringExtra("flatTo");

            String route = "Taxi will arrive at " +
                    streetFrom + ", " + houseFrom + ", " + flatFrom +
                    " in 5 minutes and take you to " +
                    streetTo + ", " + houseTo + ", " + flatTo +
                    ". If you agree click Call Taxi.";

            tvOrderTaxi.setText(route);
            btnCallTaxi.setEnabled(true);
            btnCallTaxi.setBackgroundTintList(getResources().getColorStateList(R.color.gray_dark));
        }
    }

    // Жизненный цикл
    @Override protected void onStart() { super.onStart(); Log.d(TAG, "onStart"); }
    @Override protected void onResume() { super.onResume(); Log.d(TAG, "onResume"); }
    @Override protected void onPause() { super.onPause(); Log.d(TAG, "onPause"); }
    @Override protected void onStop() { super.onStop(); Log.d(TAG, "onStop"); }
    @Override protected void onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy"); }
}
