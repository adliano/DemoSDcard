/*
   Author : Adriano Alves
   Date   : Mar 23  2015
   AppName : DemoSdCard
   Objective : Demo to show how save files
               to sdcard
 **********************************************
 */

package com.adliano.demosdcard;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class DemoSdCard extends Activity
{
    EditText userIdIn ;
    TextView txView ;
    String idToSave;

    @Override
    //************************ onCreate **************
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_demo_sd_card);

        // set textview by id
        userIdIn = (EditText)findViewById(R.id.editTextUserId);
        txView = (TextView)findViewById(R.id.textView0);
        mkFileSdCard("textdir", "text.txt");
    }
    //******************* mkToast *******************
    public void mkToast(String str)
    {
        Context cont = getApplicationContext();
        Toast t = Toast.makeText(cont, str, Toast.LENGTH_SHORT);
        t.show();
    }
    //********************* saveMyData() ****************
    public void saveMyData(View v)
    {
        try
        {
            FileOutputStream fos = openFileOutput("text.txt", Context.MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(userIdIn.getText().toString()+"\n");
            osw.flush();
            osw.close();

            mkToast("Data Saved: " +userIdIn.getText().toString());


        }catch(Exception e){ e.printStackTrace();}
    }
    //********************* loadMyData() ****************
    public void loadMyData(View v)
    {
        ArrayList<String> arrayList = new ArrayList<>();
        String line="";

        try
        {
            FileInputStream fis = openFileInput("text.txt");
            Scanner sc = new Scanner(fis);
            int i = 1;

            while(sc.hasNext())
            {
               // line +=i+") "+sc.nextLine()+"\n";
                String y = sc.nextLine();
                txView.append(i+" ) "+y+"\n");
                if(i>=10) txView.append(i+") "+y+"\n");
                arrayList.add(y);
                i++;
            }
            fis.close();
            sc.close();

            //############### debug Array ############
            for(String str : arrayList)
            {
                Log.d("ARRAY", str);
            }
            //################# debug line ############
            Log.d("LINE", line);

        }catch(Exception e){e.printStackTrace();}
    }
    //*****************************************************************
    public void mkFileSdCard(String directory, String fileName)
    {
        try
        {
            File sdCard = Environment.getExternalStorageDirectory();
            File dir = new File(sdCard.getAbsolutePath()+"/"+directory);

            if(!dir.exists())  dir.mkdirs();

            File file = new File(dir, fileName);
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write("");
            osw.flush();

        }catch(IOException e)
         {
             Log.e("ERROR", "at mkFileSdCard()", e);
         }
    }
}
//***** END ******
