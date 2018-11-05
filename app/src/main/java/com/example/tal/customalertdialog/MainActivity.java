package com.example.tal.customalertdialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    //items in activity_main.xml
    TextView tv1, tv2, tv3, tv4;
    ListView lv1;

    LinearLayout dialog;
    //items in dialog.xml
    RadioGroup dialog_type;
    EditText dialog_et_x1, dialog_et_d;

    //global variables
    int type;
    int x1 = 0, d = 0;
    String[] listString = new String[20];
    int[] list = new int[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //items in activity_main.xml
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        lv1 = findViewById(R.id.lv1);
        lv1.setOnItemClickListener(this);
    }

    public void OCbtn1(View view) {
        dialog = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog, null);
        dialog_type = findViewById(R.id.dialog_type);
        dialog_et_x1 = findViewById(R.id.dialog_et_x1);
        dialog_et_d = findViewById(R.id.dialog_et_d);

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setView(dialog);
        adb.setCancelable(false);
        adb.setTitle("Enter Data");
        adb.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                type = dialog_type.getCheckedRadioButtonId();
                x1 = Integer.parseInt(dialog_et_x1.getText().toString());
                d = Integer.parseInt(dialog_et_d.getText().toString());
                fillListView(type, x1, d);
                tv1.setText("X1= " + x1);
                tv2.setText("d= " + d);
                dialog.dismiss();
            }
        });
        adb.setNegativeButton("Reset", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                type = R.id.ari;
                x1 = 0;
                d = 0;
                fillListView(type, x1, d);
                tv1.setText("X1= " + x1);
                tv2.setText("d= " + d);
                dialog.dismiss();
            }
        });
        adb.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        adb.show();
    }

    public void fillListView(int type, int x1, int d) {
        list[1] = x1;
        if (type == R.id.ari)
            for (int i = 1; i < 20; i++)
                list[i] = list[i - 1] + d;
        else if (type == R.id.geo)
            for (int i = 1; i < 20; i++)
                list[i] = list[i - 1] * d;
        else
            Toast.makeText(this, "Please choose progression type.", Toast.LENGTH_LONG);
        for (int i = 0; i < list.length; i++)
            listString[i] = String.valueOf(list[i]);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listString);
        lv1.setAdapter(adp);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int Sn = 0;
        for (int i = 0; i < position + 1; i++)
            Sn = Sn + list[i];
        tv3.setText("n= " + (position + 1));
        tv4.setText("Sn= " + Sn);
    }
}