package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.MensagemDAO;
import model.vo.MensagemVO;

public class MensagemController {

	MensagemDAO mensagemDAO = new MensagemDAO();
	
	public ArrayList<MensagemVO> buscarMensagensPorDestinatario(int idUsuario) {
		try {
			return (ArrayList<MensagemVO>) mensagemDAO.buscarMensagensPorDestinatario(idUsuario);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	


}
