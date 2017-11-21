package br.com.upl.sfservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.upl.sfservice.model.Estoque;

public class EstoqueDao {
		
	public List<Estoque> listaEstoque () {
	

		Connection connection = null;
		try {
			connection = new DaoFactory().getConnection();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			List<Estoque> estoque = new ArrayList<Estoque>();
			PreparedStatement stmt = connection.prepareStatement("select * from ZZ_V_SF_TEMP_ESTOQUE");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {				
				estoque.add(populaEstoque(rs));
			}
			
			rs.close();
			stmt.close();
			connection.close();
			
			return estoque;
			
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}
	
	private Estoque populaEstoque(ResultSet rs) throws SQLException {
		Estoque estoque = new Estoque();
		
		estoque.setMaterial				(rs.getString("Material"));
		estoque.setMaterialDes			(rs.getString("MaterialDES"));
		estoque.setUnidade				(rs.getString("Unidade"));
		estoque.setFilial				(rs.getString("Filial"));
		estoque.setTipoMaterial			(rs.getString("TipoMaterial"));
		estoque.setDeposito				(rs.getString("Deposito"));
		estoque.setLote					(rs.getString("Lote"));
		estoque.setEstoqueDisponivel	(rs.getString("Estoque_Disponivel"));
		estoque.setEstoqueQualidade		(rs.getString("Estoque_Qualidade"));
		estoque.setEstoqueTransito		(rs.getString("Estoque_Transito"));
		estoque.setEstoqueBloqueado		(rs.getString("Estoque_Bloqueado"));
		estoque.setBase					(rs.getString("BASE"));
				
		return estoque;
	}
}
