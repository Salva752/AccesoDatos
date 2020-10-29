package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.*;
import view.*;

public class GestionEventos {

	private GestionDatos model;
	private LaunchView view;
	private ActionListener actionListener_comparar, actionListener_buscar;

	public GestionEventos(GestionDatos model, LaunchView view) {
		this.model = model;
		this.view = view;
	}

	public void contol() {
		actionListener_comparar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_compararContenido();
			}
		};
		view.getComparar().addActionListener(actionListener_comparar);

		actionListener_buscar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_buscarPalabra();
			}
		};
		view.getBuscar().addActionListener(actionListener_buscar);
	}

	private int call_compararContenido() {
		try{
			if(GestionDatos.compararContenido(view.getFichero1().getText(), view.getFichero2().getText())) {
				view.getTextArea().setText("Los dos textos son iguales");
			}else view.getTextArea().setText("Los dos textos son diferentes");
		}catch (Exception e) {
			if(view.getFichero1().getText().length() == 0 || view.getFichero2().getText().length() == 0) {
				view.showError("ERROR: Uno de los dos ficheros no ha sido definido");
				view.getTextArea().setText("");
			}else{
				view.showError("ERROR: el fichero no existe / es un directorio");
				view.getTextArea().setText("");
			}
		}
		return 1;
	}

	private int call_buscarPalabra() {
		try {
			if(GestionDatos.buscarPalabra(view.getFichero1().getText(), view.getPalabra().getText(), view.getPrimera().isSelected()) == -1) {
				view.getTextArea().setText("La palabra '"+view.getPalabra().getText()+"' no esta en el documento");
			}else view.getTextArea().setText("La palabra '"+view.getPalabra().getText()+"' esta en la linea nº "+GestionDatos.buscarPalabra(view.getFichero1().getText(), view.getPalabra().getText(), view.getPrimera().isSelected()));
		} catch (Exception e) {
			if(view.getFichero1().getText().length() == 0 || view.getPalabra().getText().length() == 0) {
				view.showError("ERROR: Uno de los dos campos no ha sido definido");
				view.getTextArea().setText("");
			}else{
				view.showError("ERROR: el fichero no existe / es un directorio");
				view.getTextArea().setText("");
			}
		}
		return 1;
	}

}
