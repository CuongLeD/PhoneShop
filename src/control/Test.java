package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Phone;
import model.Shop;
import model.service.PhoneServiceImpl;
import model.service.ShopServiceImpl;

@WebServlet(urlPatterns = {"/control/test"})
public class Test extends HttpServlet{
//	private PhoneServiceImpl phoneService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setContentType("text/html");
//		PrintWriter printWriter = resp.getWriter();
		req.setCharacterEncoding("utf-8");
		System.out.println("input: "+ req.getParameter("txtShop"));
		String shopName = req.getParameter("selectShop");
		Shop shop = new ShopServiceImpl().searchElementByName(shopName);
		System.out.println(shop.getShopName());
		
	}

	
	
}
