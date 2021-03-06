package cn.zachary.greendaodemo.database.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import cn.zachary.greendaodemo.database.table.PersonInfo;
import cn.zachary.greendaodemo.database.table.Student;

import cn.zachary.greendaodemo.database.greendao.PersonInfoDao;
import cn.zachary.greendaodemo.database.greendao.StudentDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig personInfoDaoConfig;
    private final DaoConfig studentDaoConfig;

    private final PersonInfoDao personInfoDao;
    private final StudentDao studentDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        personInfoDaoConfig = daoConfigMap.get(PersonInfoDao.class).clone();
        personInfoDaoConfig.initIdentityScope(type);

        studentDaoConfig = daoConfigMap.get(StudentDao.class).clone();
        studentDaoConfig.initIdentityScope(type);

        personInfoDao = new PersonInfoDao(personInfoDaoConfig, this);
        studentDao = new StudentDao(studentDaoConfig, this);

        registerDao(PersonInfo.class, personInfoDao);
        registerDao(Student.class, studentDao);
    }
    
    public void clear() {
        personInfoDaoConfig.clearIdentityScope();
        studentDaoConfig.clearIdentityScope();
    }

    public PersonInfoDao getPersonInfoDao() {
        return personInfoDao;
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

}
