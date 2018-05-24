package brainitall.pixelly.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import brainitall.pixelly.R;

public class CreditActivity extends AppCompatActivity {

    private TextView mTexteCredit;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        mTexteCredit = (TextView) findViewById(R.id.textView_credit);
    }

}
