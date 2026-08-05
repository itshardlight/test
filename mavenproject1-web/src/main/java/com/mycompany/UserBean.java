package com.mycompany;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import auth.UserService;

@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean {

    private String UName;
    private String UPass;
    private String CPass;
    private String URole;

    @EJB
    private UserService service;

    public String register() {
        if (UPass.equals(CPass)) {
            String message = service.save(UName, UPass, URole);
            PrimeFaces.current().executeScript("alert('" + message + "');");
            return "home.xhtml?faces-redirect=true";
        } else {
            PrimeFaces.current().executeScript("alert('Password do not match');");
        }
        return null;
    }

    public String auth() {
        String message = service.auth(UName, UPass, URole);
        if (message.equals("User Found")) {
            PrimeFaces.current().executeScript("alert('" + message + "');");

            return "home.xhtml?faces-redirect=true";
        } else {
            PrimeFaces.current().executeScript("alert('" + message + "');");
            return null;
        }
    }

    //getter and setter
    public String getURole() {
        return URole;
    }

    public void setURole(String URole) {
        this.URole = URole;
    }

    public String getUName() {
        return UName;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public String getUPass() {
        return UPass;
    }

    public void setUPass(String UPass) {
        this.UPass = UPass;
    }

    public String getCPass() {
        return CPass;
    }

    public void setCPass(String CPass) {
        this.CPass = CPass;
    }

    public UserService getService() {
        return service;
    }

    public void setService(UserService service) {
        this.service = service;
    }

}
