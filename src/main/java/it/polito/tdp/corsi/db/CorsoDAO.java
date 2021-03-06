package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO {
	
	
	public List<Corso>  getCorsiByPeriodo(Integer periodo){
		
		String sql = "SELECT * FROM corso WHERE pd=?";
		List<Corso> lst = new ArrayList<Corso>();
		
		try {
			Connection conn = DBconnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, periodo);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
				lst.add(c);
			}
				
			rs.close();
			st.close();
			conn.close();
			
		
		}catch(SQLException sqe) {
			throw new RuntimeException(sqe); 
		}
		
		return lst;
	}
	
	public Map<Corso, Integer> getIscrittiByPeriodo(Integer periodo){
		String sql = "SELECT c.codins,c.crediti,c.nome,c.pd, COUNT(*) as tot FROM corso c , iscrizione i "
				+ "WHERE c.codins = i.codins AND c.pd=? "
				+ "GROUP BY c.codins,c.crediti,c.nome,c.pd ORDER BY tot desc";
		Map<Corso, Integer> lst = new HashMap<Corso, Integer>();
		
		try {
			Connection conn = DBconnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, periodo);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
				lst.put(c,rs.getInt("tot"));
			}
				
			rs.close();
			st.close();
			conn.close();
			
		
		}catch(SQLException sqe) {
			throw new RuntimeException(sqe); 
		}
		
		return lst;
	
	}
	
	

}
