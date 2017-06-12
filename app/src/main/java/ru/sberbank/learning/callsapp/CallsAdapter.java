package ru.sberbank.learning.callsapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Ponomarev on 12.06.2017.
 */

class CallsAdapter extends RecyclerView.Adapter<CallViewHolder> {

    private List<Call> calls;

    public CallsAdapter(List<Call> calls) {
        this.calls = calls;
    }

    @Override
    public CallViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.call_item_layout, parent, false);
        return new CallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CallViewHolder holder, int position) {
        holder.bindView(calls.get(position));
    }

    @Override
    public int getItemCount() {
        return calls.size();
    }
}
