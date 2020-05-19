/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perzistencija;

import java.io.Serializable;

/**
 *
 * @author jusuf
 */
public class Zaposleni implements Serializable{
    
    private String ime;
    private int godine;
    private String adresa;
    private double dohodak;
    
    public String getIme() {
        return ime;
    }
    public int getGodine() {
        return godine;
    }
    public String getAdresa() {
        return adresa;
    }
    public double getDohodak() {
        return dohodak;
    }
    
    public void setIme(String ime) {
        this.ime = ime;
    }
    public void setGodine(int godine) {
        this.godine = godine;
    }
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
    public void setDohodak(double dohodak) {
        this.dohodak = dohodak;
    }
    
    public Zaposleni(String ime, int godine, String adresa, double dohodak) {
        this.ime = ime;
        this.godine = godine;
        this.adresa = adresa;
        this.dohodak = dohodak;
    }
    public Zaposleni() {
        
    }
    
    @Override
    public String toString() {
        return ime + " - " + godine + " - " + adresa + " - " + dohodak + "\n";
    }
}
