package com.example.oneuiapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

// أدابتور لعرض قائمة مكونة من 200 عنصر نصي في RecyclerView
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private static final int ITEM_COUNT = 200;

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // نفّذ inflate لعنصر القائمة من ملف XML المخصص
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        // ضبط نص العنصر (يبدأ من 1 إلى 200)
        holder.textView.setText("Item " + (position + 1));
    }

    @Override
    public int getItemCount() {
        return ITEM_COUNT;
    }

    // فئة داخلية لعنصر القائمة
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text);
        }
    }
}
