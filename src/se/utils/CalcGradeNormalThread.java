/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/27
 */

package se.utils;

import se.model.Mapper.GradeMapper;

import java.util.Date;

public class CalcGradeNormalThread extends Thread {
    private Date calcTime;
    private Thread t;
    private String threadName;

    public CalcGradeNormalThread(Date CalcTime, String ThreadName) {
        System.out.println("ResetTime: " + (CalcTime.getMonth() + 1) + "-" + CalcTime.getDate());
        calcTime = CalcTime;
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
        long sleep = calcTime.getTime() - now.getTime();
        try {
            Thread.sleep(sleep);
            DbUtils<GradeMapper> dbUtils = new DbUtils<>(GradeMapper.class);
            dbUtils.mapper.CalcGradeNormal();
            dbUtils.session.commit();
            dbUtils.session.close();
            System.out.println("Success calc normal grade");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
