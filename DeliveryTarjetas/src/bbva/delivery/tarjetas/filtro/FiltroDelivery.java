package bbva.delivery.tarjetas.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FiltroDelivery implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy");
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
//		System.out.println("doFilter");
		chain.doFilter(req, res);			
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init");
	}

}
