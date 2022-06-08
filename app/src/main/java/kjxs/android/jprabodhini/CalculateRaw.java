package kjxs.android.jprabodhini;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class CalculateRaw extends AppCompatActivity {

    EditText val,val1;
    Spinner sp;
    int[][] grp = {{1,10,19},{2,11,20},{3,12,21},{4,13,22},{5,14,23},{6,15,24},{7,16,25},{8,17,26},{9,18,27},};

    int[][] ngp = {{3,7},{4,8},{5,9},{6,1},
            {7,2},
            {8,3},
            {9,4},
            {1,5},
            {2,6}
    };
    String[] nax={"SELECT NAKSHATRA",
            "Ashwini",
            "Bharani",
            "Kruttika",
            "Rohini",
            "Mrigashirsha",
            "Ardra",
            "Punarvasu",
            "Pushya",
            "Ashlesha",
            "Magha",
            "Purva Phalguni",
            "Uttara Phalguni",
            "Hasta",
            "Chitra",
            "Svati",
            "Vishakha",
            "Anuradha",
            "Jyeshtha",
            "Mula",
            "Purva Ashadha",
            "Uttara Ashadha",
            "Shravana",
            "Dhanishta",
            "Shatabhisha",
            "Purva Bhadrapada",
            "Uttara Bhadrapada",
            "Revati"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_raw);
        val = findViewById(R.id.vala);
        val1 = findViewById(R.id.vala1);
        sp = findViewById(R.id.spn);
        ArrayAdapter ap = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,nax);
        sp.setAdapter(ap);
    }
    public void calc(View view)
    {
        String ms="";
        if(val.getText().toString().equals("") || val1.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter All Fields :) ", Toast.LENGTH_SHORT).show();
            return;
        }
        int k=0;
        int a = Integer.parseInt(val.getText().toString());
        int aaya =  (a * 8) % 12;
        if(aaya==0)
            aaya = 12;
        ((TextView)findViewById(R.id.aaya)).setText(""+aaya);

        int vyaay =  (a * 9) % 10;
        if(vyaay==0)
            vyaay=10;
        ((TextView)findViewById(R.id.vyaay)).setText(""+vyaay);

        if(aaya<vyaay){
            ((TextView)findViewById(R.id.aayaP)).setText("Not Auspicious");
            ((TextView)findViewById(R.id.vyaayp)).setText("Not Auspicious");
            ((TextView)findViewById(R.id.aayaP)).setTextColor(Color.RED);
            ((TextView)findViewById(R.id.vyaayp)).setTextColor(Color.RED);
            k++;
        }
        else {
            ((TextView)findViewById(R.id.aayaP)).setText("Auspicious    ");          
            ((TextView)findViewById(R.id.vyaayp)).setText("Auspicious    ");          
            ((TextView)findViewById(R.id.aayaP)).setTextColor(Color.GREEN);
            ((TextView)findViewById(R.id.vyaayp)).setTextColor(Color.GREEN);
        }
        int yoni =  (a * 3) % 8;
        if(yoni==0)
            yoni=8;
        ((TextView)findViewById(R.id.yoni)).setText(""+yoni);
        if(yoni%2==0){
            ((TextView)findViewById(R.id.yonip)).setText("Not Auspicious");
            ((TextView)findViewById(R.id.yonip)).setTextColor(Color.RED);
            k++;
        }
        else{
            ((TextView)findViewById(R.id.yonip)).setText("Auspicious    ");          
            ((TextView)findViewById(R.id.yonip)).setTextColor(Color.GREEN);
        }
        int var =  (a * 9) % 7;
        if(var==0)
            var=7;
        ((TextView)findViewById(R.id.var)).setText(""+var);
        if(var==3||var==7){
            ((TextView)findViewById(R.id.varp)).setText("Not Auspicious");
            ((TextView)findViewById(R.id.varp)).setTextColor(Color.RED);
            k++;
        }
        else{
            ((TextView)findViewById(R.id.varp)).setText("Auspicious    ");          
            ((TextView)findViewById(R.id.varp)).setTextColor(Color.GREEN);
        }
        int ayul =  (a * 27) % 100;
        if(ayul==0)
            ayul=100;
        ((TextView)findViewById(R.id.ayul)).setText(""+ayul);
        if(ayul<25){
            ((TextView)findViewById(R.id.ayulp)).setText("Not Auspicious");
            ((TextView)findViewById(R.id.ayulp)).setTextColor(Color.RED);
            k++;
        }
        else{
            ((TextView)findViewById(R.id.ayulp)).setText("Auspicious    ");
            ((TextView)findViewById(R.id.ayulp)).setTextColor(Color.GREEN);
        }
        int amasa =  (a * 4) % 9;
        if(amasa==0)
            amasa=9;
        ((TextView)findViewById(R.id.aamasa)).setText(""+amasa);
        if(amasa==1||amasa==6||amasa==8){
            ((TextView)findViewById(R.id.aamasap)).setText("Not Auspicious");
            ((TextView)findViewById(R.id.aamasap)).setTextColor(Color.RED);
        }
        else{
            ((TextView)findViewById(R.id.aamasap)).setText("Auspicious    ");
            ((TextView)findViewById(R.id.aamasap)).setTextColor(Color.GREEN);
        }
        int nak =  (a * 8) % 27;
        if(nak==0)
            nak=27;
        ((TextView)findViewById(R.id.nakshatra)).setText(""+nak);
        int unak = sp.getSelectedItemPosition();
        int mod = unak%9;
        if(mod==0)
            mod=9;
        mod=mod-1;
        int i1 = ngp[mod][0];
        i1 = i1 - 1;
        int i2 = ngp[mod][1];
        i2 = i2 - 1;
        boolean g1 = (nak!=grp[i1][0] && nak!=grp[i1][1] && nak!=grp[i1][2]);
        boolean g2 = (nak!=grp[i2][0] && nak!=grp[i2][1] && nak!=grp[i2][2]);
        if(g1==g2){
            ((TextView)findViewById(R.id.nakshatrap)).setText("Auspicious");
            ((TextView)findViewById(R.id.nakshatrap)).setTextColor(Color.GREEN);
        }
        else{
            ((TextView)findViewById(R.id.nakshatrap)).setText("Not Auspicious");
            ((TextView)findViewById(R.id.nakshatrap)).setTextColor(Color.RED);
            k++;
        }
        if(k==0){
            ((TextView)findViewById(R.id.status)).setText("STATUS : AUSPICIOUS");
            ((TextView)findViewById(R.id.status)).setTextColor(Color.GREEN);
        }
        else{
            ((TextView)findViewById(R.id.status)).setText("STATUS : NOT AUSPICIOUS");
            ((TextView)findViewById(R.id.status)).setTextColor(Color.RED);
        }
    }
    public void savepdf(View view)
    {
        String fnm = val1.getText().toString();
        String dest = Environment.getExternalStorageDirectory().getPath()+"/"+fnm+".pdf";
        String txt="";
        txt="Name : "+fnm;
        txt = txt + "\nNakshatra : "+sp.getSelectedItem().toString();
        txt = txt + "\nAayadi : "+val.getText().toString();
        txt = txt + "\n-------------------------------";
        txt = txt + "\nAaya : "+((TextView)findViewById(R.id.aaya)).getText()+" - "+((TextView)findViewById(R.id.aayaP)).getText();
        txt = txt + "\nVyaay : "+((TextView)findViewById(R.id.vyaay)).getText()+" - "+((TextView)findViewById(R.id.vyaayp)).getText();
        txt = txt + "\nYoni : "+((TextView)findViewById(R.id.yoni)).getText()+" - "+((TextView)findViewById(R.id.yonip)).getText();
        txt = txt + "\nVaar : "+((TextView)findViewById(R.id.var)).getText()+" - "+((TextView)findViewById(R.id.varp)).getText();
        txt = txt + "\nNakshatra : "+((TextView)findViewById(R.id.nakshatra)).getText()+" - "+((TextView)findViewById(R.id.nakshatrap)).getText();
        txt = txt + "\nAyul : "+((TextView)findViewById(R.id.ayul)).getText()+" - "+((TextView)findViewById(R.id.ayulp)).getText();
        txt = txt + "\nAamasa : "+((TextView)findViewById(R.id.aamasa)).getText()+" - "+((TextView)findViewById(R.id.aamasap)).getText();
        txt = txt + "\n-------------------------------";
        txt = txt + "\n"+((TextView)findViewById(R.id.status)).getText().toString();
        txt = txt + "\n-------------------------------";
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(dest));
            Font fnt = FontFactory.getFont(FontFactory.COURIER,28);
            doc.open();
            doc.add(new Paragraph(txt, fnt));
            doc.close();
            Toast.makeText(this, "SAVED", Toast.LENGTH_SHORT).show();
        }catch (Exception e){Toast.makeText(this, " "+e, Toast.LENGTH_SHORT).show();}
    }
}
