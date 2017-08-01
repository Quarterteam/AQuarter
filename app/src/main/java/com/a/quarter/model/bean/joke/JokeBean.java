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
     * character : [{"character_bad_num":0,"character_comment_num":0,"character_content":"adsfasdf","character_dictionary_value":2,"character_forward_num":0,"character_id":3,"character_uptime":"2017-07-26 22:25:17.0","character_user_id":2,"list":[],"nice_num":0,"redis_character_bad_key":"123456","redis_character_comment_key":"1234567","redis_character_forward_key":"12345678","redis_character_nice_key":"1234","user":{"userHead":"b","userId":2,"userName":"bb","userPassword":"bbbb","userPhone":"12345","userSex":"女"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"adfassdf","character_dictionary_value":2,"character_forward_num":0,"character_id":8,"character_uptime":"2017-07-20 22:25:17.0","character_user_id":1,"list":[],"nice_num":0,"redis_character_bad_key":"1241","redis_character_comment_key":"1242","redis_character_forward_key":"1243","redis_character_nice_key":"1240","user":{"userHead":"aaa","userId":1,"userName":"aa","userPassword":"aaa","userPhone":"123456","userSex":"男"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"我擦 大没了","character_dictionary_value":2,"character_forward_num":0,"character_id":1,"character_uptime":"2017-07-20 21:56:33.0","character_user_id":1,"list":[],"nice_num":0,"redis_character_bad_key":"0o8F571ssoM7ss70287183oo75s82s6o","redis_character_comment_key":"0o8F571ssoM7ss70287183oo75s82s6o","redis_character_forward_key":"787777Ms4F07607f70o05s5Ms352fF43","redis_character_nice_key":"787777Ms4F07607f70o05s5Ms352fF43","user":{"userHead":"aaa","userId":1,"userName":"aa","userPassword":"aaa","userPhone":"123456","userSex":"男"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"我擦 大没了","character_dictionary_value":2,"character_forward_num":0,"character_id":2,"character_uptime":"2017-07-15 22:25:17.0","character_user_id":1,"list":[],"nice_num":0,"redis_character_bad_key":"0o8F571ssoM7ss70287183oo75s82s6o","redis_character_comment_key":"0o8F571ssoM7ss70287183oo75s82s6o","redis_character_forward_key":"787777Ms4F07607f70o05s5Ms352fF43","redis_character_nice_key":"787777Ms4F07607f70o05s5Ms352fF43","user":{"userHead":"aaa","userId":1,"userName":"aa","userPassword":"aaa","userPhone":"123456","userSex":"男"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"rrrr","character_dictionary_value":2,"character_forward_num":0,"character_id":5,"character_uptime":"2017-07-05 22:25:17.0","character_user_id":2,"list":[],"nice_num":0,"redis_character_bad_key":"3210","redis_character_comment_key":"3212","redis_character_forward_key":"3213","redis_character_nice_key":"321","user":{"userHead":"b","userId":2,"userName":"bb","userPassword":"bbbb","userPhone":"12345","userSex":"女"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"qerqer","character_dictionary_value":2,"character_forward_num":0,"character_id":6,"character_uptime":"2017-07-05 22:25:17.0","character_user_id":1,"list":[],"nice_num":0,"redis_character_bad_key":"1232","redis_character_comment_key":"1233","redis_character_forward_key":"1235","redis_character_nice_key":"1231","user":{"userHead":"aaa","userId":1,"userName":"aa","userPassword":"aaa","userPhone":"123456","userSex":"男"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"adsfsa","character_dictionary_value":2,"character_forward_num":0,"character_id":7,"character_uptime":"2017-07-05 22:25:17.0","character_user_id":2,"list":[],"nice_num":0,"redis_character_bad_key":"1237","redis_character_comment_key":"1238","redis_character_forward_key":"1239","redis_character_nice_key":"1236","user":{"userHead":"b","userId":2,"userName":"bb","userPassword":"bbbb","userPhone":"12345","userSex":"女"}},{"character_bad_num":0,"character_comment_num":0,"character_content":"adsfasdfa","character_dictionary_value":2,"character_forward_num":0,"character_id":4,"character_uptime":"2017-07-04 22:25:17.0","character_user_id":2,"list":[],"nice_num":0,"redis_character_bad_key":"98765","redis_character_comment_key":"987654","redis_character_forward_key":"9876543","redis_character_nice_key":"9876","user":{"userHead":"b","userId":2,"userName":"bb","userPassword":"bbbb","userPhone":"12345","userSex":"女"}}]
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
         * character_content : adsfasdf
         * character_dictionary_value : 2
         * character_forward_num : 0
         * character_id : 3
         * character_uptime : 2017-07-26 22:25:17.0
         * character_user_id : 2
         * list : []
         * nice_num : 0
         * redis_character_bad_key : 123456
         * redis_character_comment_key : 1234567
         * redis_character_forward_key : 12345678
         * redis_character_nice_key : 1234
         * user : {"userHead":"b","userId":2,"userName":"bb","userPassword":"bbbb","userPhone":"12345","userSex":"女"}
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
             * userHead : b
             * userId : 2
             * userName : bb
             * userPassword : bbbb
             * userPhone : 12345
             * userSex : 女
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
