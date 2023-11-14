package com.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.*;
import android.content.Intent;
import android.graphics.*;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.*;
import android.view.View;
import android.widget.*;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.*;
import java.util.HashMap;
public class TwoActivity extends AppCompatActivity {

    private HashMap<String, Object> map = new HashMap<>();
    private String nam = "";
    private String tele = "";
    private double num = 0;
    private double price = 0;
    private String date = "";
    private String address = "";
    private double quantit = 0;
    private String product = "";
    int gt;
    private String quantite = "";
    private String id = "";
    private LinearLayout linear3;
    private TextView name;
    private ImageView imageview1;
    private LinearLayout buttonSave;
    private TextView data;
    private EditText cname;
    private EditText teleph;
    private EditText adress;
    private EditText qentite;
    private TextView prix;
    private TextView textview4;

    private Intent i = new Intent();

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_two);
        initialize(_savedInstanceState);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {
        linear3 = findViewById(R.id.linear3);
        name = findViewById(R.id.name);
        imageview1 = findViewById(R.id.imageview1);
        buttonSave = findViewById(R.id.buttonSave);
        data = findViewById(R.id.data);
        cname = findViewById(R.id.cname);
        teleph = findViewById(R.id.teleph);
        adress = findViewById(R.id.adress);
        qentite = findViewById(R.id.qentite);
        prix = findViewById(R.id.prix);
        textview4 = findViewById(R.id.textview4);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (cname.getText().toString().trim().equals("")) {
                    showMessage(getApplicationContext(), "Enter a Name");
                }
                else {
                    if (teleph.getText().toString().trim().equals("")) {
                        showMessage(getApplicationContext(), "Enter téléphone Num ");
                    }
                    else {
                        if (adress.getText().toString().trim().equals("")) {
                            showMessage(getApplicationContext(), "Enter an address");
                        }
                        else {
                            if (qentite.getText().toString().trim().equals("")) {
                                showMessage(getApplicationContext(), "Enter Quantité ");
                            }
                            else {
                                num = 0;
                                price = Double.parseDouble(getIntent().getStringExtra("prix").trim());
                                quantite = qentite.getText().toString().trim();
                                gt=Integer.parseInt(quantite);
                                nam = cname.getText().toString();
                                tele = teleph.getText().toString();
                                date = data.getText().toString();
                                address = adress.getText().toString();
                                product = name.getText().toString();
                                try{
                                    if (getIntent().hasExtra("id")) {
                                        id = getIntent().getStringExtra("id");
                                        mDBHlpr.updateData(id, nam, gt, price, address,tele);
                                    }
                                    else {
                                        mDBHlpr.insertData(nam, 0, price, date, address, tele, gt, product);
                                    }
                                    cname.setText("");
                                    teleph.setText("");
                                    adress.setText("");
                                    qentite.setText("");
                                    finish();
                                }catch(Exception e){

                                }
                            }
                        }
                    }
                }
            }
        });

        qentite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {

            }
        });

        qentite.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
                final String _charSeq = _param1.toString();
                if (!qentite.getText().toString().equals("")) {
                    prix.setText(String.valueOf(Double.parseDouble(qentite.getText().toString()) * Double.parseDouble(getIntent().getStringExtra("prix"))));
                }
                else {

                }
            }

            @Override
            public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

            }

            @Override
            public void afterTextChanged(Editable _param1) {

            }
        });
    }

    private void initializeLogic() {
        if (getIntent().hasExtra("id")) {
            imageview1.setVisibility(View.GONE);
            try{
                qentite.setText(getIntent().getStringExtra("quantite"));
                adress.setText(getIntent().getStringExtra("adress"));
            }catch(Exception e){

            }
            try{
                teleph.setText(getIntent().getStringExtra("tele"));
                name.setText(getIntent().getStringExtra("product"));
            }catch(Exception e){

            }
            try{
                data.setText(getIntent().getStringExtra("date"));
                cname.setText(getIntent().getStringExtra("cname"));
                textview4.setText("Update order ");
            }catch(Exception e){

            }
        }
        else {
            if (getIntent().getStringExtra("img").equals("1")) {
                imageview1.setImageResource(R.drawable.order_2);
            }
            else {
                imageview1.setImageResource(R.drawable.burger);
            }
            name.setText(getIntent().getStringExtra("name"));
            data.setText(getIntent().getStringExtra("data"));
            prix.setText(getIntent().getStringExtra("prix"));
        }
        _card_style(linear3, 2, 8, "#ffffff");
        _rippleRoundStroke(buttonSave, "#ffffff", "#e0e0e0", 15, 4, "#e0e0e0");
        mDBHlpr = new dbHelper(this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public void _card_style(final View _view, final double _shadow, final double _rounds, final String _colour) {
        android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
        gd.setColor(Color.parseColor(_colour));
        gd.setCornerRadius((int)_rounds);
        _view.setBackground(gd);
        _view.setElevation((int)_shadow);
    }


    public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
        android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
        GG.setColor(Color.parseColor(_focus));
        GG.setCornerRadius((float)_round);
        GG.setStroke((int) _stroke,
                Color.parseColor("#" + _strokeclr.replace("#", "")));
        android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
        _view.setBackground(RE);
    }


    public void _extra() {
    }
    private dbHelper mDBHlpr;
    private int number;
    private android.database.Cursor mCsr;
    private android.widget.SimpleCursorAdapter mSCA;
    {
    }


    @Deprecated
    public void showMessage(Context applicationContext, String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
    }



}