package cn.zsmy.akm.doctor.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.zsmy.akm.doctor.bean.PatientsNotice;
import cn.zsmy.akm.doctor.chat.bean.Message;
import cn.zsmy.akm.doctor.learning.bean.PatientCase;


public class ListSort {

    public static void sort(List<PatientsNotice.DataEntity> list) {
        Comparator comp = new Comparator() {
            public int compare(Object o1, Object o2) {
                PatientsNotice.DataEntity p1 = (PatientsNotice.DataEntity) o1;
                PatientsNotice.DataEntity p2 = (PatientsNotice.DataEntity) o2;
                return p1.getNoticeType().compareTo(p2.getNoticeType());
            }
        };
        Collections.sort(list, comp);
    }

    public static void sortChatRecord(List<Message> list) {
        Comparator comp = new Comparator() {
            public int compare(Object o1, Object o2) {
                Message p1 = (Message) o1;
                Message p2 = (Message) o2;
                return String.valueOf(p1.getTimestamp()).compareTo(String.valueOf(p2.getTimestamp()));
            }
        };
        Collections.sort(list, comp);
    }
//	public static List<List<ChildData>> cSort(HashMap<String,List<ChildData>> datas){
//		List<Entry<String,List<ChildData>>> infoIds = new ArrayList<Entry<String,List<ChildData>>>(
//				datas.entrySet());
//		Collections.sort(infoIds, new Comparator<Entry<String,List<ChildData>>>() {
//		    public int compare(Entry<String,List<ChildData>> o1,
//		            Entry<String,List<ChildData>> o2) {
//		    	System.out.println("key= "+o1.getKey()+ " and value= " + o2.getKey());
//		           return o2.getKey().compareTo(o1.getKey());
//		    }
//		});
//		List<List<ChildData>> cdata=new ArrayList<List<ChildData>>();
//		for (int i = 0;i<infoIds.size();i++) {
//		    Entry<String, List<ChildData>>ent=infoIds.get(i);
//		    cdata.add(ent.getValue());
//		}
//		return cdata;
//	}

    //病例列表帖子按时间排序
    public static void sortCaseList(List<PatientCase.DataEntity> list) {
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object lhs, Object rhs) {
                PatientCase.DataEntity lhs1 = (PatientCase.DataEntity) lhs;
                PatientCase.DataEntity rhs1 = (PatientCase.DataEntity) rhs;
                return String.valueOf(rhs1.getCreateTime()).compareTo(String.valueOf(lhs1.getCreateTime()));
            }
        };
        Collections.sort(list, comparator);
    }
}
