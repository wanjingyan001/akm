package cn.zsmy.akm.doctor.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import cn.zsmy.akm.doctor.chat.bean.Message;


/**
 *数据库工具类
 */
public class OrmDBHelper extends OrmLiteSqliteOpenHelper {

	public static final String DB_NAME = "akmSkin";
	public static final int DB_VERSION = 1;

	public OrmDBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
		super(context, DB_NAME, factory, DB_VERSION);
		
	}

	public OrmDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
		try {
//			TableUtils.createTable(connectionSource, UserBean.class);
			TableUtils.createTable(connectionSource, Message.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int arg2, int arg3) {
		try {
			TableUtils.dropTable(connectionSource, Message.class, true);
//			TableUtils.dropTable(connectionSource, UserBean.class, true);
			onCreate(database, connectionSource);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
