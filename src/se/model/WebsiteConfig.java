/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/25
 */

package se.model;

import javafx.util.converter.DateStringConverter;
import se.model.Mapper.WebConfigMapper;
import se.utils.DbUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebsiteConfig {
    private Date pubSubjectStartTime;
    private Date pubSubjectEndTime;
    private Date selectSubjectStartTime;
    private Date selectSubjectEndTime;
    private Date uploadResultStartTime;
    private Date uploadResultEndTime;
    private Date pubGradeStartTime;
    private Date pubGradeEndTime;

    private Date checkInTime;
    private Date checkOutTime;
    private Date checkInResetTime;

    public void UpdateConfig() {
        DbUtils<WebConfigMapper> dbUtils = new DbUtils<>(WebConfigMapper.class);
        dbUtils.mapper.UpdateConfig(this);
        dbUtils.session.commit();
        dbUtils.session.close();
    }

    // setter with Date
    public void setPubSubjectStartTime(Date pubSubjectStartTime) {
        this.pubSubjectStartTime = pubSubjectStartTime;
    }

    public void setPubSubjectEndTime(Date pubSubjectEndTime) {
        this.pubSubjectEndTime = pubSubjectEndTime;
    }

    public void setSelectSubjectStartTime(Date selectSubjectStartTime) {
        this.selectSubjectStartTime = selectSubjectStartTime;
    }

    public void setSelectSubjectEndTime(Date selectSubjectEndTime) {
        this.selectSubjectEndTime = selectSubjectEndTime;
    }

    public void setUploadResultStartTime(Date uploadResultStartTime) {
        this.uploadResultStartTime = uploadResultStartTime;
    }

    public void setUploadResultEndTime(Date uploadResultEndTime) {
        this.uploadResultEndTime = uploadResultEndTime;
    }

    public void setPubGradeStartTime(Date pubGradeStartTime) {
        this.pubGradeStartTime = pubGradeStartTime;
    }

    public void setPubGradeEndTime(Date pubGradeEndTime) {
        this.pubGradeEndTime = pubGradeEndTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public void setCheckInResetTime(Date checkInResetTime) {
        this.checkInResetTime = checkInResetTime;
    }

    public Date getPubSubjectStartTime() {
        return pubSubjectStartTime;
    }

    // setter for String
    public void setPubSubjectStartTime(String pubSubjectStartTime) {
        this.pubSubjectStartTime = (new DateStringConverter().fromString(pubSubjectStartTime));
    }

    public Date getPubSubjectEndTime() {
        return pubSubjectEndTime;
    }

    public void setPubSubjectEndTime(String pubSubjectEndTime) {
        this.pubSubjectEndTime = new DateStringConverter().fromString(pubSubjectEndTime);
    }

    public Date getSelectSubjectStartTime() {
        return selectSubjectStartTime;
    }

    public void setSelectSubjectStartTime(String selectSubjectStartTime) {
        this.selectSubjectStartTime = new DateStringConverter().fromString(selectSubjectStartTime);
    }

    public Date getSelectSubjectEndTime() {
        return selectSubjectEndTime;
    }

    public void setSelectSubjectEndTime(String selectSubjectEndTime) {
        this.selectSubjectEndTime = new DateStringConverter().fromString(selectSubjectEndTime);
    }

    public Date getUploadResultStartTime() {
        return uploadResultStartTime;
    }

    public void setUploadResultStartTime(String uploadResultStartTime) {
        this.uploadResultStartTime = new DateStringConverter().fromString(uploadResultStartTime);
    }

    public Date getUploadResultEndTime() {
        return uploadResultEndTime;
    }

    public void setUploadResultEndTime(String uploadResultEndTime) {
        this.uploadResultEndTime = new DateStringConverter().fromString(uploadResultEndTime);
    }

    public Date getPubGradeStartTime() {
        return pubGradeStartTime;
    }

    public void setPubGradeStartTime(String pubGradeStartTime) {
        this.pubGradeStartTime = new DateStringConverter().fromString(pubGradeStartTime);
    }

    public Date getPubGradeEndTime() {
        return pubGradeEndTime;
    }

    public void setPubGradeEndTime(String pubGradeEndTime) {
        this.pubGradeEndTime = new DateStringConverter().fromString(pubGradeEndTime);
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) throws ParseException {
        this.checkInTime = new SimpleDateFormat("HH:mm").parse(checkInTime);
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) throws ParseException {
        this.checkOutTime = new SimpleDateFormat("HH:mm").parse(checkOutTime);
    }

    public Date getCheckInResetTime() {
        return checkInResetTime;
    }

    public void setCheckInResetTime(String checkInResetTime) throws ParseException {
        this.checkInResetTime = new SimpleDateFormat("HH:mm").parse(checkInResetTime);
    }
}
