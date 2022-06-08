package kjxs.android.jprabodhini;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

public class ViewPdf extends AppCompatActivity {
    EditText limit;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);
        limit = findViewById(R.id.limit);
        txt = findViewById(R.id.txt);
    }
    public void pdfcal(View view)
    {
        txt.setText("");
        int l;
        try
        {
            l = Integer.parseInt(limit.getText().toString());
        }catch (Exception e){
            Toast.makeText(this, "ENTER LIMIT", Toast.LENGTH_SHORT).show();
            return;
        }
        txt.append(rp("\n---",'-',5)+""+rp("----",'-',5)+rp("----",'-',5)+""+rp("----",'-',5)+""+rp("----",'-',5)+""+rp("---------",'-',10)+""+rp("----",'-',5)+""+rp("------",'-',5));
        txt.append(rp("\nNo.",' ',5)+""+rp(" Aaya",' ',5)+rp(" Vyay",' ',5)+""+rp(" Yoni",' ',5)+""+rp(" Vaar",' ',5)+""+rp(" Nakshatra",' ',10)+""+rp(" Ayul",' ',5)+""+rp(" Aamasa",' ',5));
        txt.append(rp("\n---",'-',5)+""+rp("----",'-',5)+rp("----",'-',5)+""+rp("----",'-',5)+""+rp("----",'-',5)+""+rp("---------",'-',10)+""+rp("----",'-',5)+""+rp("------",'-',5));
        for(int i=1;i<=l;i++)
        {
            int a=i;
            int aaya =  (a * 8) % 12;
            int vyaay =  (a * 9) % 5;
            int yoni =  (a * 3) % 8;
            int var =  (a * 9) % 7;
            int nak =  (a * 8) % 27;
            int ayul =  (a * 27) % 50;
            int amasa =  (a * 4) % 9;
            txt.append(rp("\n"+a,' ',5)+""+rp("  "+aaya,' ',5)+rp("  "+vyaay,' ',5)+""+rp("  "+yoni,' ',5)+""+rp("  "+var,' ',5)+""+rp("     "+nak,' ',10)+""+rp("  "+ayul,' ',5)+""+rp("   "+amasa,' ',5)+"\n");
        }
        txt.append(rp("\n---",'-',5)+""+rp("----",'-',5)+rp("----",'-',5)+""+rp("----",'-',5)+""+rp("----",'-',5)+""+rp("---------",'-',10)+""+rp("----",'-',5)+""+rp("------",'-',5));
    }
    public static String rp(String input, char ch, int L)
    {
        String result = String.format("%" + (-L) + "s", input).replace(' ', ch);
        return result;
    }
    public static String lp(String input, char ch, int L)
    {
        String result = String.format("%" + L + "s", input).replace(' ', ch);
        return result;
    }
    public void save(View view) {
    }
    public void onRequestPermissionsResult(int requestCode, String[] permission, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permission, grantResults);
        switch (requestCode) {
            case 3:
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    createFile();
                }
                else{
                    Toast.makeText(this, "Not Granted", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    public void createFile()
    {
        String fnm=""+limit.getText().toString()+"RECORDS";
        String dest = Environment.getExternalStorageDirectory().getPath()+"/"+fnm+".pdf";
        try
        {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(dest));
            Font fnt = FontFactory.getFont(FontFactory.COURIER);
            doc.open();
            doc.add(new Paragraph(txt.getText().toString(), fnt));
            doc.close();
            Toast.makeText(this, "SAVED :)", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){Toast.makeText(this, " "+e, Toast.LENGTH_SHORT).show();}
    }
}
