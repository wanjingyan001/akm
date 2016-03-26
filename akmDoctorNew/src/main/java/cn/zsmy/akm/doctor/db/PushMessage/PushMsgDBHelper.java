package cn.zsmy.akm.doctor.db.PushMessage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/2/25.
 */
public class PushMsgDBHelper extends OrmLiteSqliteOpenHelper {
    public static final String TABLE_NAME = "push_message.db";
    private Dao<PushMessage, String> messages;
    private static PushMsgDBHelper dbHelper;

    private PushMsgDBHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    public PushMsgDBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, PushMessage.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
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
