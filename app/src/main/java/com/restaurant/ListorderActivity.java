package com.restaurant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.*;
import android.os.Bundle;
import android.util.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import android.database.Cursor;


public class ListorderActivity extends AppCompatActivity {

    private HashMap<String, Object> map = new HashMap<>();

    private ArrayList<HashMap<String, Object>> list = new ArrayList<>();

    private ListView listView;

    private AlertDialog.Builder d;
    private Intent inte = new Intent();

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_listorder);
        initialize(_savedInstanceState);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {
        listView = findViewById(R.id.listView);
        d = new AlertDialog.Builder(this);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
                final int _position = _param3;

                return true;
            }
        });
    }

    private void initializeLogic() {
        list.clear();
        dbHelper mDBHelper = new dbHelper(this);
        Cursor cursor = mDBHelper.readData();
        if (cursor.moveToFirst()) {
            do {
                map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("Name", cursor.getString(1));
                map.put("Number", cursor.getString(2));
                map.put("Address", cursor.getString(5));
                map.put("Telephone", cursor.getString(6));
                map.put("Quantity", cursor.getString(7));
                map.put("Price", cursor.getString(3));
                map.put("Date", cursor.getString(4));
                map.put("Product", cursor.getString(8));
                list.add(map);
            } while (cursor.moveToNext());
        }
        listView.setAdapter(new ListViewAdapter(list));
        ((BaseAdapter)listView.getAdapter()).notifyDataSetChanged();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public void _radius_to(final View _view, final double _radius, final double _shadow, final String _color) {
        android.graphics.drawable.GradientDrawable ab = new android.graphics.drawable.GradientDrawable();

        ab.setColor(Color.parseColor(_color));
        ab.setCornerRadius((float) _radius);
        _view.setElevation((float) _shadow);
        _view.setBackground(ab);
    }

    public class ListViewAdapter extends BaseAdapter {

        ArrayList<HashMap<String, Object>> _data;

        public ListViewAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
                _view = _inflater.inflate(R.layout.orde, null);
            }

            final LinearLayout linear11 = _view.findViewById(R.id.linear11);

            final TextView date = _view.findViewById(R.id.date);
            final ImageView edit = _view.findViewById(R.id.edit);
            final ImageView delet = _view.findViewById(R.id.delet);
            final TextView quantite = _view.findViewById(R.id.quantite);
            final TextView textview7 = _view.findViewById(R.id.textview7);
            final TextView name = _view.findViewById(R.id.name);
            final TextView product = _view.findViewById(R.id.product);
            final TextView address = _view.findViewById(R.id.address);
            final TextView price = _view.findViewById(R.id.price);
            final TextView teleph = _view.findViewById(R.id.teleph);

            name.setText(_data.get((int)_position).get("Name").toString());
            product.setText(_data.get((int)_position).get("Product").toString());
            address.setText(_data.get((int)_position).get("Address").toString());
            price.setText(_data.get((int)_position).get("Price").toString());
            teleph.setText(_data.get((int)_position).get("Telephone").toString());
            date.setText(_data.get((int)_position).get("Date").toString());
            quantite.setText(_data.get((int)_position).get("Quantity").toString());
            final dbHelper mDBHelper = new dbHelper(ListorderActivity.this);
            delet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View _view) {
                    d.setTitle("supprimer");
                    d.setMessage("supprimer this order?");
                    d.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface _dialog, int _which) {
                            mDBHelper.deleteData(_data.get((int)_position).get("id").toString());
                            _data.remove((int)(_position));
                            finish();
                        }
                    });
                    d.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface _dialog, int _which) {

                        }
                    });
                    d.create().show();
                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View _view) {
                    inte.setClass(getApplicationContext(), TwoActivity.class);
                    inte.putExtra("id", _data.get((int)_position).get("id").toString());
                    inte.putExtra("quantite", _data.get((int)_position).get("Quantity").toString());
                    inte.putExtra("cname", _data.get((int)_position).get("Name").toString());
                    inte.putExtra("product", _data.get((int)_position).get("Product").toString());
                    inte.putExtra("adress", _data.get((int)_position).get("Address").toString());
                    inte.putExtra("tele", _data.get((int)_position).get("Telephone").toString());
                    inte.putExtra("date", _data.get((int)_position).get("Date").toString());
                    inte.putExtra("prix", _data.get((int)_position).get("Price").toString());
                    startActivity(inte);
                    finish();
                }
            });
            _radius_to(linear11, 12, 0, "#EEEEEE");

            return _view;
        }
    }

    @Deprecated
    public void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
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