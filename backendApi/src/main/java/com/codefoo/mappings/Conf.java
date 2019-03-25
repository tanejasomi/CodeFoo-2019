package com.codefoo.mappings;

public class Conf {
    private String inputFile;
    private String connectionUrl;
    private String userName;
    private String password;
    private String driverClassName;
    private Integer commitSize;

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public Integer getCommitSize() {
        return commitSize;
    }

    public void setCommitSize(Integer commitSize) {
        this.commitSize = commitSize;
    }

    @Override
    public String toString() {
        return "Conf{" +
                "inputFile='" + inputFile + '\'' +
                ", connectionUrl='" + connectionUrl + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", driverClassName='" + driverClassName + '\'' +
                ", commitSize=" + commitSize +
                '}';
    }
}
