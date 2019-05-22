package com.kirito.planmer.calendar.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.kirito.planmer.calendar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther kirito
 * @Date 2019-05-22
 * @NOTE 类说明
 */
public class HomeCalendarPageAdapter extends RecyclerView.Adapter<HomeCalendarPageAdapter.HomeCalendarItemView> {
    private List<String> list = new ArrayList<>();

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public HomeCalendarItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeCalendarItemView(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_home_calendar_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCalendarItemView holder, int position) {
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeCalendarItemView extends RecyclerView.ViewHolder {
        public TextView textView;

        public HomeCalendarItemView(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.test);
        }
    }
}
