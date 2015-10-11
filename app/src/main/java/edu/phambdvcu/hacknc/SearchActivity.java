package edu.phambdvcu.hacknc;

/**
 * Created by phambd on 10/10/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.List;

public class SearchActivity extends AppCompatActivity{
    private EditText searchInput;
    private ListView videosFound;

    private Handler handler;
    private static final String TAG = "MyTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchInput = (EditText)findViewById(R.id.searchBox);
        videosFound = (ListView)findViewById(R.id.searchListView);
        Log.v("myTag","ListView is Clickable");

        handler = new Handler();

        searchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    searchOnYoutube(v.getText().toString());
                    return false;
                }
                return true;
            }
        });

        videosFound.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos,
                                    long id) {
                VideoItem video = new VideoItem();
                video.setTitle(searchResults.get(pos).getTitle());
                video.setId(searchResults.get(pos).getId());
                VideoItem.setVideo(video);
            }
        });

    }
    private List<VideoItem> searchResults;

    private void searchOnYoutube(final String keywords){
        new Thread(){
            public void run(){
                PlaylistUpdates connector = new PlaylistUpdates(SearchActivity.this);
                searchResults = connector.search(keywords);
                handler.post(new Runnable(){
                    public void run(){
                        updateVideosFound();
                    }
                });
            }
        }.start();
    }

    private void updateVideosFound(){
        ArrayAdapter<VideoItem> adapter = new ArrayAdapter<VideoItem>(getApplicationContext(), R.layout.video_item, searchResults) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.list_item, parent, false);
                }
//                ImageView thumbnail = (ImageView)convertView.findViewById(R.id.video_thumbnail);
                TextView title = (TextView)convertView.findViewById(R.id.video_title);
//                TextView description = (TextView)convertView.findViewById(R.id.video_description);

                VideoItem searchResult = searchResults.get(position);

//                Picasso.with(getApplicationContext()).load(searchResult.getThumbnailURL()).into(thumbnail);
                title.setText(searchResult.getTitle());

//                description.setText(searchResult.getDescription());
                return convertView;
            }
        };

        videosFound.setAdapter(adapter);
    }

    // original
//    private void updateVideosFound(){
//        ArrayAdapter<VideoItem> adapter = new ArrayAdapter<VideoItem>(getApplicationContext(), R.layout.video_item, searchResults){
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                if(convertView == null){
//                    convertView = getLayoutInflater().inflate(R.layout.list_item, parent, false);
//                }
//                ImageView thumbnail = (ImageView)convertView.findViewById(R.id.video_thumbnail);
//                TextView title = (TextView)convertView.findViewById(R.id.video_title);
//                TextView description = (TextView)convertView.findViewById(R.id.video_description);
//
//                VideoItem searchResult = searchResults.get(position);
//
////                Picasso.with(getApplicationContext()).load(searchResult.getThumbnailURL()).into(thumbnail);
//                title.setText(searchResult.getTitle());
////                description.setText(searchResult.getDescription());
//                return convertView;
//            }
//        };
//
//        videosFound.setAdapter(adapter);
//    }


//    private void updateVideosFound(){
//        ArrayAdapter<VideoItem> adapter = new ArrayAdapter<VideoItem>(getApplicationContext(), R.layout.activity_search, searchResults){
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                if(convertView == null){
//                    convertView = getLayoutInflater().inflate(R.layout.activity_search, parent, false);
//                }
//
////                videosFound = (ListView) findViewById(R.id.searchListView);
//                //ImageView thumbnail = (ImageView)convertView.findViewById(R.id.video_thumbnail);
//                //TextView title = (TextView)convertView.findViewById(R.id.video_title);
//
//                VideoItem searchResult = searchResults.get(position);
//
//                //Picasso.with(getApplicationContext()).load(searchResult.getThumbnailURL()).into(thumbnail);
//                //title.setText(searchResult.getTitle());
//
//                return convertView;
//            }
//        };
//
//        videosFound.setAdapter(adapter);
//    }

    private void addClickListener(){
        videosFound.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos,
                                    long id) {
                Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
                intent.putExtra("VIDEO_ID", searchResults.get(pos).getId());
                Log.v("myTag", "ListView is Clickable");
                startActivity(intent);
            }
        });
    }
//    private void addClickListener(){
//        videosFound.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> av, View v, int pos,
//                                    long id) {
//                Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
//                intent.putExtra("VIDEO_ID", searchResults.get(pos).getId());
//                startActivity(intent);
//            }
//
//        });
//    }
//    private void addClickListener(){
//        videosFound.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> av, View v, int pos,
//                                    long id) {
//                Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
//                intent.putExtra("VIDEO_ID", searchResults.get(pos).getId());
//                startActivity(intent);
//            }
//
//        });
//    }
}