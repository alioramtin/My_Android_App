package com.ehsan.ehsancontrolco;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import android.graphics.Color;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.Toast;


        import com.jjoe64.graphview.GraphView;
        import com.jjoe64.graphview.helper.StaticLabelsFormatter;
        import com.jjoe64.graphview.series.DataPoint;
        import com.jjoe64.graphview.series.LineGraphSeries;


        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;

public class ChartActivity extends BaseActivity {

    LineGraphSeries<DataPoint> series;
    final String filenameValues = "TestValues";
    /*final String filenameSetting = "mysetting";
    String settingValues =  readFile(filenameSetting);
    String [] settingSeperated = settingValues.split(":");
    final int min = Integer.parseInt(settingSeperated[0]) ;
    final int max = Integer.parseInt(settingSeperated[1]) ;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        String res = readFile(filenameValues);
        String [] seperated = res.split("/");
        //Toast.makeText(this, "  number of items are: " + seperated.length, Toast.LENGTH_SHORT).show();
        // Toast.makeText(this, "   items are: " + res  , Toast.LENGTH_SHORT).show();
        int x,y;
        GraphView graph = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        // y = Integer.parseInt(seperated[i]);


        for (int i = 0; i <seperated.length-1; i ++)
        {
            try {
                series.appendData(new DataPoint(i, Integer.parseInt(seperated[i].replaceAll("[\\D]",""))), true, seperated.length-1);

            }catch(NumberFormatException nfe){}
        }
        graph.addSeries(series);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
    }


    //read file
    public String readFile(String fileName) {
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

}