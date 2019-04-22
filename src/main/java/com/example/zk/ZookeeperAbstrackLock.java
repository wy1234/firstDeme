package com.example.zk;


public class ZookeeperAbstrackLock implements Lock {
    // zk连接地址
    private static final String CONNECTPATH = "127.0.01:2181";
    // 创建zk连接
    protected ZkClient zkClient = new ZkClient(CONNECTPATH);
    protected static final String PATH = "/lock";
    
    protected static final String PATH2 = "/lock2";
    
    @Override
    public void getLock() {
        if(tryLock()){
            System.out.print("....获取lock锁的资源....");
        } else {
            // 等待
            waitLock();
            // 重新获得锁
            getLock();
        }
        
    }

    abstract void waitLock();

    abstract boolean tryLock();

    @Override
    public void unLock() {

    }
}
