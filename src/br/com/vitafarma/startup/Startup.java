package br.com.vitafarma.startup;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Query;

import br.com.vitafarma.domain.Usuario;
import junit.framework.TestCase;

public class Startup extends TestCase {
	private static Logger logger = null;

	static {
		try {
			Startup.logger = Logger.getLogger(Startup.class.getName());
		} catch (Exception ex) {
			ex.printStackTrace();

			RuntimeException re = new RuntimeException(ex.getMessage());

			re.initCause(ex);

			throw re;
		}
	}

	public Startup() {
		super();
	}

	public void testCriarEntidades() throws Exception {
		try {
			List<Usuario> listUsuario = null;
			Usuario usuario = null;

			Startup.logger.info("CRIAR OBJETO USUARIO");
			usuario = new Usuario();
			Startup.logger.info("usuario: " + usuario);

			Startup.logger.info("EXECUTAR FIND DE UM USUARIO NA BASE DE DADOS");
			TestDatabase.testDatabase();
			Startup.logger.info("find executado com sucesso.");

			Startup.logger.info("CARREGAR LISTA DE USUARIOS DA BASE DE DADOS");
			listUsuario = Usuario.findAll();
			Startup.logger.info("total de usuarios: " + listUsuario.size());
		} catch (Exception ex) {
			Startup.logger.info("ERRO AO EXECUTAR STARTUP CLASS (METODO testCriarEntidades)");
			Startup.logger.info("CAUSE: " + ex.getCause());
			Startup.logger.info("MESSAGE: " + ex.getMessage());
			Startup.logger.info("LOCALIZED MESSAGE: " + ex.getLocalizedMessage());

			ex.printStackTrace();

			throw ex;
		}
	}

	public void testDual() throws Exception {
		try {
			Query q = (new Usuario()).getEntityManager().createNativeQuery("SELECT * FROM DUAL");

			q.getSingleResult();
		} catch (Exception ex) {
			Startup.logger.info("ERRO AO EXECUTAR STARTUP CLASS (METODO testDual)");
			Startup.logger.info("CAUSE: " + ex.getCause());
			Startup.logger.info("MESSAGE: " + ex.getMessage());
			Startup.logger.info("LOCALIZED MESSAGE: " + ex.getLocalizedMessage());

			ex.printStackTrace();

			throw ex;
		}
	}

	public static void main(String[] args) {
		try {
			System.out.println("INICIO STARTUP");

			(new Startup()).testDual();
			(new Startup()).testCriarEntidades();

			System.out.println("FIM STARTUP OK");
		} catch (Throwable ex) {
			System.err.println("ERRO AO EXECUTAR STARTUP CLASS (METODO MAIN)");
			System.err.println("CAUSE: " + ex.getCause());
			System.err.println("MESSAGE: " + ex.getMessage());
			System.err.println("LOCALIZED MESSAGE: " + ex.getLocalizedMessage());

			ex.printStackTrace();

			System.exit(1);
		}

		System.out.println("STARTUP CLASS EXECUTADA COM SUCESSO");
	}
}
