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
     * posts : [{"_id":"5786409cf4478b1dd49e624b","name":"dengyi","head":null,"time":{"date":"2016-07-13T13:22:36.730Z","year":2016,"month":"2016-7","day":"2016-7-13","minute":"2016-7-13 21:22"},"content":"postpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpost","title":"我是邓yi","tags":["wahh","asdasd","as23212"],"imgurls":"[{img:abc.png},{img:abc.png},{img:abc.png}]","icons":null,"comments":[],"reprint_info":[],"pv":0,"zan":0},{"_id":"5786409cf4478b1dd49e624a","name":"dengyi","head":null,"time":{"date":"2016-07-13T13:22:36.571Z","year":2016,"month":"2016-7","day":"2016-7-13","minute":"2016-7-13 21:22"},"content":"postpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpost","title":"我是邓yi","tags":["wahh","asdasd","as23212"],"imgurls":"[{img:abc.png},{img:abc.png},{img:abc.png}]","icons":null,"comments":[],"reprint_info":[],"pv":0,"zan":0},{"_id":"5786409cf4478b1dd49e6249","name":"dengyi","head":null,"time":{"date":"2016-07-13T13:22:36.395Z","year":2016,"month":"2016-7","day":"2016-7-13","minute":"2016-7-13 21:22"},"content":"postpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpost","title":"我是邓yi","tags":["wahh","asdasd","as23212"],"imgurls":"[{img:abc.png},{img:abc.png},{img:abc.png}]","icons":null,"comments":[],"reprint_info":[],"pv":0,"zan":0},{"_id":"5786409cf4478b1dd49e6248","name":"dengyi","head":null,"time":{"date":"2016-07-13T13:22:36.235Z","year":2016,"month":"2016-7","day":"2016-7-13","minute":"2016-7-13 21:22"},"content":"postpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpost","title":"我是邓yi","tags":["wahh","asdasd","as23212"],"imgurls":"[{img:abc.png},{img:abc.png},{img:abc.png}]","icons":null,"comments":[],"reprint_info":[],"pv":0,"zan":0},{"_id":"5786409cf4478b1dd49e6247","name":"dengyi","head":null,"time":{"date":"2016-07-13T13:22:36.075Z","year":2016,"month":"2016-7","day":"2016-7-13","minute":"2016-7-13 21:22"},"content":"postpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpost","title":"我是邓yi","tags":["wahh","asdasd","as23212"],"imgurls":"[{img:abc.png},{img:abc.png},{img:abc.png}]","icons":null,"comments":[],"reprint_info":[],"pv":0,"zan":0},{"_id":"5786409bf4478b1dd49e6246","name":"dengyi","head":null,"time":{"date":"2016-07-13T13:22:35.793Z","year":2016,"month":"2016-7","day":"2016-7-13","minute":"2016-7-13 21:22"},"content":"postpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpost","title":"我是邓yi","tags":["wahh","asdasd","as23212"],"imgurls":"[{img:abc.png},{img:abc.png},{img:abc.png}]","icons":null,"comments":[],"reprint_info":[],"pv":0,"zan":0}]
     * page : 1
     * isFirstPage : true
     * isLastPage : true
     * user : {"name":"dengyi","password":"c07b982d7ae82ed3c852455cecccf131","email":"978548481@qq.com","user_collection":"","account":0}
     */

    private int page;
    private boolean isFirstPage;
    private boolean isLastPage;
    /**
     * name : dengyi
     * password : c07b982d7ae82ed3c852455cecccf131
     * email : 978548481@qq.com
     * user_collection :
     * account : 0
     */

    private UserEntity user;
    /**
     * _id : 5786409cf4478b1dd49e624b
     * name : dengyi
     * head : null
     * time : {"date":"2016-07-13T13:22:36.730Z","year":2016,"month":"2016-7","day":"2016-7-13","minute":"2016-7-13 21:22"}
     * content : postpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpostpost
     * title : 我是邓yi
     * tags : ["wahh","asdasd","as23212"]
     * imgurls : [{img:abc.png},{img:abc.png},{img:abc.png}]
     * icons : null
     * comments : []
     * reprint_info : []
     * pv : 0
     * zan : 0
     */

    private List<PostsEntity> posts;

    public static Tweet objectFromData(String str) {

        return new com.google.gson.Gson().fromJson(str, Tweet.class);
    }

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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<PostsEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsEntity> posts) {
        this.posts = posts;
    }

    public static class UserEntity {
        private String name;
        private String password;
        private String email;
        private String user_collection;
        private int account;

        public static UserEntity objectFromData(String str) {

            return new com.google.gson.Gson().fromJson(str, UserEntity.class);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUser_collection() {
            return user_collection;
        }

        public void setUser_collection(String user_collection) {
            this.user_collection = user_collection;
        }

        public int getAccount() {
            return account;
        }

        public void setAccount(int account) {
            this.account = account;
        }
    }

    public static class PostsEntity implements Parcelable {
        private String _id;
        private String name;
        private String head;
        /**
         * date : 2016-07-13T13:22:36.730Z
         * year : 2016
         * month : 2016-7
         * day : 2016-7-13
         * minute : 2016-7-13 21:22
         */

        private TimeEntity time;
        private String content;
        private String title;
        private String imgurls;
        private String icons;
        private int pv;
        private int zan;
        private List<String> tags;
        private List<?> comments;
        private List<?> reprint_info;

        public static PostsEntity objectFromData(String str) {

            return new com.google.gson.Gson().fromJson(str, PostsEntity.class);
        }

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

        public TimeEntity getTime() {
            return time;
        }

        public void setTime(TimeEntity time) {
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

        public static class TimeEntity implements Parcelable {
            private String date;
            private int year;
            private String month;
            private String day;
            private String minute;

            public static TimeEntity objectFromData(String str) {

                return new com.google.gson.Gson().fromJson(str, TimeEntity.class);
            }

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

            public TimeEntity() {
            }

            protected TimeEntity(Parcel in) {
                this.date = in.readString();
                this.year = in.readInt();
                this.month = in.readString();
                this.day = in.readString();
                this.minute = in.readString();
            }

            public static final Creator<TimeEntity> CREATOR = new Creator<TimeEntity>() {
                public TimeEntity createFromParcel(Parcel source) {
                    return new TimeEntity(source);
                }

                public TimeEntity[] newArray(int size) {
                    return new TimeEntity[size];
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

        public PostsEntity() {
        }

        protected PostsEntity(Parcel in) {
            this._id = in.readString();
            this.name = in.readString();
            this.head = in.readParcelable(Object.class.getClassLoader());
            this.time = in.readParcelable(TimeEntity.class.getClassLoader());
            this.content = in.readString();
            this.title = in.readString();
            this.imgurls = in.readString();
            this.icons = in.readParcelable(Object.class.getClassLoader());
            this.pv = in.readInt();
            this.zan = in.readInt();
            this.tags = in.createStringArrayList();
//            this.comments = new ArrayList<?>();
            in.readList(this.comments, List.class.getClassLoader());
//            this.reprint_info = new ArrayList<?>();
            in.readList(this.reprint_info, List.class.getClassLoader());
        }

        public static final Parcelable.Creator<PostsEntity> CREATOR = new Parcelable.Creator<PostsEntity>() {
            public PostsEntity createFromParcel(Parcel source) {
                return new PostsEntity(source);
            }

            public PostsEntity[] newArray(int size) {
                return new PostsEntity[size];
            }
        };
    }
}
