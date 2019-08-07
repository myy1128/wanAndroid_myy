package com.example.wanandroid_myy.bean;

import java.util.List;

public class HomeTopBean {
    /**
     * data : [{"apkLink":"","author":"鸿洋","chapterId":298,"chapterName":"我的博客","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8004,"link":"http://www.wanandroid.com/blog/show/2030","niceDate":"2019-07-28","origin":"","prefix":"","projectLink":"","publishTime":1564282172000,"superChapterId":298,"superChapterName":"原创文章","tags":[],"title":"7月底收租 欢迎赞助本站","type":1,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":361,"chapterName":"课程推荐","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8537,"link":"https://market.geekbang.org/activity/channelcoupon/15?utm_source=web&amp;utm_medium=wananzhuo&amp;utm_campaign=changweiliuliang&amp;utm_term=zhanghongyang003&amp;utm_content=0530","niceDate":"2019-07-14","origin":"","prefix":"","projectLink":"","publishTime":1563111162000,"superChapterId":249,"superChapterName":"干货资源","tags":[],"title":"跟极客时间申请了一波199优惠券免费送 每人仅能领取一次","type":1,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"xiaoyang","chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>很多时候我们开发项目的时候，都需要抓包，很多情况下即使是Https也能正常抓包正常。<\/p>\r\n<p>那么问题来了：<\/p>\r\n<ol>\r\n<li>抓包的原理是？<\/li>\r\n<li>任何Https的 <strong>app<\/strong> 都能抓的到吗？<\/li>\r\n<li>如果不能，哪些情况下可以抓取，哪些情况下抓取不到？<\/li>\r\n<\/ol>","envelopePic":"","fresh":true,"id":8812,"link":"https://www.wanandroid.com/wenda/show/8812","niceDate":"16小时前","origin":"","prefix":"","projectLink":"","publishTime":1564494794000,"superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问 app https 抓包，一定能抓到吗？","type":1,"userId":2,"visible":1,"zan":4}]
     * errorCode : 0
     * errorMsg :
     */

    private int errorCode;
    private String errorMsg;
    private List<DataBean> data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * apkLink :
         * author : 鸿洋
         * chapterId : 298
         * chapterName : 我的博客
         * collect : false
         * courseId : 13
         * desc :
         * envelopePic :
         * fresh : false
         * id : 8004
         * link : http://www.wanandroid.com/blog/show/2030
         * niceDate : 2019-07-28
         * origin :
         * prefix :
         * projectLink :
         * publishTime : 1564282172000
         * superChapterId : 298
         * superChapterName : 原创文章
         * tags : []
         * title : 7月底收租 欢迎赞助本站
         * type : 1
         * userId : -1
         * visible : 1
         * zan : 0
         */

        private String apkLink;
        private String author;
        private int chapterId;
        private String chapterName;
        private boolean collect;
        private int courseId;
        private String desc;
        private String envelopePic;
        private boolean fresh;
        private int id;
        private String link;
        private String niceDate;
        private String origin;
        private String prefix;
        private String projectLink;
        private long publishTime;
        private int superChapterId;
        private String superChapterName;
        private String title;
        private int type;
        private int userId;
        private int visible;
        private int zan;
        private List<?> tags;

        public String getApkLink() {
            return apkLink;
        }

        public void setApkLink(String apkLink) {
            this.apkLink = apkLink;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public boolean isCollect() {
            return collect;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public boolean isFresh() {
            return fresh;
        }

        public void setFresh(boolean fresh) {
            this.fresh = fresh;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getProjectLink() {
            return projectLink;
        }

        public void setProjectLink(String projectLink) {
            this.projectLink = projectLink;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public int getSuperChapterId() {
            return superChapterId;
        }

        public void setSuperChapterId(int superChapterId) {
            this.superChapterId = superChapterId;
        }

        public String getSuperChapterName() {
            return superChapterName;
        }

        public void setSuperChapterName(String superChapterName) {
            this.superChapterName = superChapterName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }

        public List<?> getTags() {
            return tags;
        }

        public void setTags(List<?> tags) {
            this.tags = tags;
        }
    }
}
