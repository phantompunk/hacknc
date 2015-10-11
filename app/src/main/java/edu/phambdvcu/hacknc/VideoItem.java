package edu.phambdvcu.hacknc;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by phambd on 10/10/15.
 */
public class VideoItem {
    private String title;
    private String thumbnailURL;
    private String id;

    private static ArrayList vidArray = new ArrayList();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnail) {
        this.thumbnailURL = thumbnail;
    }

    public static ArrayList<VideoItem> getVideo() {
        if (vidArray == null)
            vidArray = new ArrayList();

            return vidArray;
    }

    public static void setVideo(VideoItem v) {
        if (vidArray == null)
            vidArray = new ArrayList();

        vidArray.add(v);
    }

}

