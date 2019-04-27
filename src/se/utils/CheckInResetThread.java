package se.utils;

import se.model.Mapper.StudentMapper;

import java.util.Date;

public class CheckInResetThread extends Thread {
    private Date resetTime;
    private Thread t;
    private String threadName;

    public CheckInResetThread(Date ResetTime, String ThreadName) {
        System.out.println("ResetTime: " + ResetTime.getHours() + ":" + ResetTime.getMinutes());
        resetTime = ResetTime;
        threadName = ThreadName;
    }

    public void start() {
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

    @Override
    public void run() {
        Date now = new Date();
        resetTime.setYear(now.getYear());
        resetTime.setMonth(now.getMonth());
        resetTime.setDate(now.getDate());
        long sleep = resetTime.getTime() - now.getTime();
        if (sleep < 0)
            sleep = now.getTime() - resetTime.getTime();
        try {
            Thread.sleep(sleep);
            while (true) {
                DbUtils<StudentMapper> dbUtils = new DbUtils<>(StudentMapper.class);
                dbUtils.mapper.CheckInReset();
                dbUtils.session.commit();
                dbUtils.session.close();
                System.out.println("Success Reset CheckIn");
                Thread.sleep(24 * 60 * 60 * 1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}