package brainitall.pixelly.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import brainitall.pixelly.R;

public class LevelActivity extends AppCompatActivity {

    private Button mNiveauTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        // Branchements Vue / Controller
        mNiveauTest = (Button) findViewById(R.id.activity_level_btn_niveauTest);

        mNiveauTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playActivity = new Intent(LevelActivity.this, PlayActivity.class);
                startActivity(playActivity);
            }
        });
    }
}
