package cn.zachary.greendaodemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import org.greenrobot.greendao.database.Database;

import cn.zachary.greendaodemo.database.greendao.DaoMaster;
import cn.zachary.greendaodemo.database.greendao.StudentDao;


/**
 * Created by pengpeng on 16/12/16.
 */

public class DbHelper extends DaoMaster.OpenHelper {

    public final static String TAG = "数据库版本升级";


    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }


    /**
     * 升级数据库版本
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        Log.i(TAG, "Upgrading schema from versionGet " + oldVersion + " to " + newVersion);
        //添加字段以下两种方式都可以
        switch (oldVersion) {
            case 1://合并数据库
                MigrationHelper.getInstance().migrate(db, new MyDropOrcreateTable(oldVersion, newVersion), StudentDao.class);
                break;
            case 2://添加字段
                //MigrationHelper.getInstance().addColumn(db, StudentDao.class, StudentDao.Properties.Age);
                break;
        }
    }

    class MyDropOrcreateTable implements MigrationHelper.DropOrcreateTable {

        private int oldVersion;
        private int newVersion;

        MyDropOrcreateTable(int oldVersion, int newVersion) {
            this.oldVersion = oldVersion;
            this.newVersion = newVersion;
        }

        @Override
        public void dropTable(Database db) {
           /* switch (oldVersion){
                case 1:

                    break;
            }*/
            StudentDao.dropTable(db, true);
        }

        @Override
        public void createTable(Database db) {
            /* switch (oldVersion){
                case 1:

                    break;
            }*/
            StudentDao.createTable(db, true);
        }
    }
}
