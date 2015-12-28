package th.ac.kmutt.dsd.train.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

public class InitLog4JServlet extends HttpServlet{
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		String configFile = config.getInitParameter("configFile");
		PropertyConfigurator.configure(getClass().getResource(configFile));

	}
}
