package br.com.vitafarma.startup;

import java.util.logging.Logger;

import junit.framework.TestCase;
import br.com.vitafarma.domain.Usuario;

public class TestDatabase extends TestCase {
	public static void testDatabase() {
		Logger logger = Logger.getLogger(TestDatabase.class.getName());
		logger.info("Executa find de um usuario na base de dados:");
		Usuario usuario = Usuario.find("vitafarma");
		logger.info("usuario encontrado: " + usuario);
		logger.info("teste executado com sucesso.");
	}
}
