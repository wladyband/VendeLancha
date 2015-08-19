package br.com.vendelancha.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

@ManagedBean
public class InserirImagemBean implements ActionListener{

	@Override
	public void processAction(ActionEvent event) throws AbortProcessingException {
	System.out.println("clicando no botão" + event.getComponent().getId());
		
	}

}
