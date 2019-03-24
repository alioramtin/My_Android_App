package com.ehsan.ehsancontrolco;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.SharedPreferences;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.Toast;

        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;


        import static com.ehsan.ehsancontrolco.R.id.spinnerMax;
        import static com.ehsan.ehsancontrolco.R.id.spinnerMin;

public class SettingActivity extends BaseActivity {

    Button btnSaveAndShow, btnExit;
    final String filename = "mysetting";
    final String minSpinnerVal = "20";
    final String maxSpinnerVal = "80";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        String res =  readFile();
        try {
            String [] seperated = res .split(":");
            if ((!seperated[0].isEmpty())&& (!seperated[1].isEmpty()) ) {
                Spinner  minSelected  = (Spinner)findViewById(R.id.spinnerMin);
                minSelected.setSelection( ((ArrayAdapter<String>) minSelected.getAdapter()).getPosition(seperated[0]));

                Spinner maxSelected = (Spinner)findViewById(R.id.spinnerMax);
                maxSelected.setSelection( ((ArrayAdapter<String>) maxSelected.getAdapter()).getPosition(seperated[1]));
            }
            else {
                Spinner  minSelected  = (Spinner)findViewById(R.id.spinnerMin);
                minSelected.setSelection( ((ArrayAdapter<String>) minSelected.getAdapter()).getPosition(minSpinnerVal));

                Spinner maxSelected = (Spinner)findViewById(R.id.spinnerMax);
                maxSelected.setSelection( ((ArrayAdapter<String>) maxSelected.getAdapter()).getPosition(maxSpinnerVal));
                String saveValues = minSpinnerVal + ":" + maxSpinnerVal ;
                writeFile(saveValues);
            }

        }catch(Exception e){}


    }

    public void saveBtnClickListener(View v){
        Spinner spinnerMinSelected  = (Spinner)findViewById(R.id.spinnerMin);
        String testMin = spinnerMinSelected.getSelectedItem().toString();

        final Spinner spinnerMaxSelected = (Spinner)findViewById(R.id.spinnerMax);
        String testMax = String.valueOf(spinnerMaxSelected.getSelectedItem());

        String saveValues = testMin + ":" + testMax ;
        writeFile(saveValues);
        Toast.makeText(this, " کمترین " + testMin + " بیشترین " +testMax , Toast.LENGTH_SHORT ).show();

    }

    public void writeFile(String str) {
        //FileOutputStream fOut = openFileOutput(filename, Context.MODE_APPEND);
        try {
            FileOutputStream fOut = openFileOutput(filename, Context.MODE_PRIVATE);
            fOut.write(str.getBytes());
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile() {
        String res = "";
        try {
            FileInputStream fin = openFileInput(filename);
            int c;
            String temp = "";
            while ((c = fin.read()) != -1) {
                temp = temp + Character.toString((char) c);
            }
            //Toast.makeText(this, "  مقادیر انتخابی شما" + temp, Toast.LENGTH_SHORT).show();
            res = temp;
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }


    public void exitBtnClickListener(View v){

        alertView("آیا میخواهید خارج شوید؟");
        /*finish();
        moveTaskToBack(true);
        System.exit(0);*/
    }

    public void alertView(String message) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("تایید")
                // .setIcon(R.drawable.ic_launcher)
                .setMessage(message)
                .setPositiveButton("آری", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialoginterface, int i){
                        //Toast.makeText(getApplicationContext(), " مرسی از انتخابتون", Toast.LENGTH_LONG) .show();
                        finish();
                        moveTaskToBack(true);
                        System.exit(0);
                    }
                }).show();
    }
}
