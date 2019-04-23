/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/14
 */

package se.Model;

public class Subject {
    private String subjectId;
    private String subjectName;
    private String subjectKind;
    private String userName;
    private String userId;
    private boolean resultSubmitted;
    private String resultUrl;
    private int maxSelectNum;
    private int currSelectNum;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isResultSubmitted() {
        return resultSubmitted;
    }

    public void setResultSubmitted(boolean resultSubmitted) {
        this.resultSubmitted = resultSubmitted;
    }

    public String getResultUrl() {
        return resultUrl;
    }

    public void setResultUrl(String resultUrl) {
        this.resultUrl = resultUrl;
    }

    public int getMaxSelectNum() {
        return maxSelectNum;
    }

    public void setMaxSelectNum(int maxSelectNum) {
        this.maxSelectNum = maxSelectNum;
    }

    public int getCurrSelectNum() {
        return currSelectNum;
    }

    public void setCurrSelectNum(int currSelectNum) {
        this.currSelectNum = currSelectNum;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectKind() {
        return subjectKind;
    }

    public void setSubjectKind(String subjectKind) {
        this.subjectKind = subjectKind;
    }
}
