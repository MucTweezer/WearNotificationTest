package me.muctweezer.wearnotificationtest;

import android.app.Activity;
import android.app.Notification;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                final Button myButton = (Button)stub.findViewById(R.id.button);
                myButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        runSecondNotification();
                    }
                });
            }
        });
    }

    private void runNotification() {
        Toast.makeText(this, R.string.button_response, Toast.LENGTH_LONG).show();
    }

    private void runSecondNotification() {
        //Intent testIntent = new Intent(this, Something.class);
        //PendingIntent testPendingIntent = PendingIntent.getActivity(this, 0, testIntent, 0);

        NotificationCompat.Action action = new NotificationCompat.Action.Builder(android.R.drawable.ic_menu_call, "My Label", null).build();

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_menu_call)
                .setContentTitle("My Title")
                .setContentText("My Content")
                .extend(new NotificationCompat.WearableExtender().addAction(action))
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, notification);
    }
}
