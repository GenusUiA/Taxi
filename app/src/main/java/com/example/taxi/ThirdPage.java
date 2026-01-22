package com.example.taxi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThirdPage extends AppCompatActivity {

    private static final String TAG = "ThirdPage";

    EditText etStreetFrom, etHouseFrom, etFlatFrom;
    EditText etStreetTo, etHouseTo, etFlatTo;
    Button btnOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.third_page);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d(TAG, "onCreate");

        // Инициализация EditText
        etStreetFrom = findViewById(R.id.StreetFrom);
        etHouseFrom = findViewById(R.id.HouseFrom);
        etFlatFrom = findViewById(R.id.FlatFrom);

        etStreetTo = findViewById(R.id.StreetTo);
        etHouseTo = findViewById(R.id.HouseTo);
        etFlatTo = findViewById(R.id.FlatTo);

        btnOK = findViewById(R.id.ButtonOK);

        // Кнопка OK — возврат данных в SecondPage
        btnOK.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("streetFrom", etStreetFrom.getText().toString());
            resultIntent.putExtra("houseFrom", etHouseFrom.getText().toString());
            resultIntent.putExtra("flatFrom", etFlatFrom.getText().toString());

            resultIntent.putExtra("streetTo", etStreetTo.getText().toString());
            resultIntent.putExtra("houseTo", etHouseTo.getText().toString());
            resultIntent.putExtra("flatTo", etFlatTo.getText().toString());

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    // Жизненный цикл
    @Override protected void onStart() { super.onStart(); Log.d(TAG, "onStart"); }
    @Override protected void onResume() { super.onResume(); Log.d(TAG, "onResume"); }
    @Override protected void onPause() { super.onPause(); Log.d(TAG, "onPause"); }
    @Override protected void onStop() { super.onStop(); Log.d(TAG, "onStop"); }
    @Override protected void onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy"); }
}
