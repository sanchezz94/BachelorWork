package com.example.amonakhov.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.igorpresnyakov.myapplication.R;

public class infoScreen extends AppCompatActivity {

    public TextView txtName;
    public TextView txtDiag;
    public TextView summ;
    public ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_screen);
        txtName = (TextView) this.findViewById(R.id.name);
        txtDiag = (TextView) this.findViewById(R.id.diag);
        summ = (TextView) this.findViewById(R.id.summ);
        im = (ImageView) this.findViewById(R.id.photo);
        Bundle b = getIntent().getExtras();

        im.setImageBitmap((Bitmap) b.getParcelable("bitmap"));
        txtDiag.setText(b.getString("diag"));
        txtName.setText(b.getString("name"));
        summ.setText(b.getString("summ"));
    }

    public void smsSend(View v) {
        String number = "7715";
        EditText message=(EditText)findViewById(R.id.message);
        String messageText= "НИЖЕГОРОДСКИЙ " + message.getText().toString();
        SmsManager.getDefault().sendTextMessage(number,null,messageText.toString(),null,null);
        Intent intent = new Intent(infoScreen.this,screen_confirm.class);
        startActivity(intent);
    }
}

