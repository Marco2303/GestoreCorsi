package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;

public class Model {
	
	
	public void Mode() {
	}
	
	public List<Corso> getCorsiByPeriodo(Integer pd){
		CorsoDAO cdao = new CorsoDAO();
		return cdao.getCorsiByPeriodo(pd);
	}
	
	public Map<Corso, Integer> getIscrittiByPeriodo(Integer pd){
		CorsoDAO cdao = new CorsoDAO();
		return cdao.getIscrittiByPeriodo(pd);

	}
	
}
