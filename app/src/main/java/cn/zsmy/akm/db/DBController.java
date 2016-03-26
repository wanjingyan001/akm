package cn.zsmy.akm.db;


import cn.zsmy.akm.home.MyApplication;

/**
 * @author Stay
 * @version create time：Sep 5, 2014 11:37:46 AM
 */
public class DBController {
	private static DBController instance;
	private OrmDBHelper mDBhelper;

	private DBController() {
		mDBhelper = new OrmDBHelper(MyApplication.gContext);
		mDBhelper.getWritableDatabase();
	}

	public static DBController getInstance() {
		if (instance == null) {
			instance = new DBController();
		}
		return instance;
	}
	
	public OrmDBHelper getDB(){
		return mDBhelper;
	}

}
