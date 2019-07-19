package com.syafrizal.submission2.notifications;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.syafrizal.submission2.Constant;
import com.syafrizal.submission2.R;
import com.syafrizal.submission2.helper.MovieApiService;
import com.syafrizal.submission2.models.Movie;
import com.syafrizal.submission2.models.MovieResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;
import static com.syafrizal.submission2.Constant.CHANNEL_ID;
import static com.syafrizal.submission2.Constant.CHANNEL_NAME;
import static com.syafrizal.submission2.Constant.NOTIFICATION_ID;
import static com.syafrizal.submission2.Constant.NOTIFICATION_ID_;

public class MovieReleaseReceiver extends BroadcastReceiver {
    public List<Movie> movies = new ArrayList<>();
    private static Retrofit retrofit = null;
    private Context mContext;

    public MovieReleaseReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        this.mContext = context;
        getUpcomingMovie();
    }

    private void getUpcomingMovie(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        Call<MovieResponse> call = movieApiService.getUpComingMovie(Constant.API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                movies = response.body().getResults();
                int index = new Random().nextInt(movies.size());
                Movie movie = movies.get(index);
                String title = movie.getTitle() + " Just Come Out !";
                String message = movie.getOverview();
                sendNotification(mContext, title, message, NOTIFICATION_ID_);

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable throwable) {
                Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show();
                Log.e(TAG, throwable.toString());
            }
        });
    }


    private void sendNotification(Context context, String title, String desc, int id) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(context, DailyReceiver.class);
        PendingIntent pendingIntent = TaskStackBuilder.create(context)
                .addNextIntent(intent)
                .getPendingIntent(NOTIFICATION_ID,PendingIntent.FLAG_UPDATE_CURRENT);
        Uri uriTone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_movies_black)
                .setContentTitle(title)
                .setContentText(desc)
                .setContentIntent(pendingIntent)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(uriTone);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

            builder.setChannelId(CHANNEL_ID);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }


        Notification notification = builder.build();




        if (notificationManager != null) {
            notificationManager.notify(id, notification);
        }

    }


    public void setAlarm(Context context) {
        cancelAlarm(context);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context,MovieReleaseReceiver.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE,0);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,NOTIFICATION_ID,intent,0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }

    public void cancelAlarm(Context context){
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context,DailyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,NOTIFICATION_ID,intent,0);
        alarmManager.cancel(pendingIntent);

    }
}
