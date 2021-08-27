package cn.zachary.greendaodemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

import java.util.List;

import cn.zachary.greendaodemo.database.greendao.DaoMaster;
import cn.zachary.greendaodemo.database.greendao.DaoSession;
import cn.zachary.greendaodemo.database.greendao.PersonInfoDao;
import cn.zachary.greendaodemo.database.greendao.StudentDao;
import cn.zachary.greendaodemo.database.table.PersonInfo;
import cn.zachary.greendaodemo.database.table.Student;

public class DbManager {
    /**
     * Helper
     */
    private DbHelper mHelper;//获取Helper对象
    /**
     * 数据库
     */
    private SQLiteDatabase db;
    /**
     * DaoMaster
     */
    private DaoMaster mDaoMaster;
    /**
     * DaoSession
     */
    private DaoSession mDaoSession;
    /**
     * 上下文
     */
    private Context context;
    /**
     * 添加表直接新增Dao
     */
    private PersonInfoDao personInfoDao;
    private StudentDao studentDao;

    private static DbManager mDbManager;

    /**
     * 获取单例
     */
    public static DbManager getInstance(Context context){
        if(mDbManager == null){
            synchronized (DbManager.class){
                if(mDbManager == null){
                    mDbManager = new DbManager(context);
                }
            }
        }
        return mDbManager;
    }
    /**
     * 初始化
     * @param context
     */
    public DbManager(Context context) {
        this.context = context;
        mHelper = new DbHelper(context,"person.db", null);
        mDaoMaster = new DaoMaster(getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
        personInfoDao = mDaoSession.getPersonInfoDao();
        studentDao = mDaoSession.getStudentDao();
    }
    /**
     * 获取可读数据库
     */
    private Database getReadableDatabase(){
        if(mHelper == null){
            mHelper = new DbHelper(context,"person.db",null);
        }
        Database db =mHelper.getEncryptedReadableDb("zhoulifeng");
        return db;
    }

    /**
     * 获取可写数据库
     * @return
     */
    private Database getWritableDatabase(){
        if(mHelper == null){
            mHelper =new DbHelper(context,"person.db",null);
        }
        //SQLiteDatabase db = mHelper.getWritableDatabase();
        Database db = mHelper.getEncryptedWritableDb("zhoulifeng");
        return db;
    }

    public void insertOrReplaceStudent(Student student){
        studentDao.insertOrReplace(student);
    }

    /**
     * 会自动判定是插入还是替换
     * @param personInfo
     */
    public void insertOrReplace(PersonInfo personInfo){
        personInfoDao.insertOrReplace(personInfo);
    }
    /**插入一条记录，表里面要没有与之相同的记录
     *
     * @param personInfo
     */
    public long insert(PersonInfo personInfo){
        return  personInfoDao.insert(personInfo);
    }

    /**
     * 更新数据
     * @param personInfo
     */
    public void update(PersonInfo personInfo){
        PersonInfo mOldPersonInfo = personInfoDao.queryBuilder().where(PersonInfoDao.Properties.Id.eq(personInfo.getId())).build().unique();//拿到之前的记录
        if(mOldPersonInfo !=null){
            mOldPersonInfo.setName("zhangsan");
            personInfoDao.update(mOldPersonInfo);
        }
    }
    /**
     * 按条件查询数据
     */
    public List<PersonInfo> searchByWhere(String wherecluse){
        List<PersonInfo> personInfos = (List<PersonInfo>) personInfoDao.queryBuilder().where(PersonInfoDao.Properties.Name.eq(wherecluse)).build().unique();
        return personInfos;
    }
    /**
     * 查询所有数据
     */
    public List<PersonInfo> searchAll(){
        List<PersonInfo> personInfos =personInfoDao.queryBuilder().list();
        return personInfos;
    }
    /**
     * 删除数据
     */
    public void delete(String wherecluse){
        personInfoDao.queryBuilder().where(PersonInfoDao.Properties.Name.eq(wherecluse)).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    public List<Student> searchStudents(){
        List<Student> students =studentDao.queryBuilder().list();
        return students;
    }
}