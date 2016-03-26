package cn.zsmy.akm.db.PushMessage;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.zsmy.akm.chat.utils.Trace;

/**
 * Created by Administrator on 2016/2/25.
 */
public class PushMessageController {

    public static Dao<PushMessage, String> getDao() throws SQLException {
        return PushDBController.getInstance().getDB().getMessages();
    }


    /**
     * 更新或添加一条数据
     *
     * @param pushMessage
     */
    public static void addOrUpdate(PushMessage pushMessage) {
        try {
            getDao().createOrUpdate(pushMessage);
            Trace.d("数据插入成功" + pushMessage.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除一条数据
     *
     * @param pushMessage
     */
    public static void deleteData(PushMessage pushMessage) {
        try {
            getDao().delete(pushMessage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    public static List<PushMessage> queryAllMessage() {
        List<PushMessage> pushMessages = new ArrayList<>();
        try {
            pushMessages = getDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pushMessages;
    }

    /**
     * 删除所有数据
     */
    public static void deleteAll() {
        try {
            DeleteBuilder<PushMessage, String> builder = getDao().deleteBuilder();
            Where<PushMessage, String> where = builder.where();
            where.isNotNull("id");
            getDao().delete(builder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改消息为已读
     *
     * @param id
     */
    public static void updateStaus(int id) {
        try {
            UpdateBuilder<PushMessage, String> builder = getDao().updateBuilder();
            builder.updateColumnValue("isRead", true).where().eq("id", id);
            getDao().update(builder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
