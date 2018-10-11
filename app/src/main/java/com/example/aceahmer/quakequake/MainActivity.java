package com.example.aceahmer.quakequake;



import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<DataModel>>{
ListView listView;
TextView empty;
ProgressBar progress;
static String urlString="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2012-01-01&endtime=2012-12-01&minmagnitude=3&limit=10";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.list);
        empty=findViewById(R.id.empty);
        empty.setVisibility(View.GONE);
        progress=findViewById(R.id.progress);

        if(network.haveNetworkConnection(getApplicationContext()))
        getSupportLoaderManager().initLoader(1, null, this).forceLoad();
        else{
            empty.setText("No Network");
            listView.setEmptyView(empty);
            progress.setVisibility(View.GONE);
        }

    }
    private void updateUI(final ArrayList<DataModel>  arrayList) {

        ListAdapter adapter=new ListAdapter(getApplicationContext(),arrayList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(arrayList.get(position).getUrl()));
                startActivity(browserIntent);
            }
        });
    }


    @Override
    public android.support.v4.content.Loader<ArrayList<DataModel>> onCreateLoader(int id, Bundle args) {
        return new BackgroundThreadLoader(this);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<ArrayList<DataModel>> loader, ArrayList<DataModel> data) {
        progress.setVisibility(View.GONE);
        if(data!=null&&!data.isEmpty()) {
            updateUI(data);
        }
        else {
            listView.setEmptyView(empty);
        }
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<ArrayList<DataModel>> loader) {
        updateUI(new ArrayList<DataModel>());
    }




    private static class BackgroundThreadLoader extends AsyncTaskLoader<ArrayList<DataModel>> {
        public BackgroundThreadLoader(Context context) {
            super(context);
        }

        @Nullable
        @Override
        public ArrayList<DataModel> loadInBackground() {
            URL url=createUrl();
            MyJSonParser myJSonParser=new MyJSonParser(makeNetworkRequest.makeHttpRequest(url));
            ArrayList<DataModel> dataModels=new ArrayList<DataModel>();
            dataModels.addAll(myJSonParser.data());
            return dataModels;
        }


    }

    private static URL createUrl() {
        URL url=null;
        try {
            url=new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
