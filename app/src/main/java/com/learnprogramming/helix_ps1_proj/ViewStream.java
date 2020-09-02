package com.learnprogramming.helix_ps1_proj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ViewStream extends AppCompatActivity {

    VideoView videoplayer;
    private Button likeButton;
    private Button dislikeButton;
    private Button btnAddComment;
    private TextView likesCount;
    private TextView dislikesCount;
    private int likeCount;
    private int dislikeCount;
    private boolean isLiked;
    private boolean isDisliked;
    private EditText etAddComment;
    private RecyclerView rvComments;
    private TextView tvStreamer;
    private String username = "Ramit";
    private String streamerName = "Some Streamer";
    private List<Comment> commentList;


    public void setUsername(String username) {
        this.username = username;
    }

    MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stream);
        videoplayer = findViewById(R.id.videoView);
        mediaController = new MediaController(this);
        videoplayer.setVideoPath("android.resource://" + getPackageName()+"/"+R.raw.samplevideo);
        mediaController.setAnchorView(videoplayer);
        videoplayer.setMediaController(mediaController);
        videoplayer.start();

        likeButton = (Button) findViewById(R.id.btn_like);
        dislikeButton = (Button) findViewById(R.id.btn_dislike);
        likesCount = (TextView) findViewById(R.id.tv_like_count);
        dislikesCount = (TextView) findViewById(R.id.tv_dislike_count);
        etAddComment = findViewById(R.id.et_add_comment);
        btnAddComment = findViewById(R.id.btn_add_comment);
        tvStreamer = (TextView) findViewById(R.id.tv_streamer_info);
        rvComments = (RecyclerView) findViewById(R.id.rv_comments);

        likeCount = 0;
        likesCount.setText(String.valueOf(likeCount));
        dislikeCount = 0;
        dislikesCount.setText(String.valueOf(dislikeCount));

        isLiked = false;
        isDisliked = false;

        commentList = new ArrayList<>();

        tvStreamer.setText(streamerName);

        likeButton.setOnClickListener(v -> {
            if(!isLiked)
            {
                isLiked = true;
                likeCount++;
                likesCount.setText(String.valueOf(likeCount));
                likeButton.setBackgroundResource(R.drawable.ic_like_blue);
                if(isDisliked) {
                    isDisliked = false;
                    dislikeCount--;
                    dislikesCount.setText(String.valueOf(dislikeCount));
                    dislikeButton.setBackgroundResource(R.drawable.ic_dislike);
                }
            }
            else {
                isLiked = false;
                likeCount--;
                likesCount.setText(String.valueOf(likeCount));
                likeButton.setBackgroundResource(R.drawable.ic_like);
            }
        });

        dislikeButton.setOnClickListener(v -> {
            if(!isDisliked)
            {
                isDisliked = true;
                dislikeCount++;
                dislikesCount.setText(String.valueOf(dislikeCount));
                dislikeButton.setBackgroundResource(R.drawable.ic_dislike_blue);
                if(isLiked) {
                    likeCount--;
                    isLiked = false;
                    likesCount.setText(String.valueOf(likeCount));
                    likeButton.setBackgroundResource(R.drawable.ic_like);
                }
            }
            else {
                isDisliked = false;
                dislikeCount--;
                dislikesCount.setText(String.valueOf(dislikeCount));
                dislikeButton.setBackgroundResource(R.drawable.ic_dislike);
            }
        });

        btnAddComment.setOnClickListener(v -> {

            if(etAddComment.getText().toString().equals("")) return;

            Comment comment = new Comment();
            comment.setUsername(username);
            comment.setCommentText(etAddComment.getText().toString());
            comment.setCommentCalendar(Calendar.getInstance());

            commentList.add(comment);

            CommentAdapter commentAdapter = new CommentAdapter(ViewStream.this, commentList);
            rvComments.setAdapter(commentAdapter);
            rvComments.setLayoutManager(new LinearLayoutManager(ViewStream.this));
            rvComments.scrollToPosition(commentList.size()-1);
        });
    }
}