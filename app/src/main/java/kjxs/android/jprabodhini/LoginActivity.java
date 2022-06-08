package kjxs.android.jprabodhini;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;
import java.util.concurrent.TimeUnit;
import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;
public class LoginActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView phn;
    String rph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        phn = findViewById(R.id.phno);
        phn.setEnabled(false);
        if (ActivityCompat.checkSelfPermission(this, READ_SMS) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, READ_PHONE_NUMBERS) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            TelephonyManager tMgr = (TelephonyManager)   this.getSystemService(Context.TELEPHONY_SERVICE);
            String mPhoneNumber = tMgr.getLine1Number();
            phn.setText(mPhoneNumber);
            return;
        }
        else {
            requestPermission();
        }
    }
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{READ_SMS, READ_PHONE_NUMBERS, READ_PHONE_STATE}, 100);
        }
    }
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 100:
                TelephonyManager tMgr = (TelephonyManager)  this.getSystemService(Context.TELEPHONY_SERVICE);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) !=
                        PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED  && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                String mPhoneNumber = tMgr.getLine1Number();
                phn.setText(mPhoneNumber);
                break;
        }
    }
    public void sendsms(View view) {
        rph = phn.getText().toString();
        rph = rph.replace("+","");
        if(rph.length()>10 || rph.length()==12){
            rph = rph.substring(2);
        }
        final String nph = rph;
        db.collection("phonelogin").document(nph.trim()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String data = task.getResult().getString("phone");
                if(data!=null) {
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }
                else
                    Toast.makeText(LoginActivity.this, "Number not registered yet :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
