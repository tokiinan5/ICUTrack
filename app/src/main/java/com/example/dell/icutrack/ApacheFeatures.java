package com.example.dell.icutrack;

public class ApacheFeatures {
    private int Temperature;
    private int BloodPressure;
    private int HeartRate;
    private int RespiratoryRate;
    private int PaO2;
    private int Ph;
    private int Sodium;
    private int Potassium;
    private int Creatinine;
    private int Age;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    private String result;

    public int getTemperature() {
        return Temperature;
    }

    public void setTemperature(int temperature) {
        Temperature = temperature;
    }

    public int getBloodPressure() {
        return BloodPressure;
    }

    public void setBloodPressure(int bloodPressure) {
        BloodPressure = bloodPressure;
    }

    public int getHeartRate() {
        return HeartRate;
    }

    public void setHeartRate(int heartRate) {
        HeartRate = heartRate;
    }

    public int getRespiratoryRate() {
        return RespiratoryRate;
    }

    public void setRespiratoryRate(int respiratoryRate) {
        RespiratoryRate = respiratoryRate;
    }

    public int getPaO2() {
        return PaO2;
    }

    public void setPaO2(int paO2) {
        PaO2 = paO2;
    }

    public int getPh() {
        return Ph;
    }

    public void setPh(int ph) {
        Ph = ph;
    }

    public int getSodium() {
        return Sodium;
    }

    public void setSodium(int sodium) {
        Sodium = sodium;
    }

    public int getPotassium() {
        return Potassium;
    }

    public void setPotassium(int potassium) {
        Potassium = potassium;
    }

    public int getCreatinine() {
        return Creatinine;
    }

    public void setCreatinine(int creatinine) {
        Creatinine = creatinine;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getHematocrit() {
        return Hematocrit;
    }

    public void setHematocrit(int hematocrit) {
        Hematocrit = hematocrit;
    }

    public int getWBc() {
        return WBc;
    }

    public void setWBc(int WBc) {
        this.WBc = WBc;
    }

    public int getComa() {
        return Coma;
    }

    public void setComa(int coma) {
        Coma = coma;
    }

    public String getCriticalDesease() {
        return CriticalDesease;
    }

    public void setCriticalDesease(String criticalDesease) {
        CriticalDesease = criticalDesease;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private int Hematocrit;
    private int WBc;
    private int Coma;
   private String CriticalDesease, date;
}
