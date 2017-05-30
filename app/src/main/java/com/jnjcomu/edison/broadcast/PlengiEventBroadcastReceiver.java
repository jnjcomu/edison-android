package com.jnjcomu.edison.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.jnjcomu.edison.api.APIBuilder;
import com.jnjcomu.edison.model.Region;
import com.jnjcomu.edison.storage.UserStorage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlengiEventBroadcastReceiver extends BroadcastReceiver {
    public static final String RECEIVER_ID = "com.jnjcomu.edison.cloud.response";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!intent.getAction().equals(RECEIVER_ID)) return;

        noticeRegion(context, intent, true);
    }

    private void noticeRegion(Context context, Intent intent, boolean retry) {
        UserStorage userStorage = UserStorage.getInstance(context);

        APIBuilder.getAPI().noticeRegion(
                userStorage.getTicket(),
                new Region(
                        Integer.parseInt(intent.getStringExtra("place.id")),
                        intent.getStringExtra("place.name")
                )
        ).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                try {
                    APIBuilder.getAPI()
                            .fetchTicket(userStorage.getTicket())
                            .subscribe(ticket -> {
                                UserStorage.getInstance(context).saveUserTicket(ticket);
                            });

                    if (retry) noticeRegion(context, intent, false);
                } catch (Exception ignored) {
                }
            }
        });
    }
}
