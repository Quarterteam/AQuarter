package com.a.quarter.model.bean.joke;

import java.util.List;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/7/31.
 */

public class JokeBean {

    /**
     * character : [{"character_bad_num":0,"character_comment_num":0,"character_content":"shangchuanduanzi","character_dictionary_value":2,"character_forward_num":0,"character_id":18,"character_uptime":"2017-08-01 15:55:52.0","character_user_id":32,"list":[],"nice_num":0,"redis_character_bad_key":"3so88o2foo34a3ffM5133f0ssM86M54s","redis_character_comment_key":"12sof683100sa3ssf5641f7F2M65440o","redis_character_forward_key":"50846o65M7Fsosa7MF1sa68643608sf1","redis_character_nice_key":"os4f1Mf5aFs6F734140F43o25sF2fsa8","user":{"userHead":"http://169.254.1.100/ic_ss.jpg","userId":32,"userName":"baobao","userPassword":"123456","userPhone":"13484741060","userSex":"男"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"haha,taogehao","character_dictionary_value":2,"character_forward_num":0,"character_id":17,"character_uptime":"2017-08-01 15:54:20.0","character_user_id":32,"list":[],"nice_num":0,"redis_character_bad_key":"3f30s0010M8s265s103oss2sF703F067","redis_character_comment_key":"43M50FFM71foM7384sfaa0f7os8a2f17","redis_character_forward_key":"o0s41410f3ff7s533f640M7ao65s1s8o","redis_character_nice_key":"7f060f288374s64f4fMs51o454so26M7","user":{"userHead":"http://169.254.1.100/ic_ss.jpg","userId":32,"userName":"baobao","userPassword":"123456","userPhone":"13484741060","userSex":"男"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"","character_dictionary_value":2,"character_forward_num":0,"character_id":16,"character_uptime":"2017-08-01 15:33:17.0","character_user_id":1,"list":[],"nice_num":0,"redis_character_bad_key":"7f46o247s4105242ss7oaM0Fs5sf6847","redis_character_comment_key":"0F740f74FMf8665M1fM34542016134ss","redis_character_forward_key":"50s1626o147a68F56s1a327764s1a2a4","redis_character_nice_key":"s5fos57055685s483ss15o074aF243Ff","user":{"userHead":"","userId":1,"userName":"aa","userPassword":"aaa","userPhone":"123456","userSex":"男"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"","character_dictionary_value":2,"character_forward_num":0,"character_id":15,"character_uptime":"2017-08-01 15:33:06.0","character_user_id":1,"list":[],"nice_num":0,"redis_character_bad_key":"Ma22s22028514M1s82Fsfo155s3161f0","redis_character_comment_key":"72M1Mf7ss0o24o2f0sMfso8600oos2s7","redis_character_forward_key":"2so855s26of12706so43sfaa5738osF7","redis_character_nice_key":"451F37fFM624M1F81MsM1Ms61526a167","user":{"userHead":"","userId":1,"userName":"aa","userPassword":"aaa","userPhone":"123456","userSex":"男"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"","character_dictionary_value":2,"character_forward_num":0,"character_id":14,"character_uptime":"2017-08-01 15:32:46.0","character_user_id":1,"list":[],"nice_num":0,"redis_character_bad_key":"2055Ma6011Ma67a271s8667f61oo26f6","redis_character_comment_key":"1F4s21601oo7aasf422s0M6F4ssF0427","redis_character_forward_key":"a7a17sF0ssa64FoFasfM12oFsM6F6f7s","redis_character_nice_key":"aa0aof8a7sf1816s7sa41s5146s1sM61","user":{"userHead":"","userId":1,"userName":"aa","userPassword":"aaa","userPhone":"123456","userSex":"男"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"haha,taogehao","character_dictionary_value":2,"character_forward_num":0,"character_id":13,"character_uptime":"2017-08-01 14:20:28.0","character_user_id":32,"list":[],"nice_num":0,"redis_character_bad_key":"3s6410M6ao382M41so36588Fs5M27aF2","redis_character_comment_key":"oFf077418s22Maa6s3Mo61o840603s3s","redis_character_forward_key":"s7soaos50F2f66F2f42s30421632112s","redis_character_nice_key":"f7FFoM3o168a47MF2af72os8M3Fsa547","user":{"userHead":"http://169.254.1.100/ic_ss.jpg","userId":32,"userName":"baobao","userPassword":"123456","userPhone":"13484741060","userSex":"男"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"haha,taogehao","character_dictionary_value":2,"character_forward_num":0,"character_id":12,"character_uptime":"2017-08-01 11:35:25.0","character_user_id":32,"list":[],"nice_num":0,"redis_character_bad_key":"03183478F6870763oFos3f2f628fsoFs","redis_character_comment_key":"03183478F6870763oFos3f2f628fsoFs","redis_character_forward_key":"6f7M718828427381f4M1M4s317205saa","redis_character_nice_key":"6f7M718828427381f4M1M4s317205saa","user":{"userHead":"http://169.254.1.100/ic_ss.jpg","userId":32,"userName":"baobao","userPassword":"123456","userPhone":"13484741060","userSex":"男"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"haha,nihao","character_dictionary_value":2,"character_forward_num":0,"character_id":11,"character_uptime":"2017-08-01 10:24:44.0","character_user_id":32,"list":[],"nice_num":0,"redis_character_bad_key":"os880s25f1f2f4016s4FoM3s1607551a","redis_character_comment_key":"os880s25f1f2f4016s4FoM3s1607551a","redis_character_forward_key":"084M7fF77s113osoMoa0527oo6a73a7o","redis_character_nice_key":"084M7fF77s113osoMoa0527oo6a73a7o","user":{"userHead":"http://169.254.1.100/ic_ss.jpg","userId":32,"userName":"baobao","userPassword":"123456","userPhone":"13484741060","userSex":"男"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"gegehao","character_dictionary_value":2,"character_forward_num":0,"character_id":10,"character_uptime":"2017-07-31 16:07:53.0","character_user_id":32,"list":[],"nice_num":0,"redis_character_bad_key":"888f3Fa0F33F3f78sa5o3oaMFosa707o","redis_character_comment_key":"888f3Fa0F33F3f78sa5o3oaMFosa707o","redis_character_forward_key":"3o0582FMs2Ms8f21411FaF18sa12sF15","redis_character_nice_key":"3o0582FMs2Ms8f21411FaF18sa12sF15","user":{"userHead":"http://169.254.1.100/ic_ss.jpg","userId":32,"userName":"baobao","userPassword":"123456","userPhone":"13484741060","userSex":"男"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"taogehao","character_dictionary_value":2,"character_forward_num":0,"character_id":9,"character_uptime":"2017-07-31 15:39:02.0","character_user_id":32,"list":[],"nice_num":0,"redis_character_bad_key":"1o1MsoM122s16s763oF7M320124sss76","redis_character_comment_key":"1o1MsoM122s16s763oF7M320124sss76","redis_character_forward_key":"464ff5s3131oFMFMM5os32MF023oM6MM","redis_character_nice_key":"464ff5s3131oFMFMM5os32MF023oM6MM","user":{"userHead":"http://169.254.1.100/ic_ss.jpg","userId":32,"userName":"baobao","userPassword":"123456","userPhone":"13484741060","userSex":"男"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"adsfasdf","character_dictionary_value":2,"character_forward_num":0,"character_id":3,"character_uptime":"2017-07-26 22:25:17.0","character_user_id":2,"list":[],"nice_num":0,"redis_character_bad_key":"123456","redis_character_comment_key":"1234567","redis_character_forward_key":"12345678","redis_character_nice_key":"1234","user":{"userHead":"","userId":2,"userName":"bb","userPassword":"bbbb","userPhone":"12345","userSex":"女"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"adfassdf","character_dictionary_value":2,"character_forward_num":0,"character_id":8,"character_uptime":"2017-07-20 22:25:17.0","character_user_id":1,"list":[],"nice_num":0,"redis_character_bad_key":"1241","redis_character_comment_key":"1242","redis_character_forward_key":"1243","redis_character_nice_key":"1240","user":{"userHead":"","userId":1,"userName":"aa","userPassword":"aaa","userPhone":"123456","userSex":"男"}},{"character_bad_num":0,"character_comment_num":546,"character_content":"我擦 大没了","character_dictionary_value":2,"character_forward_num":546,"character_id":1,"character_uptime":"2017-07-20 21:56:33.0","character_user_id":1,"list":[],"nice_num":546,"redis_character_bad_key":"0o8F571ssoM7ss70287183oo75s82s6o","redis_character_comment_key":"0o8F571ssoM7ss70287183oo75s82s6o","redis_character_forward_key":"787777Ms4F07607f70o05s5Ms352fF43","redis_character_nice_key":"787777Ms4F07607f70o05s5Ms352fF43","user":{"userHead":"","userId":1,"userName":"aa","userPassword":"aaa","userPhone":"123456","userSex":"男"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"我擦 大没了","character_dictionary_value":2,"character_forward_num":0,"character_id":2,"character_uptime":"2017-07-15 22:25:17.0","character_user_id":1,"list":[],"nice_num":756,"redis_character_bad_key":"0o8F571ssoM7ss70287183o","redis_character_comment_key":"0o8F571ssoM7ss70287183oo","redis_character_forward_key":"787777Ms4F07607","redis_character_nice_key":"787777Ms4F07607f70o05s5Ms","user":{"userHead":"","userId":1,"userName":"aa","userPassword":"aaa","userPhone":"123456","userSex":"男"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"rrrr","character_dictionary_value":2,"character_forward_num":0,"character_id":5,"character_uptime":"2017-07-05 22:25:17.0","character_user_id":2,"list":[],"nice_num":0,"redis_character_bad_key":"3210","redis_character_comment_key":"3212","redis_character_forward_key":"3213","redis_character_nice_key":"321","user":{"userHead":"","userId":2,"userName":"bb","userPassword":"bbbb","userPhone":"12345","userSex":"女"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"qerqer","character_dictionary_value":2,"character_forward_num":0,"character_id":6,"character_uptime":"2017-07-05 22:25:17.0","character_user_id":1,"list":[],"nice_num":0,"redis_character_bad_key":"1232","redis_character_comment_key":"1233","redis_character_forward_key":"1235","redis_character_nice_key":"1231","user":{"userHead":"","userId":1,"userName":"aa","userPassword":"aaa","userPhone":"123456","userSex":"男"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"adsfsa","character_dictionary_value":2,"character_forward_num":0,"character_id":7,"character_uptime":"2017-07-05 22:25:17.0","character_user_id":2,"list":[],"nice_num":0,"redis_character_bad_key":"1237","redis_character_comment_key":"1238","redis_character_forward_key":"1239","redis_character_nice_key":"1236","user":{"userHead":"","userId":2,"userName":"bb","userPassword":"bbbb","userPhone":"12345","userSex":"女"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"adsfasdfa","character_dictionary_value":2,"character_forward_num":0,"character_id":4,"character_uptime":"2017-07-04 22:25:17.0","character_user_id":2,"list":[],"nice_num":0,"redis_character_bad_key":"98765","redis_character_comment_key":"987654","redis_character_forward_key":"9876543","redis_character_nice_key":"9876","user":{"userHead":"","userId":2,"userName":"bb","userPassword":"bbbb","userPhone":"12345","userSex":"女"}}]
     * code : 200
     */

    private String code;
    private List<Characterp> character;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Characterp> getCharacter() {
        return character;
    }

    public void setCharacter(List<Characterp> character) {
        this.character = character;
    }

    public static class Characterp {
        /**
         * character_bad_num : 0
         * character_comment_num : 0
         * character_content : shangchuanduanzi
         * character_dictionary_value : 2
         * character_forward_num : 0
         * character_id : 18
         * character_uptime : 2017-08-01 15:55:52.0
         * character_user_id : 32
         * list : []
         * nice_num : 0
         * redis_character_bad_key : 3so88o2foo34a3ffM5133f0ssM86M54s
         * redis_character_comment_key : 12sof683100sa3ssf5641f7F2M65440o
         * redis_character_forward_key : 50846o65M7Fsosa7MF1sa68643608sf1
         * redis_character_nice_key : os4f1Mf5aFs6F734140F43o25sF2fsa8
         * user : {"userHead":"http://169.254.1.100/ic_ss.jpg","userId":32,"userName":"baobao","userPassword":"123456","userPhone":"13484741060","userSex":"男"}
         */

        private int character_bad_num;
        private int character_comment_num;
        private String character_content;
        private int character_dictionary_value;
        private int character_forward_num;
        private int character_id;
        private String character_uptime;
        private int character_user_id;
        private int nice_num;
        private String redis_character_bad_key;
        private String redis_character_comment_key;
        private String redis_character_forward_key;
        private String redis_character_nice_key;
        private Userp user;
        private List<?> list;

        public int getCharacter_bad_num() {
            return character_bad_num;
        }

        public void setCharacter_bad_num(int character_bad_num) {
            this.character_bad_num = character_bad_num;
        }

        public int getCharacter_comment_num() {
            return character_comment_num;
        }

        public void setCharacter_comment_num(int character_comment_num) {
            this.character_comment_num = character_comment_num;
        }

        public String getCharacter_content() {
            return character_content;
        }

        public void setCharacter_content(String character_content) {
            this.character_content = character_content;
        }

        public int getCharacter_dictionary_value() {
            return character_dictionary_value;
        }

        public void setCharacter_dictionary_value(int character_dictionary_value) {
            this.character_dictionary_value = character_dictionary_value;
        }

        public int getCharacter_forward_num() {
            return character_forward_num;
        }

        public void setCharacter_forward_num(int character_forward_num) {
            this.character_forward_num = character_forward_num;
        }

        public int getCharacter_id() {
            return character_id;
        }

        public void setCharacter_id(int character_id) {
            this.character_id = character_id;
        }

        public String getCharacter_uptime() {
            return character_uptime;
        }

        public void setCharacter_uptime(String character_uptime) {
            this.character_uptime = character_uptime;
        }

        public int getCharacter_user_id() {
            return character_user_id;
        }

        public void setCharacter_user_id(int character_user_id) {
            this.character_user_id = character_user_id;
        }

        public int getNice_num() {
            return nice_num;
        }

        public void setNice_num(int nice_num) {
            this.nice_num = nice_num;
        }

        public String getRedis_character_bad_key() {
            return redis_character_bad_key;
        }

        public void setRedis_character_bad_key(String redis_character_bad_key) {
            this.redis_character_bad_key = redis_character_bad_key;
        }

        public String getRedis_character_comment_key() {
            return redis_character_comment_key;
        }

        public void setRedis_character_comment_key(String redis_character_comment_key) {
            this.redis_character_comment_key = redis_character_comment_key;
        }

        public String getRedis_character_forward_key() {
            return redis_character_forward_key;
        }

        public void setRedis_character_forward_key(String redis_character_forward_key) {
            this.redis_character_forward_key = redis_character_forward_key;
        }

        public String getRedis_character_nice_key() {
            return redis_character_nice_key;
        }

        public void setRedis_character_nice_key(String redis_character_nice_key) {
            this.redis_character_nice_key = redis_character_nice_key;
        }

        public Userp getUser() {
            return user;
        }

        public void setUser(Userp user) {
            this.user = user;
        }

        public List<?> getList() {
            return list;
        }

        public void setList(List<?> list) {
            this.list = list;
        }

        public static class Userp {
            /**
             * userHead : http://169.254.1.100/ic_ss.jpg
             * userId : 32
             * userName : baobao
             * userPassword : 123456
             * userPhone : 13484741060
             * userSex : 男
             */

            private String userHead;
            private int userId;
            private String userName;
            private String userPassword;
            private String userPhone;
            private String userSex;

            public String getUserHead() {
                return userHead;
            }

            public void setUserHead(String userHead) {
                this.userHead = userHead;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserPassword() {
                return userPassword;
            }

            public void setUserPassword(String userPassword) {
                this.userPassword = userPassword;
            }

            public String getUserPhone() {
                return userPhone;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }

            public String getUserSex() {
                return userSex;
            }

            public void setUserSex(String userSex) {
                this.userSex = userSex;
            }
        }
    }
}
