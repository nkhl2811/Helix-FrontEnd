package com.learnprogramming.helix_ps1_proj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private Context context;
    private List<Comment> commentList;

    public CommentAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment currentComment = commentList.get(position);

        String username = currentComment.getUsername();
        String commentText = currentComment.getCommentText();
        Calendar commentCalendar = currentComment.getCommentCalendar();

        String timeAddedText =  getTimeAddedText(commentCalendar);

        holder.tvUsername.setText(username);
        holder.tvCommentText.setText(commentText);
        holder.tvTimeAdded.setText(timeAddedText);
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvUsername;
        public TextView tvCommentText;
        public TextView tvTimeAdded;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            tvUsername = itemView.findViewById(R.id.tv_user_name);
            tvCommentText = itemView.findViewById(R.id.tv_comment_text);
            tvTimeAdded = itemView.findViewById(R.id.tv_comment_time);
        }
    }

    private String getTimeAddedText(Calendar commentCalendar) {

        int sec = commentCalendar.get(Calendar.SECOND);
        int min = commentCalendar.get(Calendar.MINUTE);
        int hrs = commentCalendar.get(Calendar.HOUR_OF_DAY);
        int date = commentCalendar.get(Calendar.DATE);
        int mon = commentCalendar.get(Calendar.MONTH);
        int yrs = commentCalendar.get(Calendar.YEAR);

        Calendar rightNow = Calendar.getInstance();
        int curSec = rightNow.get(Calendar.SECOND);
        int curMin = rightNow.get(Calendar.MINUTE);
        int curHrs = rightNow.get(Calendar.HOUR_OF_DAY);
        int curDate = rightNow.get(Calendar.DATE);
        int curMon = rightNow.get(Calendar.MONTH);
        int curYrs = rightNow.get(Calendar.YEAR);

        String timeAddedText;

        if(yrs - curYrs > -1) {
            if(mon - curMon > -1) {
                if(date - curDate > -1) {
                    if(hrs - curHrs > -1) {
                        if(min - curMin > -1) {
                            if(sec - curSec > -1) {
                                timeAddedText = "just now";
                            } else {
                                timeAddedText = String.valueOf(-(sec - curSec)).concat(" seconds ago");
                            }
                        } else {
                            timeAddedText = String.valueOf(-(min - curMin)).concat(" minutes ago");
                        }
                    } else {
                        timeAddedText = String.valueOf(-(hrs - curHrs)).concat(" hours ago");
                    }
                } else {
                    timeAddedText = String.valueOf(-(date - curDate)).concat(" days ago");
                }
            } else {
                timeAddedText = String.valueOf(-(mon-curMon)).concat(" months ago");
            }
        } else {
            timeAddedText = String.valueOf(-(yrs-curYrs)).concat(" years ago");
        }

        return timeAddedText;
    }
}
