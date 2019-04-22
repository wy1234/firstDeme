package com.example.zk;

import java.util.concurrent.CountDownLatch;

public class ZookeeperOistrbuteLock implements ZookeeperAbstrackLock {
    private CountDownLatch countDownLatch = null;
    @Override
    void waitLock() {
        // 通过监听已经获得锁
        IzkDataListener izDataListener = new IzkDataListener(){
            public void handeDataDeleted(String path){
                // 唤醒被等待的线程
                if(countDownLatch != null){
                    countDownLatch.countDown();//节点减去1
                }
            }
            public void handleDataChange(String path, Object data) throws Exception{

            }
        };
        // 注册事件
        zkClient.subsctibeDataChanges(PATH, izDataListener);
        if(zkClient.exists(PATH)){
            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 删除监听
        zkClient.unsubscribeDataChanges(PATH, izDataListener);
    }

    @Override
    boolean tryLock() {
        try {
            // 能不能创建节点成功，成功的话返回true
            zkClient.createEphemeral(PATH);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public void unLock(){
        if(zkClient != null){
            zkClient.delete(PATH);
            zkClient.close();
            System.out.print("释放资源。。。。");
        }
    }
}
