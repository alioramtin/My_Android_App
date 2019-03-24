package com.ehsan.ehsancontrolco;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.graphics.Color;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.util.Calendar;
        import java.util.Iterator;
        import java.util.LinkedList;
        import java.util.Queue;
        import java.util.Random;

public class TestActivity extends BaseActivity {

    final String filenameSetting = "mysetting";
    final String filenameValues = "TestValues";

    //Queue<String> qe = new LinkedList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void btnChechClickListener(View v){
        // Read file and split
        String settingValues =  readFile(filenameSetting);
        String [] seperated = settingValues.split(":");
        int min = Integer.parseInt(seperated[0]) ;
        int max = Integer.parseInt(seperated[1]) ;
        Random r = new Random();
        int i = r.nextInt( 200 - 20) + 20;
        TextView textViewShowResult = (TextView) findViewById(R.id.textViewShowResult);
        if ((i < min)||(i > max))  {
            textViewShowResult.setTextColor(Color.RED);
        }
        else {
            textViewShowResult.setTextColor(Color.GREEN);
        }
        String test = Integer.toString(i);
        textViewShowResult.setText(test);
        String resultOfTest = " " +  i +" / ";

        writeFile(filenameValues, resultOfTest);
        String resultTests = readFile(filenameValues);
        Toast.makeText(this, "read from file ->" + resultTests,Toast.LENGTH_SHORT ).show();


    }
    public void writeFile(String fileName, String str) {
        try {
            FileOutputStream fOut = openFileOutput(fileName, Context.MODE_APPEND);
            fOut.write(str.getBytes());
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String readFile( String fileName) {
        String res = "";
        try {
            FileInputStream fin = openFileInput(fileName);
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

    public void btnClearFileClickListener(View v){

        alertِDeleteView("Erase all previous amounts?");

    }

    public void btnExitClickListener(View v){

        alertView("Do you want to quit?");
        /*finish();
        moveTaskToBack(true);
        System.exit(0);*/
    }

    public void alertِDeleteView(String message) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Confirmation")
                // .setIcon(R.drawable.ic_launcher)
                .setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialoginterface, int i){
                        try {
                            String str="";
                            FileOutputStream fOut = openFileOutput(filenameValues, Context.MODE_PRIVATE);
                            fOut.write(str.getBytes());
                            fOut.close();
                            //Toast.makeText(this, " اطلاعات مقادیر تست های شما پاک شد ", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }                    }
                }).show();
        Toast.makeText(this, " Previous amounts are erased! ", Toast.LENGTH_SHORT).show();

    }

    public void alertView(String message) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Confirmation")
                // .setIcon(R.drawable.ic_launcher)
                .setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialoginterface, int i){
                        //Toast.makeText(getApplicationContext(), " مرسی از انتخابتون", Toast.LENGTH_LONG) .show();
                        finish();
                        moveTaskToBack(true);
                        System.exit(0);
                    }
                }).show();
    }

}
