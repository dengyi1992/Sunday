package com.huawei.gxlm.sunday.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deng on 16-7-13.
 */
public class Tweet {

    /**
     * posts : [{"_id":"5788712614dabe0f7aba9055","name":"dengyi","head":"http://www.gravatar.com/avatar/1a885ae77be414b8e1beda9298e08f7a?s=48","time":{"date":"2016-07-15T05:14:14.452Z","year":2016,"month":"2016-7","day":"2016-7-15","minute":"2016-7-15 13:14"},"content":"这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试","title":"这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试","tags":["活动","文章","校园交易"],"imgurls":"---------aasdasdf-----------adsfasdfsad","icons":null,"comments":[],"reprint_info":[],"pv":0,"zan":0},{"_id":"5788701514dabe0f7aba9054","name":"dengyi","head":"http://www.gravatar.com/avatar/1a885ae77be414b8e1beda9298e08f7a?s=48","time":{"date":"2016-07-15T05:09:41.557Z","year":2016,"month":"2016-7","day":"2016-7-15","minute":"2016-7-15 13:09"},"content":"这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试","title":"这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试","tags":["活动","文章","校园交易"],"imgurls":"[{img:\"abc.png\"},{img:\"abc.png\"},{img:\"abc.png\"}]","icons":null,"comments":[],"reprint_info":[],"pv":0,"zan":0},{"_id":"57886fe714dabe0f7aba9053","name":"dengyi","head":"http://www.gravatar.com/avatar/1a885ae77be414b8e1beda9298e08f7a?s=48","time":{"date":"2016-07-15T05:08:55.144Z","year":2016,"month":"2016-7","day":"2016-7-15","minute":"2016-7-15 13:08"},"content":"Ghgsgvd ","title":"这是标题","tags":["文章",null,null],"imgurls":[],"icons":null,"comments":[],"reprint_info":[],"pv":0,"zan":0},{"_id":"57886fb214dabe0f7aba9052","name":"dengyi","head":"http://www.gravatar.com/avatar/1a885ae77be414b8e1beda9298e08f7a?s=48","time":{"date":"2016-07-15T05:08:02.307Z","year":2016,"month":"2016-7","day":"2016-7-15","minute":"2016-7-15 13:08"},"content":"Hshvdvdvdbb ","title":"这是标题","tags":["活动","文章","校园交易"],"imgurls":[],"icons":null,"comments":[],"reprint_info":[],"pv":0,"zan":0}]
     * page : 1
     * isFirstPage : true
     * isLastPage : true
     */

    private int page;
    private boolean isFirstPage;
    private boolean isLastPage;
    /**
     * _id : 5788712614dabe0f7aba9055
     * name : dengyi
     * head : http://www.gravatar.com/avatar/1a885ae77be414b8e1beda9298e08f7a?s=48
     * time : {"date":"2016-07-15T05:14:14.452Z","year":2016,"month":"2016-7","day":"2016-7-15","minute":"2016-7-15 13:14"}
     * content : 这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试
     * title : 这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试
     * tags : ["活动","文章","校园交易"]
     * imgurls : ---------aasdasdf-----------adsfasdfsad
     * icons : null
     * comments : []
     * reprint_info : []
     * pv : 0
     * zan : 0
     */

    private List<PostsBean> posts;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public List<PostsBean> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsBean> posts) {
        this.posts = posts;
    }

    public static class PostsBean implements Parcelable {
        private String _id;
        private String name;
        private String head;
        /**
         * date : 2016-07-15T05:14:14.452Z
         * year : 2016
         * month : 2016-7
         * day : 2016-7-15
         * minute : 2016-7-15 13:14
         */

        private TimeBean time;
        private String content;
        private String title;
        private String imgurls;
        private String icons;
        private int pv;
        private int zan;
        private List<String> tags;
        private List<?> comments;
        private List<?> reprint_info;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public TimeBean getTime() {
            return time;
        }

        public void setTime(TimeBean time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgurls() {
            return imgurls;
        }

        public void setImgurls(String imgurls) {
            this.imgurls = imgurls;
        }

        public String getIcons() {
            return icons;
        }

        public void setIcons(String icons) {
            this.icons = icons;
        }

        public int getPv() {
            return pv;
        }

        public void setPv(int pv) {
            this.pv = pv;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public List<?> getComments() {
            return comments;
        }

        public void setComments(List<?> comments) {
            this.comments = comments;
        }

        public List<?> getReprint_info() {
            return reprint_info;
        }

        public void setReprint_info(List<?> reprint_info) {
            this.reprint_info = reprint_info;
        }

        public static class TimeBean implements Parcelable {
            private String date;
            private int year;
            private String month;
            private String day;
            private String minute;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getMinute() {
                return minute;
            }

            public void setMinute(String minute) {
                this.minute = minute;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.date);
                dest.writeInt(this.year);
                dest.writeString(this.month);
                dest.writeString(this.day);
                dest.writeString(this.minute);
            }

            public TimeBean() {
            }

            protected TimeBean(Parcel in) {
                this.date = in.readString();
                this.year = in.readInt();
                this.month = in.readString();
                this.day = in.readString();
                this.minute = in.readString();
            }

            public static final Creator<TimeBean> CREATOR = new Creator<TimeBean>() {
                @Override
                public TimeBean createFromParcel(Parcel source) {
                    return new TimeBean(source);
                }

                @Override
                public TimeBean[] newArray(int size) {
                    return new TimeBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this._id);
            dest.writeString(this.name);
            dest.writeString(this.head);
            dest.writeParcelable(this.time, flags);
            dest.writeString(this.content);
            dest.writeString(this.title);
            dest.writeString(this.imgurls);
            dest.writeString(this.icons);
            dest.writeInt(this.pv);
            dest.writeInt(this.zan);
            dest.writeStringList(this.tags);
            dest.writeList(this.comments);
            dest.writeList(this.reprint_info);
        }

        public PostsBean() {
        }

        protected PostsBean(Parcel in) {
            this._id = in.readString();
            this.name = in.readString();
            this.head = in.readString();
            this.time = in.readParcelable(TimeBean.class.getClassLoader());
            this.content = in.readString();
            this.title = in.readString();
            this.imgurls = in.readString();
            this.icons = in.readParcelable(Object.class.getClassLoader());
            this.pv = in.readInt();
            this.zan = in.readInt();
            this.tags = in.createStringArrayList();
//            this.comments = new ArrayList<?>();
//            in.readList(this.comments, ?.class.getClassLoader());
//            this.reprint_info = new ArrayList<?>();
//            in.readList(this.reprint_info, ?.class.getClassLoader());
        }

        public static final Parcelable.Creator<PostsBean> CREATOR = new Parcelable.Creator<PostsBean>() {
            @Override
            public PostsBean createFromParcel(Parcel source) {
                return new PostsBean(source);
            }

            @Override
            public PostsBean[] newArray(int size) {
                return new PostsBean[size];
            }
        };
    }
}
