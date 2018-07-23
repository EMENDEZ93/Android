package app.andriod.em.androidem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (TextView) findViewById(R.id.textViewSecondaryMain);

        //capturar los datos del intent
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String MESSAGE = bundle.getString("MESSAGE");
            Toast.makeText(SecondActivity.this, MESSAGE, Toast.LENGTH_LONG ).show();
            textView.setText(MESSAGE);
        } else {
            Toast.makeText(SecondActivity.this, "It's empty", Toast.LENGTH_LONG );
        }

    }
}
