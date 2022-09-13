/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.utilitarios.utils;

import java.io.Serializable;

/**
 *
 * @author gdb
 */
public class MailConfigDto implements Serializable {

    private String from;
    private String host;
    private String port;
    private String tls;
    private String auth;
    private String user;
    private String pwd;
    private String active;
    private String isGmail;

    private String emailDebug;

    public MailConfigDto(String username, String password, String hostname, Integer port, String useTLS, String from, String emailDebug) {
        this.user = username;
        this.pwd = password;
        this.host = hostname;
        this.port = port.toString();
        this.tls = useTLS;
        this.from = from;
        this.emailDebug = emailDebug;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getTls() {
        return tls;
    }

    public void setTls(String tls) {
        this.tls = tls;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Boolean getIsGmail() {
        return isGmail.equalsIgnoreCase("true");
    }

    public void setIsGmail(String isGmail) {
        this.isGmail = isGmail;
    }

    public String getEmailDebug() {
        return emailDebug;
    }

    public void setEmailDebug(String emailDebug) {
        this.emailDebug = emailDebug;
    }
}