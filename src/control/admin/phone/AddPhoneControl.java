package control.admin.phone;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.Category;
import model.ImageLink;
import model.Phone;
import model.Shop;
import model.Vendor;
import model.service.CategoryServiceImpl;
import model.service.PhoneServiceImpl;
import model.service.ShopServiceImpl;
import model.service.VendorServiceImpl;

@WebServlet(urlPatterns = {"/phone/add-phone"})
public class AddPhoneControl extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Shop> shops;
	private List<Category> categories;
	private List<Vendor> vendors;
	private PhoneServiceImpl phoneService;
	@Override
	public void init() throws ServletException {
//		shops = new ArrayList<Shop>();
//		categories = new ArrayList<Category>();
//		vendors = new ArrayList<Vendor>();
		phoneService = new PhoneServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.categories = new CategoryServiceImpl().getElements(0, new CategoryServiceImpl().amountRows());
		this.vendors = new VendorServiceImpl().getElements(0, new VendorServiceImpl().amountRows());
		this.shops = new ShopServiceImpl().getElements(0, new ShopServiceImpl().amountRows());
		req.setAttribute("categories", this.categories);
		req.setAttribute("vendors", this.vendors);
		req.setAttribute("shops", this.shops);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/phone/add-phone.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Phone phone = getPhone(req);
		boolean insertResult = phoneService.insertElement(phone);
		resp.setContentType("text/html");
		PrintWriter printWriter = resp.getWriter();
		if(insertResult)
		{
			printWriter.println("<script type=\"text/javascript\">");
			printWriter.println("alert('Insert succed')");
			printWriter.println("</script>");
			resp.sendRedirect("/PhoneShop/phone/list-phone");
		}else
		{
			printWriter.println("<script type=\"text/javascript\">");
			printWriter.println("alert('Insert failed')");
			printWriter.println("</script>");
			resp.sendRedirect("/PhoneShop/phone/list-phone");
		}
		
		
	}

	private  Phone getPhone(HttpServletRequest req) throws UnsupportedEncodingException {
		Phone phone = new Phone();
		List<ImageLink> imageLinks = new ArrayList<ImageLink>();
		String url = "/home/cl/eclipse-workspace/PhoneShop/WebContent/";
		File file = new File(url + "image");
		if(!file.exists())
			file.mkdir();
		
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		diskFileItemFactory.setRepository(new File(url));
		ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
		try {
			List<FileItem> fileItems = fileUpload.parseRequest(req);
			for(FileItem fileItem : fileItems)
			{
				if(!fileItem.isFormField())
				{
					if(fileItem.getSize() > 0)
					{
						file = new File(url+ "image/" + fileItem.getName());
						if(file.exists())
							file.delete();
						fileItem.write(file);
						
						ImageLink imageLink = new ImageLink();
						if(fileItem.getFieldName().equals("avatar"))
						{
							imageLink.setAvatar(true);
						}else
						{
							imageLink.setAvatar(false);
						}
						imageLink.setImageLink( "/PhoneShop/image/" +fileItem.getName());
						imageLinks.add(imageLink);
					}
				}else
				{
					if(fileItem.getFieldName().equals("txtName"))
						phone.setPhoneName(fileItem.getString("utf-8"));
					if(fileItem.getFieldName().equals("txtQuantity"))
						phone.setQuantity(Integer.valueOf(fileItem.getString().trim()));
					if(fileItem.getFieldName().equals("txtPrice"))
						phone.setPrice(Integer.valueOf(fileItem.getString().trim()));
					if(fileItem.getFieldName().equals("txtDescription"))
						phone.setDescription(fileItem.getString("utf-8"));
					if(fileItem.getFieldName().equals("selectVendor"))
						phone.setVendorId(new VendorServiceImpl().searchElementByName(fileItem.getString("utf-8")).getVendorId());
					if(fileItem.getFieldName().equals("selectShop"))
					{
						phone.setShopName(fileItem.getString("utf-8"));
						phone.setShopId(new ShopServiceImpl().searchElementByName(fileItem.getString("utf-8")).getShopId());
					}
					if(fileItem.getFieldName().equals("selectCategory"))
					{
						phone.setCategoryId(new CategoryServiceImpl().searchElementByName(fileItem.getString("utf-8")).getCategoryId());
					}
				}
			}
			phone.setImageLinks(imageLinks);
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return phone;
	}
	

}
