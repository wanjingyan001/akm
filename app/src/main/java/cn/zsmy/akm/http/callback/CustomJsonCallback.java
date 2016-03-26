package cn.zsmy.akm.http.callback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonSyntaxException;

import cn.zsmy.akm.http.AbstractCallback;
import cn.zsmy.akm.http.AppException;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Trace;

/**
 * Created by qinwei on 2015/10/6. email:qinwei_it@163.com
 */
public abstract class CustomJsonCallback<T> extends AbstractCallback<T> {
    private Type type;

    public CustomJsonCallback() {
        type = getClass().getGenericSuperclass();
        type = ((ParameterizedType) type).getActualTypeArguments()[0];
    }

    @Override
    public T bindData(String content) throws AppException {
        try {
            Trace.d(content);
            JSONObject jsonObject = new JSONObject(content);
            String status = jsonObject.getString("status");
            if (status.equals("success")) {
                return JsonParser.deserializeFromJson(jsonObject.opt("data").toString(), type);
            }
            throw new AppException(403, jsonObject.getString("message"));
        } catch (JsonSyntaxException e) {
            throw new AppException(AppException.ErrorType.PARSE_JSON, e.getMessage());
        } catch (JSONException e) {
            throw new AppException(AppException.ErrorType.PARSE_JSON, e.getMessage());
        }
    }
}
