package com.syafrizal.submission2.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.syafrizal.submission2.R;
import com.syafrizal.submission2.notifications.DailyReceiver;
import com.syafrizal.submission2.notifications.MovieReleaseReceiver;

import static com.syafrizal.submission2.Constant.DAILY_REMINDER_FIELD;
import static com.syafrizal.submission2.Constant.DAILY_REMINDER_KEY;
import static com.syafrizal.submission2.Constant.PREF_KEY;
import static com.syafrizal.submission2.Constant.RELEASE_REMINDER_FIELD;

public class SettingActivity extends AppCompatActivity {

    TextView languageSetting;
    Switch dailySwitch,newRelease;
    SharedPreferences sharedPreferences;
    DailyReceiver dailyReceiver;
    MovieReleaseReceiver movieReleaseReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        sharedPreferences = this.getSharedPreferences(PREF_KEY,MODE_PRIVATE);
        dailyReceiver = new DailyReceiver();
        movieReleaseReceiver = new MovieReleaseReceiver();
        initView();
    }


    private void initView(){
        languageSetting = findViewById(R.id.lang_setting);
        languageSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(mIntent);
            }
        });



        dailySwitch = findViewById(R.id.daily_reminder);

        dailySwitch.setChecked(sharedPreferences.getBoolean(DAILY_REMINDER_FIELD,true));
        dailySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        sharedPreferences.edit().putBoolean(DAILY_REMINDER_FIELD,true).commit();
                        dailyReminderOn();
                    }else{
                        sharedPreferences.edit().putBoolean(DAILY_REMINDER_FIELD,false).commit();
                        dailyReminderOff();
                    }
            }
        });

        newRelease = findViewById(R.id.release_Reminder);

        newRelease.setChecked(sharedPreferences.getBoolean(RELEASE_REMINDER_FIELD,true));
        newRelease.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    sharedPreferences.edit().putBoolean(RELEASE_REMINDER_FIELD,true).commit();
                    newReleaseOn();

                }else {
                    sharedPreferences.edit().putBoolean(RELEASE_REMINDER_FIELD,true).commit();
                    newReleaseOff();
                }
            }
        });

    }

    private void dailyReminderOn() {
        dailyReceiver.setAlarm(SettingActivity.this);
    }


    private void dailyReminderOff() {
        dailyReceiver.cancelAlarm(SettingActivity.this);
    }

    private void newReleaseOn(){ movieReleaseReceiver.setAlarm(SettingActivity.this);}

    private void newReleaseOff(){ movieReleaseReceiver.cancelAlarm(SettingActivity.this);}



}
