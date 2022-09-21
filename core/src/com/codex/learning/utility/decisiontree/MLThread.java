package com.codex.learning.utility.decisiontree;

public class MLThread extends Thread{
    public void run() {
        System.out.println("Thread name: " + Thread.currentThread().getName());
        System.out.println("Check if its DaemonThread: "
                + Thread.currentThread().isDaemon());
    }
}
