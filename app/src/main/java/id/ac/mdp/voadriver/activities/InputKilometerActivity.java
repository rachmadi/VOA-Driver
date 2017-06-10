package id.ac.mdp.voadriver.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import id.ac.mdp.voadriver.R;

public class InputKilometerActivity extends AppCompatActivity {

    Button btnSubmitKilometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_kilometer);
        btnSubmitKilometer = (Button) findViewById(R.id.btnSubmitKilometer);

        btnSubmitKilometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InputKilometerActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
