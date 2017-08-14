package com.xianwei.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayList<News> newsList = new ArrayList<>();
        newsList.add(new News("this is a titlek whthi a long  tie whasert isdfi is too long oasdflk jsdf", "this is author", "Section",new Date(7523967970034938905L)));
        newsList.add(new News("this is a title", "this is author", "Section",new Date(7523967970034938905L)));
        newsList.add(new News("this is a title", "this is author", "Section",new Date(7523967970034938905L)));
        newsList.add(new News("this is a title", "this is author", "Section",new Date(7523967970034938905L)));
        newsList.add(new News("this is a titleaksdfnm,fdsfuwen asdf", "this is author", "Section",new Date(7523967970034938905L)));
        newsList.add(new News("this is a title", "this is author", "Section",new Date(7523967970034938905L)));
        newsList.add(new News("this is a tiasdf alksdflk asidfj alsdkfj tle", "this is author", "Section",new Date(7523967970034938905L)));

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(newsList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
