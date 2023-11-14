package com.example.foodapplication;

import static com.example.foodapplication.DAO.Database.Typereaction.ANGRY;
import static com.example.foodapplication.DAO.Database.Typereaction.NEUTRAL;
import static com.example.foodapplication.DAO.Database.Typereaction.SATISFIED;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ImageView;

import com.example.foodapplication.Adapter.AdapterListener;
import com.example.foodapplication.Adapter.ReclamationAdapter;
import com.example.foodapplication.DAO.Database.Reclamation;
import com.example.foodapplication.DAO.Database.ReclamationDAO;
import com.example.foodapplication.DAO.Database.Status;
import com.example.foodapplication.DAO.Database.Typereaction;
import com.example.foodapplication.Sqlite.Mydatabase;

import java.lang.reflect.Type;
import java.util.List;

public class ListreclamationActivity extends AppCompatActivity implements AdapterListener {
    private ReclamationAdapter adapter;
    private ReclamationDAO reclamationDAO;

    RecyclerView recyclerView;
    private static final int SMS_PERMISSION_REQUEST_CODE = 123; // Updated code


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listreclamation);

        recyclerView = findViewById(R.id.reclamationrecycler);
        adapter = new ReclamationAdapter(this, this);

        // Initialize the database and DAO
        Mydatabase mydatabase = Mydatabase.getInstance(this);
        reclamationDAO = mydatabase.getDao();

        // Fetch and display the list of reclamations
        fetchData();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fetchData() {
        List<Reclamation> reclamations = reclamationDAO.getAll();
        for (Reclamation reclamation : reclamations) {
            adapter.addreclamation(reclamation);
        }
    }

    @Override
    public void OnUpdate(int id, int pos) {
        Reclamation reclamationToUpdate = reclamationDAO.getById(id);

        // Update the status to TREATED
        reclamationToUpdate.setStatus(Status.TREATED);
        reclamationDAO.update(reclamationToUpdate);
        adapter.update(pos);

        // Check and request SMS permission if needed
        requestSMSPermissions();

        // Save the updated status in SharedPreferences
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("status", Status.TREATED.toString());
        editor.apply();

        // Update the empty ImageView based on the new status
        updateEmptyImageView();
    }


    private void updateEmptyImageView() {
        ImageView emptyImageView = findViewById(R.id.empty);

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        Status storedStatus = Status.valueOf(sharedPreferences.getString("status", Status.PENDING.toString()));

        if (storedStatus == Status.TREATED) {
            emptyImageView.setImageResource(R.drawable.checked); // Change to checked.xml
        } else {
            emptyImageView.setImageResource(R.drawable.empty); // Change to empty.xml
        }
    }


    private void sendThankYouSMS(String feedback, Typereaction status) {
        try {  String emulatorPhoneNumber = getEmulatorPhoneNumber();
        String message;

        switch (status) {
            case SATISFIED:
                message = "Thank you for your positive feedback: " + feedback;
                break;
            case ANGRY:
                message = "We apologize for any inconvenience. We are working to address your concerns. Thank you for your patience.";
                break;
            case NEUTRAL:
            default:
                message = "Thank you for your feedback: " + feedback;
                break;
        }


            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(emulatorPhoneNumber, null, message, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private String getEmulatorPhoneNumber() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_SMS, Manifest.permission.READ_PHONE_NUMBERS, Manifest.permission.READ_PHONE_STATE},
                        123);

                return "Permissions not granted";
            }

            return telephonyManager.getLine1Number();
        } else {
            Log.e("SMS", "TelephonyManager is null");
            return null;
        }
    }

    private void requestSMSPermissions() {
        Log.d("SMS", "Checking SMS permission...");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d("SMS", "Permission not granted. Requesting permission...");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    SMS_PERMISSION_REQUEST_CODE);
        } else {
            // Permission already granted, you can directly send the SMS
            Log.d("SMS", "Permission already granted");
            sendThankYouSMS("Sample Feedback", NEUTRAL);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                String phoneNumber = getEmulatorPhoneNumber();
                if (phoneNumber != null) {
                }
        }}
    }
    @Override
    public void OnDelete(int id, int pos) {
reclamationDAO.delete(id);
adapter.remove(pos);
    }

}
