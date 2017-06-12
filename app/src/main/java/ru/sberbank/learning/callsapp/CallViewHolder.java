package ru.sberbank.learning.callsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Тичер on 10.06.2017.
 */
public class CallViewHolder extends RecyclerView.ViewHolder {

    private static final Locale USES_LOCALE = Locale.ENGLISH;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm", USES_LOCALE);
    private final Context context;

    public CallViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
    }

    public void bindView(Call call) {
        ((TextView) itemView.findViewById(R.id.date)).setText(DATE_FORMAT.format(new Date(call.date)));
        ((TextView) itemView.findViewById(R.id.number)).setText(call.number);
        ((TextView) itemView.findViewById(R.id.duration)).setText(String.format(USES_LOCALE, context.getString(R.string.duration_format),
                call.duration / 3600,
                (call.duration % 3600) / 60,
                call.duration % 60)
        );
        ((ImageView) itemView.findViewById(R.id.type)).setImageDrawable(context.getResources().getDrawable(getCallDrawable(call.type)));
    }

    private int getCallDrawable(Call.Type type) {

        switch (type) {
            case INCOMING:
                return R.drawable.ic_call_in;
            case OUTGOING:
                return R.drawable.ic_call_out;
            case MISSED:
                return R.drawable.ic_call_miss;

            default:
                return android.R.drawable.stat_notify_error;
        }
    }
}
