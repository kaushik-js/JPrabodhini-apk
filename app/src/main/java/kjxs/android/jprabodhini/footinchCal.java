package kjxs.android.jprabodhini;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class footinchCal extends AppCompatActivity {

    EditText win,wft,lin,lft;
    TextView res,res2,res3,res11,res12,res13,pres,pres2,pres3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footinch_cal);
        win = findViewById(R.id.win);
        lin = findViewById(R.id.lin);
        wft = findViewById(R.id.wft);
        lft = findViewById(R.id.lft);
        res = findViewById(R.id.res);
        res2 = findViewById(R.id.res2);
        res2.setEnabled(false);
        res3 = findViewById(R.id.res3);
        pres = findViewById(R.id.pres);
        pres2 = findViewById(R.id.pres2);
        pres2.setEnabled(false);
        pres3 = findViewById(R.id.pres3);
        res11 = findViewById(R.id.res11);
        res12 = findViewById(R.id.res12);
        res12.setEnabled(false);
        res13 = findViewById(R.id.res13);
    }
    public void footcal(View view)  {

        res.setText("");
        res11.setText("");
        pres.setText("");
        int wf ;
        int lf ;
        int wi ;
        int li ;
        try{
             wf = Integer.parseInt(wft.getText().toString());
             lf = Integer.parseInt(lft.getText().toString());
             wi = Integer.parseInt(win.getText().toString());
             li = Integer.parseInt(lin.getText().toString());
        }catch (Exception e) {
            Toast.makeText(this, "PLAESE ENTER ALL VALUES :)", Toast.LENGTH_SHORT).show();
            return;
        }
        int wfi = (wf*12) + wi;
        int lfi = (lf*12) + li;
        int realp = wfi+wfi+lfi+lfi;
        int rr1 = Math.round(realp/12);
        int rr2 = Math.round(realp%12);
        int nwfi = wfi * 16;
        int f1 = Math.round(nwfi/9);
        int r3 = f1 % 16;
        int f2 = Math.round(f1/16);
        int r1 = Math.round(f2/12);
        int r2 = f2 % 12;
        res.append("Width : \n"+r1+" Feet - "+r2+" Inch ");
        if(r3>0)
        {
            res2.setText(""+r3);
            res3.setText("16");
        }
        int nlfi = lfi * 16;
        int f11 = Math.round(nlfi/9);
        int r33 = f11 % 16;
        int f22 = Math.round(f11/16);
        int r11 = Math.round(f22/12);
        int r22 = f22 % 12;
        res11.append("Length : \n"+r11+" Feet - "+r22+" Inch ");
        if(r33>0)
        {
            res12.setText(""+r33);
            res13.setText("16");
        }
        Float s1 = Float.parseFloat(wf+"."+wi);
        Float s2 = Float.parseFloat(lf+"."+li);
        Float pm =  s1+s1+s2+s2;
        float rest = Float.parseFloat(String.format("%.2f",pm));
        String rt = (""+rest).replace(".",",");
        String[] kk = rt.split(",");
        pres.setText("Perimeter : \n"+kk[0]+" Feet - "+kk[1]+" Inch");
    }
}
