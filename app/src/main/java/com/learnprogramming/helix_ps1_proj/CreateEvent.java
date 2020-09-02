package com.learnprogramming.helix_ps1_proj;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class CreateEvent extends AppCompatActivity {

    private static final String TAG = "CreateEvent";

    private EditText etStartTime;
    private EditText etDate;
    private Button btnImg;
    private Button createBtn;
    private EditText etName;
    private EditText etDesc;
    private Spinner genreList;
    private ImageView ivTest;

    private String imagePath = "";

    private static final int PICK_IMAGE = 1;
    private ArrayList<String> genres;

    private static String AUTH_TOKEN = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        SharedPreferences saveAuthToken = getSharedPreferences("saveAuthToken", MODE_PRIVATE);
        AUTH_TOKEN = saveAuthToken.getString("authToken", "default");

        etName = findViewById(R.id.et_event_name);
        etDesc = findViewById(R.id.et_description);
        ivTest = findViewById(R.id.iv_test);

//        Implementation of time picker for Select time
        etStartTime = findViewById(R.id.et_start_time);

        etStartTime.setOnClickListener(v -> {

            Calendar currentTime = Calendar.getInstance();
            int hour = currentTime.get(Calendar.HOUR_OF_DAY);
            int minute = currentTime.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(CreateEvent.this, AlertDialog.THEME_HOLO_DARK,
                    (view, hourOfDay, minute1) -> {
                        String hrs = String.valueOf(hourOfDay);
                        String mns = String.valueOf(minute1);
                        if (hourOfDay < 10) {
                            hrs = "0" + hrs;
                        }
                        if (minute1 < 10) {
                            mns = "0" + mns;
                        }
                        etStartTime.setText(hrs.concat(":").concat(mns));
                    }, hour, minute, true);
            timePickerDialog.setTitle("Set starting Time");
            timePickerDialog.show();
        });

//        Implementation of date picker for Select Date
        etDate = findViewById(R.id.et_date);

        etDate.setOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();
            int date = calendar.get(Calendar.DATE);
            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(CreateEvent.this, AlertDialog.THEME_HOLO_DARK,
                    (view, year1, month1, date1) -> {
                        String dateString = (date1 < 10) ? "0".concat(String.valueOf(date1)) : String.valueOf(date1);
                        String monthString = (month1 + 1 < 10) ? "0".concat(String.valueOf(month1 + 1)) : String.valueOf(month1 + 1);
                        String yearString = (date1 < 10) ? "0".concat(String.valueOf(year1)) : String.valueOf(year1);

                        etDate.setText(dateString.concat("/").concat(monthString).concat("/").concat(yearString));
                    }, year, month, date);
            datePickerDialog.setTitle("Set Date");
            datePickerDialog.show();
        });

//        Implement choose file button
        btnImg = findViewById(R.id.btn_pic);

        btnImg.setOnClickListener(v -> {

            Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
            getIntent.setType("image/*");

            Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                pickIntent.setType("image/*");

            Intent chooseIntent = Intent.createChooser(getIntent, "Choose an app");
            chooseIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

            startActivityForResult(chooseIntent, PICK_IMAGE);
        });

        genreList = findViewById(R.id.genre_list);
        genres = new ArrayList<>();
        genres.add("Select Genre");
        genres.add("Rock");
        genres.add("Pop");
        genres.add("Hip Hop");

        ArrayAdapter<String> genreAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, genres);
        genreList.setAdapter(genreAdapter);

        createBtn = findViewById(R.id.bt_create);
        createBtn.setOnClickListener(v -> makeEvent());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Toast.makeText(CreateEvent.this, "Image URL : " + data.getData(), Toast.LENGTH_SHORT).show();
            imagePath = data.getData().toString();
            Picasso.get().load(imagePath).into(ivTest);
        } else {
            Toast.makeText(CreateEvent.this, "No Image was selected", Toast.LENGTH_SHORT).show();
        }
    }

    protected void makeEvent() {
        String Name = etName.getText().toString();
        String Desc = etDesc.getText().toString();
        String Genre = "";
        if(!genreList.getSelectedItem().toString().equalsIgnoreCase("Select Genre"))
            Genre = genreList.getSelectedItem().toString();
        String Date = etDate.getText().toString();
        String StartTime = etStartTime.getText().toString();
        String path = imagePath;

//        uploading local image url to api connect to firebase or some other cloud storage to put image online

        Retrofit createEventBuilder = new Retrofit.Builder()
                .baseUrl("https://helix-ps-backend.herokuapp.com/homepage/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CreateEventAPI createEventAPI = createEventBuilder.create(CreateEventAPI.class);

        Call<CreateEventResponse> createEventResponseCall = createEventAPI.createEvent(AUTH_TOKEN ,Desc, Name, StartTime, Genre, path, Date);

        createEventResponseCall.enqueue(new Callback<CreateEventResponse>() {
            @Override
            public void onResponse(Call<CreateEventResponse> call, Response<CreateEventResponse> response) {
                CreateEventResponse createEventResponse;

                createEventResponse = response.body();

                if(response.code() == 200) {
                    Toast.makeText(CreateEvent.this, createEventResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    CreateEvent.this.finish();
                } else if (response.code() == 400) {
                    Toast.makeText(CreateEvent.this, "Please Enter all fields", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreateEventResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: create event post failure");
                t.printStackTrace();
            }
        });
    }
}