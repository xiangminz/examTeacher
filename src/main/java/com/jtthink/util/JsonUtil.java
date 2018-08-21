package com.jtthink.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Collection;
import java.util.List;

public class JsonUtil {

    /**
     * 将 Array,list,set 解析成 Json 串
     * @return Json 串
     */
    public static String arrayToJsonStr(Object objs){
        JSONArray json = JSONArray.fromObject(objs);
        return json.toString();
    }

    /***
     * 将javabean对象和map对象 解析成 Json 串
     * @param obj
     * @return
     */
    public static String objectToJsonStr(Object obj){
        JSONObject json = JSONObject.fromObject(obj);
        return json.toString();

    }

    /**
     * 将  Json 串 解析成 Array,list,set
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Collection<T> jsonStrToArray(String jsonStr){
        JSONArray jsonArray = JSONArray.fromObject(jsonStr);
        Object array = JSONArray.toArray(jsonArray);
        return (Collection<T>) array;
    }

    /**
     * 将  Json 串 解析成 Array
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] jsonStrToArray(String jsonStr, Class<T> clazz){
        JSONArray jsonArray = JSONArray.fromObject(jsonStr);
        return (T[]) JSONArray.toArray(jsonArray,clazz);
    }

    /**
     * 将  Json 串 解析成 Collection
     * @return
     */
    public static <T> Collection<T> jsonStrToCollection(String jsonStr, Class<T> clazz){
        JSONArray jsonArray = JSONArray.fromObject(jsonStr);
        @SuppressWarnings("unchecked")
        Collection<T>  array = JSONArray.toCollection(jsonArray,clazz);
        return array;
    }

    /**
     * 将  Json 串 解析成 list
     * @return
     */
    public static <T> List<T> jsonStrToList(String jsonStr, Class<T> clazz){
        return (List<T>) jsonStrToCollection(jsonStr,clazz);
    }

    /**
     * 将  Json 串 解析成 Map或者javabean
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T jsonStrToObject(String jsonStr, Class<T> clazz){
        JSONObject json = JSONObject.fromObject(jsonStr);
        Object obj = JSONObject.toBean(json, clazz);
        return (T) obj;
    }

    public static void main(String[] args){

        /* arrayToJsonStr 测试
        * 结果：[{"name":"zhangsan","pass":"123"},{"name":"lisi","pass":"456"}]
        List<UserInfo> list = new ArrayList<UserInfo>();
        list.add(new UserInfo("zhangsan","123"));
        list.add(new UserInfo("lisi","456"));
        String str = arrayToJsonStr(list);
        System.out.println(str);
        */

        /* objectToJsonStr 测试
        * 结果：{"name":"zhangsan","pass":"lisi"}
        *       {"zhangsan":{"name":"zhangsan","pass":"lisi"}}
        UserInfo userInfo = new UserInfo("zhangsan", "lisi");
        HashMap<String, UserInfo> map = new HashMap<String, UserInfo>();
        map.put("zhangsan", userInfo);
        String str = objectToJsonStr(map);
        System.out.println(str);
        */

        /*测试 jsonStrToObject
        String str = "{'name':'zhangsan','pass':'lisi'}";
        JSONObject obj = new JSONObject().fromObject(str);
        UserInfo jb = (UserInfo)JSONObject.toBean(obj, UserInfo.class);
        System.out.println(jb);
        */

        /*测试 jsonStrToObject
        String str = "[{'name':'niushijin','pass':'yanghui'},{'name':'niushijin1','pass':'yanghui1'}]";
        List<UserInfo> list= jsonStrToList(str, UserInfo.class);
        System.out.println(list);
        */
    }

}
