package cn.zachary.greendaodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import cn.zachary.greendaodemo.database.DbManager;
import cn.zachary.greendaodemo.database.table.PersonInfo;
import cn.zachary.greendaodemo.database.table.Student;

public class MainActivity extends AppCompatActivity {

    private Button Add,Delete,Update,Search;
    private DbManager mDbManager;
    private PersonInfo personInfo1, personInfo2, personInfo3, personInfo4;
    private Student student;
    private TextView dataArea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbManager = DbManager.getInstance(MainActivity.this);
        initView();
        Envent();
        similateData();
    }

    private void similateData() {
        personInfo1 = new PersonInfo(null,"001","张三","男");
        personInfo2 = new PersonInfo(null,"002","李四","女");
        personInfo3 = new PersonInfo(null,"003","王朝","男");
        personInfo4 = new PersonInfo(null,"004","马汉","女");

        //要添加自增id,不然相同的会重复添加不会替换
        student = new Student(null,"波波","女","厦门");
    }

    private void Envent() {

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add
                mDbManager.insertOrReplace(personInfo1);

                mDbManager.insertOrReplace(personInfo2);

                mDbManager.insertOrReplace(personInfo3);

                mDbManager.insertOrReplace(personInfo4);

                mDbManager.insertOrReplaceStudent(student);

                showDataList();
            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Delete
                mDbManager.delete("王朝");

                showDataList();
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Update
                mDbManager.update(personInfo1);

                showDataList();
            }
        });

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Search
                showDataList();
            }
        });
    }

    private void showDataList() {
        StringBuilder sb = new StringBuilder();
        List<PersonInfo> personInfos = mDbManager.searchAll();
        List<Student> students = mDbManager.searchStudents();
        for(PersonInfo personInfo : personInfos){
            // dataArea.setText("id:"+p);
            sb.append("id:").append(personInfo.getId())
                    .append("perNo:").append(personInfo.getPerNo())
                    .append("name:").append(personInfo.getName())
                    .append("sex:").append(personInfo.getSex())
                    .append("\n");
        }
        for (Student student:students){
            sb.append("name:").append(student.getName());
        }
        dataArea.setText(sb.toString());
    }

    private void initView() {

        Add = findViewById(R.id.Add);

        Delete = findViewById(R.id.Delete);

        Update = findViewById(R.id.Update);

        Search = findViewById(R.id.Search);

        dataArea= findViewById(R.id.tips);

    }
}
