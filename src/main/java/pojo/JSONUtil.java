package pojo;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class JSONUtil {

    private JSONObject jsonObject;
     static String expected_Key;
    private static JSONObject jsonObject_temp;
    public static String getKey(JSONObject jsonObject, String Key){
        boolean key_exist = jsonObject.has(Key);
        Iterator<?> iterator_keys;
        String nextKey;
        if(!key_exist){
            iterator_keys = jsonObject.keys();
            while(iterator_keys.hasNext()){

                nextKey = (String) iterator_keys.next();
                try{
                    if(jsonObject.get(nextKey) instanceof JSONObject) {
                        key_exist = ((JSONObject) jsonObject.get(nextKey)).has(Key);
                            if(key_exist == false){
                                getKey(jsonObject.getJSONObject(nextKey),Key);
                            } else{
                                jsonObject_temp = jsonObject.getJSONObject(nextKey);
                                expected_Key = (jsonObject_temp.get(Key)).toString();
                            }
                    } else if(jsonObject.get(nextKey) instanceof JSONArray) {

                                JSONArray jsonArray = jsonObject.getJSONArray(nextKey);
                                for(int i=0;i<jsonArray.length();i++){
                                    String jsonArrayString = jsonArray.get(i).toString();
                                    JSONObject childJSONObject = new JSONObject(jsonArrayString);
                                    key_exist = childJSONObject.has(Key);
                                    if(key_exist == false){
                                        getKey(childJSONObject, Key);
                                    } else {
                                        expected_Key = (childJSONObject.get(Key)).toString();
                                    }
                                }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return expected_Key;
    }
}
