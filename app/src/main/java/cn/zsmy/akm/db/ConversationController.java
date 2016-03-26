package cn.zsmy.akm.db;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;

import cn.zsmy.akm.chat.bean.Message;
import cn.zsmy.akm.chat.im.Conversation;


/**
 * @author Stay
 * @version create time：Oct 23, 2014 10:45:30 AM
 */
public class ConversationController {
	public static Dao<Conversation, String> getDao() throws SQLException {
		return DBController.getInstance().getDB().getDao(Conversation.class);
	}

	public static void addOrUpdate(Conversation conversation) {
		try {
			getDao().createOrUpdate(conversation);
//			ConversationController.syncConversation(Conversation);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void delete(Conversation conversation) {
		try {
			getDao().delete(conversation);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询所有会话列表
	 * @return
	 */
	public static ArrayList<Conversation> queryAllByTimeAsc(){
		try {
			QueryBuilder<Conversation, String> queryBuilder = getDao().queryBuilder();
			queryBuilder.orderBy(Conversation.TIMESTAMP, false);
			return (ArrayList<Conversation>) getDao().query(queryBuilder.prepare());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

//	public static ArrayList<Conversation> queryAllByTimeAsc(String id1, String id2, int state, long timestamp) {
//		ArrayList<Conversation> Conversations = new ArrayList<Conversation>();
//		try {
//			QueryBuilder<Conversation, String> queryBuilder = getDao().queryBuilder();
//			queryBuilder.orderBy(Conversation.TIMESTAMP, false);
//			Where<Conversation, String> where = queryBuilder.where();
//			where.in(Conversation.SENDERID, id1, id2);
//			where.and();
//			where.in(Conversation.RECEIVERID, id1, id2);
//			if (state == Constants.LOADMORE) {
//				where.and();
//				where.lt(Conversation.TIMESTAMP, timestamp);
//			}
//			queryBuilder.limit(Constants.PAGECOUNT);
//			// (senderid=id1 and receiverid=id2) or (senderid=id2 and
//			// receiverid=id1)
//			// "where senderid in(id1,id2) and receiverid in(id1,id2) order by timestamp desc";
//			List<Conversation> tmp =  getDao().query(queryBuilder.prepare());
//			if (tmp == null || tmp.size() == 0) {
//				return Conversations;
//			}
//			for (int i = tmp.size() - 1; i >= 0; i--) {
//				Conversations.add(tmp.get(i));
//			}
//			return Conversations;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return Conversations;
//	}
	
	
//	public static ArrayList<Conversation> queryAllByTimeAsc(String id1, String id2,String caseId, int state, long timestamp) {
//		ArrayList<Conversation> Conversations = new ArrayList<Conversation>();
//		try {
//			QueryBuilder<Conversation, String> queryBuilder = getDao().queryBuilder();
//			queryBuilder.orderBy(Conversation.TIMESTAMP, false);
//			Where<Conversation, String> where = queryBuilder.where();
//			where.eq(Conversation.CASEID, caseId);
//			where.and();
//			where.in(Conversation.SENDERID, id1, id2);
//			where.and();
//			where.in(Conversation.RECEIVERID, id1, id2);
//			if (state == Constants.LOADMORE) {
//				where.and();
//				where.lt(Conversation.TIMESTAMP, timestamp);
//			}
//			queryBuilder.limit(Constants.PAGECOUNT);
//			// (senderid=id1 and receiverid=id2) or (senderid=id2 and
//			// receiverid=id1)
//			// "where senderid in(id1,id2) and receiverid in(id1,id2) order by timestamp desc";
//			List<Conversation> tmp =  getDao().query(queryBuilder.prepare());
//			if (tmp == null || tmp.size() == 0) {
//				return Conversations;
//			}
//			for (int i = tmp.size() - 1; i >= 0; i--) {
//				Conversations.add(tmp.get(i));
//			}
//			return Conversations;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return Conversations;
//	}

	public static Conversation queryById(String id) {
		try {
			return getDao().queryForId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void addOrUpdate(ArrayList<Conversation> t) {
		try {
			if (t != null && t.size() > 0) {
				for (Conversation conversation : t) {
					getDao().createOrUpdate(conversation);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 同步消息会话
	 * @param message
	 */
//	public static void syncMessage(Message message) {
//		addOrUpdate(message.copyTo());
//	}
//
//	public static void markAsRead(String targetId) {
//		Conversation conversation=queryById(targetId);
//		conversation.setUnReadNumber(0);
//		addOrUpdate(conversation);
//	}

//	private static void deleteOldData(Conversation Conversation) {
//		try {
//			DeleteBuilder<Conversation, String> builder = getDao().deleteBuilder();
//			Where<Conversation, String> where = builder.where();
//			where.in(Conversation.SENDERID, Conversation.getSenderId(), Conversation.getReceiverId());
//			where.and();
//			where.in(Conversation.RECEIVERID, Conversation.getSenderId(), Conversation.getReceiverId());
//			where.and();
//			where.lt(Conversation.TIMESTAMP, Conversation.getTimestamp());
//			getDao().delete(builder.prepare());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * @param targetId
	 * @param selfId
	 * @return query latest read Conversation timestamp
	 */
//	public static long queryEndTimestamp(String targetId, String selfId) {
//		try {
//			QueryBuilder<Conversation, String> queryBuilder = getDao().queryBuilder();
//			queryBuilder.orderBy(Conversation.TIMESTAMP, false);
//			Where<Conversation, String> where = queryBuilder.where();
//			where.eq(Conversation.RECEIVERID, selfId);
//			where.and();
//			where.eq(Conversation.SENDERID, targetId);
//			where.and();
//			where.eq(Conversation.ISREAD, true);
//			queryBuilder.limit(1l);
//			// (senderid=id1 and receiverid=id2) or (senderid=id2 and
//			// receiverid=id1)
//			// "where senderid in(id1,id2) and receiverid in(id1,id2) order by timestamp desc";
//			List<Conversation> Conversations2 = getDao().query(queryBuilder.prepare());
//			if (Conversations2 != null && Conversations2.size() > 0) {
//				return Conversations2.get(0).getTimestamp();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}
}
