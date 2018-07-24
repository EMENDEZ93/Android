package app.andriod.em.androidem;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ViewAnimator;

public class ThirdActivity extends AppCompatActivity {

    private EditText editTextThirdActivity;
    private EditText editTextThirdActivity2;
    private ImageButton imageButtonThirdActivity;

    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        editTextThirdActivity = (EditText) findViewById(R.id.editTextThirdActivity);
        editTextThirdActivity2 = (EditText) findViewById(R.id.editTextThirdActivity2);
        imageButtonThirdActivity = (ImageButton) findViewById(R.id.imageButtonThirdActivity);


        imageButtonThirdActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editText = editTextThirdActivity.getText().toString();
                if (editText != null) {
                    //comprobar version actual del android
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                    } else {
                        olderVersion(editText);
                    }
                }
            }

            private void olderVersion(String editText) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + editText));
                if (checkPermission(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentCall);
                } else {
                    Toast.makeText(ThirdActivity.this, "You declined the access", Toast.LENGTH_LONG);
                }
            }

        });

    }

    private boolean checkPermission(String permission) {
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //estamos en el caso del telefono
        switch (requestCode) {
            case PHONE_CALL_CODE:
                String permission = permissions[0];
                int result = grantResults[0];

                if (permission.equals(Manifest.permission.CALL_PHONE)) {
                    //comprobar si ha sido aceptado o denegado la peticion del permiso
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        //concedio permiso
                        String phoneNumber = editTextThirdActivity.getText().toString();
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("Tel: " + editTextThirdActivity));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(intentCall);
                    } else {
                        Toast.makeText(ThirdActivity.this, "You declined the access", Toast.LENGTH_LONG);
                    }
                }

                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }
}
