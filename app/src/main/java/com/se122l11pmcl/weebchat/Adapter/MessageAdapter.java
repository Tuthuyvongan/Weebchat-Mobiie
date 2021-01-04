package com.se122l11pmcl.weebchat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.se122l11pmcl.weebchat.MessageActivity;
import com.se122l11pmcl.weebchat.Model.Chat;
import com.se122l11pmcl.weebchat.Model.User;
import com.se122l11pmcl.weebchat.R;
import com.squareup.picasso.Picasso;
import com.theophrast.ui.widget.SquareImageView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    private Context mContext;
    private List<Chat> mChat;
    private String imageurl;

    FirebaseUser fuser;

    public MessageAdapter(Context mContext, List<Chat> mChat, String imageurl){
        this.mChat = mChat;
        this.mContext = mContext;
        this.imageurl = imageurl;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
            return new MessageAdapter.ViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        Chat chat = mChat.get(position);
        if (chat.getType().equals("text")) {
            holder.show_message.setText(chat.getMessage());
            holder.show_message.setVisibility(View.VISIBLE);
            holder.txt_seen.setVisibility(View.VISIBLE);
            holder.txt_seen_img.setVisibility(View.GONE);
            holder.image_send.setVisibility(View.GONE);
        }else{
            if(chat.getType().equals("image")){
                holder.show_message.setVisibility(View.GONE);
                holder.image_send.setVisibility(View.VISIBLE);
                holder.txt_seen.setVisibility(View.GONE);
                holder.txt_seen_img.setVisibility(View.VISIBLE);
                Picasso.get().load(chat.getMessage()).into(holder.image_send);
            }
        }

        if (imageurl.equals("default")){
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        } else {
            Glide.with(mContext).load(imageurl).into(holder.profile_image);
        }

        if (position == mChat.size()-1){
            if (chat.isIsView()) {
                holder.txt_seen.setText("Seen");
                holder.txt_seen_img.setText("Seen");
            }
            else {
                holder.txt_seen.setText("Delivered");
                holder.txt_seen_img.setText("Delivered");
            }
        } else {
            holder.txt_seen.setVisibility(View.GONE);
            holder.txt_seen_img.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView show_message;
        public ImageView profile_image;
        public SquareImageView image_send;
        public TextView txt_seen_img;


        public TextView txt_seen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            show_message = itemView.findViewById(R.id.show_message);
            profile_image = itemView.findViewById(R.id.profile_image);
            image_send = itemView.findViewById(R.id.image_send);
            txt_seen = itemView.findViewById(R.id.txt_seen);
            txt_seen_img = itemView.findViewById(R.id.txt_seen_img);
        }
    }

    @Override
    public int getItemViewType(int position) {
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if (mChat.get(position).getSender().equals(fuser.getUid())){
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }
    }
}