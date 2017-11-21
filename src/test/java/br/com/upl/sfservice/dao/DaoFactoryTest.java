package br.com.upl.sfservice.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.upl.sfservice.model.Estoque;

public class DaoFactoryTest {
	/*
	private EstoqueDao estoqueDao;
	private DaoFactory daoFactory = new DaoFactory();
		
	@Before
	public void before() {
		try {
			estoqueDao = new EstoqueDao(daoFactory);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void after() {
		daoFactory.closeConnection();
	}
	
	@Test
	public void estoqueShouldBeGreaterThanZero() {
		
		List<Estoque> estoque = new ArrayList<Estoque>();
		estoque = estoqueDao.listaEstoque();
		
		assertTrue(estoque.size() > 0);
	}
	*/
}
