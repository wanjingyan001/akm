package cn.zsmy.akm.doctor.log;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.Date;

import cn.zsmy.akm.doctor.chat.bean.Message;

/**
 *
 * @author yutaotao
 *
 * @time 2016/2/26.
 */
public class LogDBHelper extends OrmLiteSqliteOpenHelper {

    public static final String DB_NAME = "TS_LOG_APP";
    public static final int DB_VERSION = 1;

    public LogDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public LogDBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, DB_NAME, factory, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, LogError.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, LogError.class, true);
//			TableUtils.dropTable(connectionSource, UserBean.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
