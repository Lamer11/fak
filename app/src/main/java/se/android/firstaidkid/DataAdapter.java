package se.android.firstaidkid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<se.android.firstaidkid.ViewHolder> {


    ArrayList<String> messages;

    LayoutInflater inflater;

    public DataAdapter(Context context, ArrayList<String> messages) {
        System.out.println("Наш контекст выглядит так:" + context);
        this.messages = messages;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public se.android.firstaidkid.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.viewholder, parent, false);

        return new se.android.firstaidkid.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull se.android.firstaidkid.ViewHolder holder, int position) {

        String msg = messages.get(position);
        holder.message.setText(msg);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

}
