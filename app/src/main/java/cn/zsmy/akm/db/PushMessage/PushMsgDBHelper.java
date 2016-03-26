package cn.zsmy.akm.db.PushMessage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import cn.zsmy.akm.chat.utils.Trace;

/**
 * Created by Administrator on 2016/2/25.
 */
public class PushMsgDBHelper extends OrmLiteSqliteOpenHelper {
    public static final String TABLE_NAME = "push_messages.db";
    private Dao<PushMessage, String> messages;
    private static PushMsgDBHelper dbHelper;
    public static final int DATABASE_VERSION = 3;

    private PushMsgDBHelper(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }


    public static synchronized PushMsgDBHelper getDbHelper(Context context) {
        if (dbHelper == null) {
            synchronized (PushMsgDBHelper.class) {
                if (dbHelper == null) {
                    dbHelper = new PushMsgDBHelper(context);
                }
            }
        }
        return dbHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, PushMessage.class);
            Trace.d("数据库创建成功");
        } catch (SQLException e) {
            Trace.d("数据库创建失败");
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, PushMessage.class, true);
            onCreate(sqLiteDatabase, connectionSource);
            Trace.d("更新数据库成功");
        } catch (SQLException e) {
            Trace.d("更新数据库失败");
            e.printStackTrace();
        }
    }

    public Dao<PushMessage, String> getMessages() throws SQLException {
        if (messages == null) {
            messages = getDao(PushMessage.class);
        }
        return messages;
    }

    @Override
    public void close() {
        super.close();
        messages = null;
    }
}
