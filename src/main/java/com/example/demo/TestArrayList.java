package com.example.demo;

import java.beans.Transient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestArrayList {
    private Lock lock = new ReentrantLock();
    public  static void main(String[] args) throws IOException {
        Student student1= new Student("zhangsan", 20, 1000l);
        Student student2= new Student("lisi", 20, 10002);
        Student student3= new Student("wangwu", 20, 10003);
        Student student4= new Student("zhangsan", 20, 1000l);

        List<Student> listOne= new ArrayList<Student>();
        listOne.add(student1);
        listOne.add(student2);
        listOne.add(student3);
        listOne.add(student4);


        Map map1 = new HashMap();
        map1.put("key1","abc");
        map1.put("key2","def");
        Map map2 = new HashMap();
        map2.put("key1","abc");
        map2.put("key4","def");
        map2.putAll(map1);
        map2.entrySet().forEach(a -> System.out.println(a));





       /* Set<Student> newList = new HashSet<Student>();
        for (Student list: listOne) {
            Student student = new Student(list.getName(),list.getAge(),list.getNumber());
            newList.add(student);
        }*/

       Map<Long,Student> studentMap= new HashMap<Long,Student>() ;
        listOne.forEach( stu->{
            studentMap.put(stu.getNumber(),stu);
        });

        List<Student> listTwo= new ArrayList<Student>();

        for (Map.Entry<Long, Student> entry : studentMap.entrySet()) {
            listTwo.add(entry.getValue());
        }

        System.out.println("&&&&&&&"+listTwo);
    }
}
