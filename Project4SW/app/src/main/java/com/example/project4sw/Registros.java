package com.example.project4sw;

public class Registros {
    private String tip;
    private String act;
    private String fec;
    private String perReg;
    private String perSol;
    private String est;
    private String hor;
    private String obs;

    public Registros() {

    }

    public Registros(String tip, String act, String fec, String perReg, String perSol, String est, String hor, String obs) {
        this.tip = tip;
        this.act = act;
        this.fec = fec;
        this.perReg = perReg;
        this.perSol = perSol;
        this.est = est;
        this.hor = hor;
        this.obs = obs;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getFec() {
        return fec;
    }

    public void setFec(String fec) {
        this.fec = fec;
    }

    public String getPerReg() {
        return perReg;
    }

    public void setPerReg(String perReg) {
        this.perReg = perReg;
    }

    public String getPerSol() {
        return perSol;
    }

    public void setPerSol(String perSol) {
        this.perSol = perSol;
    }

    public String getEst() {
        return est;
    }

    public void setEst(String est) {
        this.est = est;
    }

    public String getHor() {
        return hor;
    }

    public void setHor(String hor) {
        this.hor = hor;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
