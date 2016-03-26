package cn.zsmy.akm.doctor.log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * 崩溃日志数据库操作类
 * @author  yutaotao
 * @time 2016/2/26.
 */
public class LogController {
    public static Dao<LogError, String> getDao() throws SQLException {
        return LogDBController.getInstance().getDB().getDao(LogError.class);
    }

    /**
     * 往数据库添加数据
     * @param logError  崩溃信息
     */
    public static void addOrUpdate(LogError logError) {
        try {
            getDao().createOrUpdate(logError);
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }

    /**
     * 删除数据
     * @param logError 崩溃信息
     */
    public static void delete(LogError logError) {
        try {
            getDao().delete(logError);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
