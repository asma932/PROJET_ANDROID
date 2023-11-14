package com.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.*;

import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.widget.*;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;

import android.database.Cursor;



public class HomeActivity extends AppCompatActivity {

    private HashMap<String, Object> map = new HashMap<>();
    private String date = "";
    private double price = 0;
    private String img = "";
    private String nm = "";

    private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
    private ArrayList<String> li = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> listpanne = new ArrayList<>();


    private GridView gridviewpanne;
    private LinearLayout linear6;

    private HorizontalScrollView hscroll1;


    private ImageView imageview7;
    private ImageView imageview6;
    private LinearLayout linear8;

    private LinearLayout linear14;

    private LinearLayout linear21;
    private LinearLayout linear15;

    private LinearLayout linear22;
    private LinearLayout linear27;

    private GridView gridviewall;
    private LinearLayout linear29;


    private Intent in = new Intent();
    private Intent inn = new Intent();
    private Intent innn = new Intent();
    private AlertDialog.Builder d;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_home);
        initialize(_savedInstanceState);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {

        gridviewpanne = findViewById(R.id.gridviewpanne);
        linear6 = findViewById(R.id.linear6);
        hscroll1 = findViewById(R.id.hscroll1);
        imageview7 = findViewById(R.id.imageview7);
        imageview6 = findViewById(R.id.imageview6);
        linear8 = findViewById(R.id.linear8);

        linear14 = findViewById(R.id.linear14);
        linear21 = findViewById(R.id.linear21);
        linear15 = findViewById(R.id.linear15);
        linear22 = findViewById(R.id.linear22);
        linear27 = findViewById(R.id.linear27);
        gridviewall = findViewById(R.id.gridviewall);
        linear29 = findViewById(R.id.linear29);
        d = new AlertDialog.Builder(this);

        imageview7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                linear6.setVisibility(View.GONE);
                gridviewpanne.setVisibility(View.VISIBLE);
                listpanne.clear();
                final dbHelper mDBHlpr = new dbHelper(HomeActivity.this);
                Cursor cursor = mDBHlpr.readData2();

                if (cursor.moveToFirst()) {
                    do {
                        map = new HashMap<>();
                        map.put("id", cursor.getString(0));
                        map.put("name", cursor.getString(1));
                        map.put("img", cursor.getString(2));
                        map.put("prix", cursor.getString(3));
                        map.put("date", cursor.getString(4));
                        listpanne.add(map);
                    } while (cursor.moveToNext());
                }
                gridviewpanne.setAdapter(new GridviewpanneAdapter(listpanne));
            }
        });

        imageview6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                innn.setClass(getApplicationContext(), ListorderActivity.class);
                startActivity(innn);
            }
        });

        gridviewall.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
                final int _position = _param3;

            }
        });
    }

    private void initializeLogic() {
        mDBHlpr = new dbHelper(this);
        gridviewpanne.setVisibility(View.GONE);
        map = new HashMap<>();
        map.put("name", "Italian pizza");
        map.put("prix", "15 ");
        map.put("date", "2023/11/15");
        map.put("img", "1");
        listmap.add(map);
        map = new HashMap<>();
        map.put("name", "Cheese Burger");
        map.put("prix", "14");
        map.put("date", "2023/1/15");
        map.put("img", "2");
        listmap.add(map);
        map = new HashMap<>();
        map.put("name", "Italian pizza");
        map.put("prix", "15 ");
        map.put("date", "2023/11/15");
        map.put("img", "1");
        listmap.add(map);
        map = new HashMap<>();
        map.put("name", "Cheese Burger");
        map.put("prix", "7");
        map.put("date", "2023/1/15");
        map.put("img", "2");
        listmap.add(map);
        gridviewall.setAdapter(new GridviewallAdapter(listmap));
        /*pDBHlpr = new dbHelperpa(this);*/
        listpanne.clear();

        final dbHelper mDBHlpr = new dbHelper(HomeActivity.this);
        Cursor cursor = mDBHlpr.readData2();

        if (cursor.moveToFirst()) {
            do {
                map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("name", cursor.getString(1));
                map.put("img", cursor.getString(2));
                map.put("prix", cursor.getString(3));
                map.put("date", cursor.getString(4));
                listpanne.add(map);
            } while (cursor.moveToNext());
        }
        gridviewpanne.setAdapter(new GridviewpanneAdapter(listpanne));
        Window w = this.getWindow();

        w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        w.setStatusBarColor(Color.parseColor("#FFFFFF"));
        linear6.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        _SX_CornerRadius_4(linear8, "#dd7755", "#dd7755", 15, 15, 15, 15, 15);
        _SX_CornerRadius_4(linear14, "#dd7755", "#dd7755", 15, 15, 15, 15, 15);
        _SX_CornerRadius_4(linear21, "#dd7755", "#dd7755", 15, 15, 15, 15, 15);
        _removeScollBar(hscroll1);
        _radius_to(linear29, 12, 0, "#EEEEEE");
        _radius_to(linear15, 12, 15, "#FFFFFF");
        _radius_to(linear22, 12, 15, "#FFFFFF");
        _radius_to(linear27, 12, 15, "#FFFFFF");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (gridviewpanne.getVisibility() == View.VISIBLE) {
            gridviewpanne.setVisibility(View.GONE);
            linear6.setVisibility(View.VISIBLE);
        } else {
            d.setTitle("exit");
            d.setMessage("fermer l'application ?");
            d.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface _dialog, int _which) {
                    finishAffinity();
                }
            });
            d.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface _dialog, int _which) {

                }
            });
            d.create().show();
        }
    }


    public void _card_style(final View _view, final double _shadow, final double _rounds, final String _colour) {
        android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
        gd.setColor(Color.parseColor(_colour));
        gd.setCornerRadius((int)_rounds);
        _view.setBackground(gd);
        _view.setElevation((int)_shadow);
    }


    public void _status_bar_color(final String _colour1, final String _colour2) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            Window w = this.getWindow(); w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setNavigationBarColor(Color.parseColor(_colour2));
        }
    }


    public void _roundcorner(final double _a, final double _b, final double _c, final double _d, final String _BGcolor, final View _view) {
        Double tlr = _a;
        Double trr = _b;
        Double blr = _c;
        Double brr = _d;
        android.graphics.drawable.GradientDrawable s = new android.graphics.drawable.GradientDrawable();
        s.setShape(android.graphics.drawable.GradientDrawable.RECTANGLE);
        s.setCornerRadii(new float[] {tlr.floatValue(),tlr.floatValue(), trr.floatValue(),trr.floatValue(), blr.floatValue(),blr.floatValue(), brr.floatValue(),brr.floatValue()});
        s.setColor(Color.parseColor(_BGcolor));
        _view.setBackground(s);
    }


    public void _extra() {
    }
    private dbHelper mDBHlpr;
    private int number;
    private android.database.Cursor mCsr;
    private android.widget.SimpleCursorAdapter mSCA;
    {
    }


    public void _SX_CornerRadius_4(final View _view, final String _color1, final String _color2, final double _str, final double _n1, final double _n2, final double _n3, final double _n4) {
        android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();

        gd.setColor(Color.parseColor(_color1));

        gd.setStroke((int)_str, Color.parseColor(_color2));

        gd.setCornerRadii(new float[]{(int)_n1,(int)_n1,(int)_n2,(int)_n2,(int)_n3,(int)_n3,(int)_n4,(int)_n4});

        _view.setBackground(gd);

        _view.setElevation(4);
    }


    public void _ICC(final ImageView _img, final String _c1, final String _c2) {
        _img.setImageTintList(new android.content.res.ColorStateList(new int[][] {{-android.R.attr.state_pressed},{android.R.attr.state_pressed}},new int[]{Color.parseColor(_c1), Color.parseColor(_c2)}));
    }


    public void _radius_to(final View _view, final double _radius, final double _shadow, final String _color) {
        android.graphics.drawable.GradientDrawable ab = new android.graphics.drawable.GradientDrawable();

        ab.setColor(Color.parseColor(_color));
        ab.setCornerRadius((float) _radius);
        _view.setElevation((float) _shadow);
        _view.setBackground(ab);
    }


    public void _removeScollBar(final View _view) {
        _view.setVerticalScrollBarEnabled(false); _view.setHorizontalScrollBarEnabled(false);
    }

    public class GridviewpanneAdapter extends BaseAdapter {

        ArrayList<HashMap<String, Object>> _data;

        public GridviewpanneAdapter(ArrayList<HashMap<String, Object>> _arr) {
            _data = _arr;
        }

        @Override
        public int getCount() {
            return _data.size();
        }

        @Override
        public HashMap<String, Object> getItem(int _index) {
            return _data.get(_index);
        }

        @Override
        public long getItemId(int _index) {
            return _index;
        }

        @Override
        public View getView(final int _position, View _v, ViewGroup _container) {
            LayoutInflater _inflater = getLayoutInflater();
            View _view = _v;
            if (_view == null) {
                _view = _inflater.inflate(R.layout.costp, null);
            }

            final LinearLayout linear29 = _view.findViewById(R.id.linear29);
            final TextView name = _view.findViewById(R.id.name);
            final ImageView image = _view.findViewById(R.id.image);
            final LinearLayout linear30 = _view.findViewById(R.id.linear30);
            final TextView textview13 = _view.findViewById(R.id.textview13);
            final TextView prix = _view.findViewById(R.id.prix);
            final ImageView imageview8 = _view.findViewById(R.id.imageview8);

            if (_data.get((int)_position).get("img").toString().equals("1")) {
                image.setImageResource(R.drawable.order_3);
            }
            else {
                image.setImageResource(R.drawable.burger);
            }
            name.setText(_data.get((int)_position).get("name").toString());
            prix.setText(_data.get((int)_position).get("prix").toString());
            _radius_to(linear29, 12, 0, "#EEEEEE");
            _radius_to(imageview8, 12, 0, "#dd7755");
            imageview8.setImageResource(R.drawable.delet);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View _view) {
                    inn.setClass(getApplicationContext(), TwoActivity.class);
                    inn.putExtra("name", listpanne.get((int)_position).get("name").toString());
                    inn.putExtra("prix", listpanne.get((int)_position).get("prix").toString());
                    inn.putExtra("data", listpanne.get((int)_position).get("date").toString());
                    inn.putExtra("img", listpanne.get((int)_position).get("img").toString());
                    startActivity(inn);
                }
            });
            imageview8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View _view) {
                    final dbHelper mDBHlpr = new dbHelper(HomeActivity.this);
                    mDBHlpr.deleteData2(_data.get((int)_position).get("id").toString());
                }
            });

            return _view;
        }
    }

    public class GridviewallAdapter extends BaseAdapter {

        ArrayList<HashMap<String, Object>> _data;

        public GridviewallAdapter(ArrayList<HashMap<String, Object>> _arr) {
            _data = _arr;
        }

        @Override
        public int getCount() {
            return _data.size();
        }

        @Override
        public HashMap<String, Object> getItem(int _index) {
            return _data.get(_index);
        }

        @Override
        public long getItemId(int _index) {
            return _index;
        }

        @Override
        public View getView(final int _position, View _v, ViewGroup _container) {
            LayoutInflater _inflater = getLayoutInflater();
            View _view = _v;
            if (_view == null) {
                _view = _inflater.inflate(R.layout.costp, null);
            }

            final LinearLayout linear29 = _view.findViewById(R.id.linear29);
            final TextView name = _view.findViewById(R.id.name);
            final ImageView image = _view.findViewById(R.id.image);
            final LinearLayout linear30 = _view.findViewById(R.id.linear30);
            final TextView textview13 = _view.findViewById(R.id.textview13);
            final TextView prix = _view.findViewById(R.id.prix);
            final ImageView imageview8 = _view.findViewById(R.id.imageview8);

            if (_data.get((int)_position).get("img").toString().equals("1")) {
                image.setImageResource(R.drawable.pizza);
            }
            else {
                image.setImageResource(R.drawable.burger);
            }
            name.setText(_data.get((int)_position).get("name").toString());
            prix.setText(_data.get((int)_position).get("prix").toString());
            _radius_to(linear29, 12, 0, "#EEEEEE");
            _radius_to(imageview8, 12, 0, "#dd7755");
            linear29.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View _view) {
                    try{
                        nm = _data.get((int)_position).get("name").toString();
                        date = _data.get((int)_position).get("date").toString();
                        img = _data.get((int)_position).get("img").toString();
                        price = Double.parseDouble(_data.get((int)_position).get("prix").toString());
                        final dbHelper mDBHlpr = new dbHelper(HomeActivity.this);
                        mDBHlpr.insertData2(nm,price,date,img);
                    }catch(Exception e){

                    }
                }
            });

            return _view;
        }
    }

    @Deprecated
    public void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
    }

    @Deprecated
    public int getLocationX(View _v) {
        int _location[] = new int[2];
        _v.getLocationInWindow(_location);
        return _location[0];
    }

    @Deprecated
    public int getLocationY(View _v) {
        int _location[] = new int[2];
        _v.getLocationInWindow(_location);
        return _location[1];
    }

    @Deprecated
    public int getRandom(int _min, int _max) {
        Random random = new Random();
        return random.nextInt(_max - _min + 1) + _min;
    }

    @Deprecated
    public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
        ArrayList<Double> _result = new ArrayList<Double>();
        SparseBooleanArray _arr = _list.getCheckedItemPositions();
        for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
            if (_arr.valueAt(_iIdx))
                _result.add((double)_arr.keyAt(_iIdx));
        }
        return _result;
    }

}