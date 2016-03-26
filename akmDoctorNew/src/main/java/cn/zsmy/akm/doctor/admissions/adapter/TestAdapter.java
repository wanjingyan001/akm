package cn.zsmy.akm.doctor.admissions.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.zsmy.akm.doctor.admissions.bean.TestSuggest;
import cn.zsmy.doctor.R;

/**
 * Created by Administrator on 2016/1/26.
 */
public class TestAdapter extends BaseAdapter {
    private List<TestSuggest> testSuggests;
    private Context context;
    private String name;

    public TestAdapter(Context context, List<TestSuggest> testSuggests) {
        this.context = context;
        this.testSuggests = testSuggests;
    }

    @Override
    public int getCount() {
        return testSuggests.size();
    }

    @Override
    public TestSuggest getItem(int position) {
        return testSuggests.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_doc_suggest_test, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.initializeData(position);
        if (name != null) {
            holder.testName.setText(name);
        }
        return convertView;
    }


    public void setName(String name) {
        this.name = name;
    }


    class ViewHolder extends QBaseViewHolder {
        private TextView testName;

        @Override
        public void initializeView(View view) {
            testName = ((TextView) view.findViewById(R.id.test_name));
        }

        @Override
        public void initializeData(int position) {
            String tname = testSuggests.get(position).getTestName();
            testName.setText(tname);
        }
    }
}
