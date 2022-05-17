package com.example.app.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.Classes.Grade;

import java.util.List;

public class GradesListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Grade> gradeList;

    public GradesListViewAdapter(Context context, List<Grade> gradeList) {
        this.context = context;
        this.gradeList = gradeList;
    }

    @Override
    public int getCount() {
        return gradeList.size();
    }

    @Override
    public Object getItem(int pos) {
        return gradeList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View view, ViewGroup parent) {
        Log.d("MainActivity", "oui");
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.grade_card, null);
        }

        Grade grade = gradeList.get(pos);

        //reference to layout objects
        TextView tv_gradeValue = view.findViewById(R.id.value);
        TextView tv_gradeComment = view.findViewById(R.id.comment);
        TextView tv_gradeLogin = view.findViewById(R.id.login);

        //import data
        tv_gradeValue.setText(grade.getData("value"));

        if(grade.getData("comment") == null){
            tv_gradeComment.setVisibility(View.GONE);
        } else {
            tv_gradeComment.setText(grade.getData("comment"));
        }
        tv_gradeLogin.setText(grade.getData("user_id"));

        return view;
    }
}
