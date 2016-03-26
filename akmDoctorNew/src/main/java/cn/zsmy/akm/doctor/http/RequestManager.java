package cn.zsmy.akm.doctor.http;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by qinwei on 2015/10/6. email:qinwei_it@163.com
 */
public class RequestManager {
	private static RequestManager mInstance;
	private ExecutorService mExecutors;
	private ArrayList<Request> requests;

	private RequestManager() {
		requests = new ArrayList<Request>();
		mExecutors = Executors.newFixedThreadPool(5);
	}

	public static RequestManager getInstance() {
		if (mInstance == null) {
			mInstance = new RequestManager();
		}
		return mInstance;
	}

	public void execute(String tag, Request request) {
		request.setTag(tag);
		requests.add(request);
		request.execute(mExecutors);
	}

	public void cancelRequest(String tag) {
		cancelRequest(tag, false);
	}

	public void cancelRequest(String tag, boolean force) {
        Trace.d("before cancel size=" + requests.size());
        ArrayList<Request> removes = new ArrayList<Request>();
        for (int i = 0; i < requests.size(); i++) {
            if (tag.equals(requests.get(i).tag)) {
                requests.get(i).cancel(force);
                removes.add(requests.get(i));
            }
        }
        requests.removeAll(removes);
        Trace.d("after cancel size=" + requests.size());
    }

	public void cancelRequests() {
		for (int i = 0; i < requests.size(); i++) {
			requests.get(i).cancel(true);
		}
		requests.clear();
	}
}
