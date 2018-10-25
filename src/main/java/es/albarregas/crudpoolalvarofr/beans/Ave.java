/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.crudpoolalvarofr.beans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author atomsk
 */
public class Ave implements Serializable{
    String anilla;
    String especie;
    String lugar;
    Date fecha;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-mm");

    public String getAnilla() {
	return anilla;
    }

    public void setAnilla(String anilla) {
	this.anilla = anilla;
    }

    public String getEspecie() {
	return especie;
    }

    public void setEspecie(String especie) {
	this.especie = especie;
    }

    public String getLugar() {
	return lugar;
    }

    public void setLugar(String lugar) {
	this.lugar = lugar;
    }

    public String getFecha() {
	return sdf.format(fecha);
    }

    public void setFecha(String fecha) {
	try {
	    this.fecha = sdf.parse(fecha);
	} catch (ParseException ex) {
	    Logger.getLogger(Ave.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    public String allParamsToString(){
	return getAnilla()+"_"+getEspecie()+"_"+getLugar()+"_"+getFecha();
    }
}
