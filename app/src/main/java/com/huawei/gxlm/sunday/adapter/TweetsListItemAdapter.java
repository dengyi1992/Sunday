package com.huawei.gxlm.sunday.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import de.hdodenhof.circleimageview.CircleImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huawei.gxlm.sunday.R;
import com.huawei.gxlm.sunday.api.Api;
import com.huawei.gxlm.sunday.bean.Artical;
import com.huawei.gxlm.sunday.bean.Tweet;
import com.huawei.gxlm.sunday.widget.enter.AutoHeightGridView;
import android.widget.CheckBox;
import android.widget.TextSwitcher;
import android.widget.ImageButton;

public class TweetsListItemAdapter extends BaseAdapter {

    private List<Tweet.PostsBean> objects = new ArrayList<Tweet.PostsBean>();

    private Context context;
    private LayoutInflater layoutInflater;



    public TweetsListItemAdapter(Context context, List<Tweet.PostsBean> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects=objects;
//        for (int i = 0; i < 5; i++) {
//            objects.add(new Tweet.PostsBean());
//        }
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Tweet.PostsBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.tweets_list_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Tweet.PostsBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Tweet.PostsBean object, ViewHolder holder) {
        //TODO implement
        Glide.with(context).load(object.getHead()).placeholder(R.mipmap.logo1).into(holder.avatar);
        holder.username.setText(object.getName());
        holder.time.setText(object.getTime().getMinute());
        holder.content.setText(object.getContent());
//        holder.gridView
        holder.likeCount.setText(object.getZan()+"");
        holder.commentCount.setText(object.getComments().size()+"");
        String s = context.getString(R.string.img_split);
        List<String> urls=new ArrayList<>();
        String[] imgs = object.getImgurls().split("---"+"\\*\\*\\*" +"---");
        for (String imgurl:
             imgs) {
            urls.add(Api.HOST+"/"+imgurl);
        }
        GridPhotoAdapter adapter = new GridPhotoAdapter(context, urls);
        holder.gridView.setAdapter(adapter);

    }

    protected class ViewHolder {
    private CircleImageView avatar;
    private TextView username;
    private TextView time;
    private TextView content;
    private AutoHeightGridView gridView;
    private CheckBox btnLike;
    private TextSwitcher tsLikesCounter;
    private TextView likeCount;
    private ImageButton btnComments;
    private TextView commentCount;

        public ViewHolder(View view) {
            avatar = (CircleImageView) view.findViewById(R.id.avatar);
            username = (TextView) view.findViewById(R.id.username);
            time = (TextView) view.findViewById(R.id.time);
            content = (TextView) view.findViewById(R.id.content);
            gridView = (AutoHeightGridView) view.findViewById(R.id.gridView);
            btnLike = (CheckBox) view.findViewById(R.id.btnLike);
            tsLikesCounter = (TextSwitcher) view.findViewById(R.id.tsLikesCounter);
            likeCount = (TextView) view.findViewById(R.id.like_count);
            btnComments = (ImageButton) view.findViewById(R.id.btnComments);
            commentCount = (TextView) view.findViewById(R.id.comment_count);
        }
    }
}
