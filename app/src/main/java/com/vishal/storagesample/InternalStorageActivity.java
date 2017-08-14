package com.vishal.storagesample;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class InternalStorageActivity extends AppCompatActivity {

    EditText editTextFileName, editTextFileContent;
    TextView textViewMsg;
    boolean externalStorage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        editTextFileName = (EditText) findViewById(R.id.editTextFileName);
        editTextFileContent = (EditText) findViewById(R.id.fileContent);

        textViewMsg = (TextView) findViewById(R.id.message);

        externalStorage = getIntent().getBooleanExtra("external-storage", false);
    }



    public void performTask(View view)
    {
        // getFilesDir() --> /data/data/com.vishal.storagesample/files

        String fileName = editTextFileName.getText().toString().trim();
        String fileContent = editTextFileContent.getText().toString();

        File ff;

        File folder;

        Environment.getExternalStorageDirectory();
        getFilesDir();
        if(externalStorage)
            folder = Environment.getExternalStorageDirectory();
        else
            folder = getFilesDir();



        textViewMsg.setText("");

        switch (view.getId()) {

            case R.id.clear_btn:
                editTextFileName.setText("");
                editTextFileContent.setText("");
                textViewMsg.setText("");
                break;

            case R.id.save_btn:

                if(fileName == null || fileName.isEmpty()) {
                    textViewMsg.setText("Specify a file name.");
                }
                else {
                    try {

                        if(!externalStorage) {
                            FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
                            byte[] b = fileContent.getBytes();
                            fos.write(b);
                            fos.close();
                        }
                        else {
                            ff = new File(folder, fileName);
                            FileWriter fw = new FileWriter(ff);
                            fw.write(fileContent);
                            fw.flush();
                            fw.close();

                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                break;

            case R.id.open_btn:
                if(fileName == null || fileName.isEmpty()) {
                    textViewMsg.setText("Specify a file name.");
                }
                else {

                    File f1 = new File(folder,fileName);
                    if(f1.exists()) {
                        try {
                            if(!externalStorage)
                            {
                                FileInputStream fis = openFileInput(fileName);
                                byte[] b1 = new byte[fis.available()];
                                fis.read(b1);
                                fileContent = new String(b1);
                                editTextFileContent.setText(fileContent);
                            }
                            else {
                                FileReader fr = new FileReader(f1);
                                BufferedReader br = new BufferedReader(fr);

                                StringBuilder sb = new StringBuilder();
                                String line = br.readLine();

                                while(line != null) {
                                    sb.append(line).append("\n");
                                    line = br.readLine();
                                }

                                editTextFileContent.setText(sb.toString());
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        textViewMsg.setText("File does not exist");
                    }
                }
                break;

            case R.id.delete_btn:
                if(fileName == null || fileName.isEmpty()) {
                    textViewMsg.setText("Specify a file name.");
                }

                File f1 = new File(folder, fileName);
                if(f1.exists()) {
                    if(!f1.delete()) {
                        textViewMsg.setText("Failed to delete the file.");

                    }
                }
                else {
                    textViewMsg.setText("File does not exit.");

                }
                break;
        }

    }
}
