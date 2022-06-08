package kjxs.android.jprabodhini;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permission, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permission, grantResults);
        switch (requestCode) {
            case 3:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    public void cal(View view) {
        startActivity(new Intent(this, CalculateRaw.class));
    }
    public void pdf(View view) {
        startActivity(new Intent(this, ViewPdf.class));
    }
    public void ftinch(View view) {
        startActivity(new Intent(this, footinchCal.class));
    }
    public void savelogin(View view) {
        TelephonyManager tMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String mPhoneNumber = tMgr.getLine1Number();
        if(mPhoneNumber.equals("9823079929"))
            startActivity(new Intent(this,newLogin.class));
        else {
            if (mPhoneNumber.equals("9156123921"))
                startActivity(new Intent(this, newLogin.class));
        }
    }
}
