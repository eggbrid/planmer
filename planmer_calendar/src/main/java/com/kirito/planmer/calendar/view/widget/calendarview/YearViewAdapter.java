/*
 * Copyright (C) 2016 huanghaibin_dev <huanghaibin_dev@163.com>
 * WebSite https://github.com/MiracleTimes-Dev
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kirito.planmer.calendar.view.widget.calendarview;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Constructor;

final class YearViewAdapter extends BaseRecyclerAdapter<com.kirito.planmer.calendar.view.widget.calendarview.Month> {
    private CalendarViewDelegate mDelegate;
    private int mItemWidth, mItemHeight;

    YearViewAdapter(Context context) {
        super(context);
    }

    final void setup(CalendarViewDelegate delegate) {
        this.mDelegate = delegate;
    }


    final void setYearViewSize(int width, int height) {
        this.mItemWidth = width;
        this.mItemHeight = height;
    }

    @Override
    RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        com.kirito.planmer.calendar.view.widget.calendarview.YearView yearView;
        if (TextUtils.isEmpty(mDelegate.getYearViewClassPath())) {
            yearView = new com.kirito.planmer.calendar.view.widget.calendarview.DefaultYearView(mContext);
        } else {
            try {
                Constructor constructor = mDelegate.getYearViewClass().getConstructor(Context.class);
                yearView = (com.kirito.planmer.calendar.view.widget.calendarview.YearView) constructor.newInstance(mContext);
            } catch (Exception e) {
                e.printStackTrace();
                yearView = new DefaultYearView(mContext);
            }
        }
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.MATCH_PARENT);
        yearView.setLayoutParams(params);
        return new YearViewHolder(yearView, mDelegate);
    }

    @Override
    void onBindViewHolder(RecyclerView.ViewHolder holder, com.kirito.planmer.calendar.view.widget.calendarview.Month item, int position) {
        YearViewHolder h = (YearViewHolder) holder;
        com.kirito.planmer.calendar.view.widget.calendarview.YearView view = h.mYearView;
        view.init(item.getYear(), item.getMonth());
        view.measureSize(mItemWidth, mItemHeight);
    }

    private static class YearViewHolder extends RecyclerView.ViewHolder {
        com.kirito.planmer.calendar.view.widget.calendarview.YearView mYearView;

        YearViewHolder(View itemView, CalendarViewDelegate delegate) {
            super(itemView);
            mYearView = (YearView) itemView;
            mYearView.setup(delegate);
        }
    }
}
