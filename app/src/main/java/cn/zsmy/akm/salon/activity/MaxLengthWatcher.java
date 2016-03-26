package cn.zsmy.akm.salon.activity;

import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/1/7.
 */
public class MaxLengthWatcher implements TextWatcher {
    private int maxLen = 0;
    private EditText editText = null;
    private Context context;


    public MaxLengthWatcher(int maxLen, EditText editText, Context context) {
        this.maxLen = maxLen;
        this.context = context;
        this.editText = editText;
    }

    /**
     * @param s
     * @param start
     * @param count
     * @param after
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    /**
     * @param s
     * @param start
     * @param before
     * @param count
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Editable editable = editText.getText();
        int len = editable.length();

        if (len > maxLen) {
            Toast.makeText(context, "最多输入" + maxLen + "个字符", Toast.LENGTH_SHORT).show();
            int selEndIndex = Selection.getSelectionEnd(editable);
            String str = editable.toString();
            //截取新字符串
            String newStr = str.substring(0, maxLen);
            editText.setText(newStr);
            editable = editText.getText();

            //新字符串的长度
            int newLen = editable.length();
            //旧光标位置超过字符串长度
            if (selEndIndex > newLen) {
                selEndIndex = editable.length();
            }
            //设置新光标所在的位置
            Selection.setSelection(editable, selEndIndex);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
