package com.group4.coffeeblend;

import java.io.IOException;
import java.lang.reflect.Method;
//import java.sql.Date;
import java.util.Arrays;
import java.util.Date;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.group4.coffeeblend.helpers.Constant;
import com.group4.coffeeblend.helpers.FileUploadUtils;
import com.group4.coffeeblend.model.AccountDetails;
import com.group4.coffeeblend.model.Invoice;
import com.group4.coffeeblend.model.InvoiceStatus;
import com.group4.coffeeblend.model.Product;
import com.group4.coffeeblend.model.ProductStatus;
import com.group4.coffeeblend.model.ProductType;
import com.group4.coffeeblend.model.RoleDetails;
import com.group4.coffeeblend.service.AccountDetailsImpl;
import com.group4.coffeeblend.service.InvoiceService;
import com.group4.coffeeblend.service.InvoiceStatusService;
import com.group4.coffeeblend.service.ProductService;
import com.group4.coffeeblend.service.ProductStatusService;
import com.group4.coffeeblend.service.ProductTypeService;
import com.group4.coffeeblend.service.RoleDetailImpl;

@Controller
public class Appcontroller {
	
	@Autowired
	private RoleDetailImpl roleImpl;
	
	@Autowired
	private AccountDetailsImpl accountDetailImpl;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	@Autowired
	private ProductStatusService productStatusService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private InvoiceStatusService invoiceStatusService;
	
	
//	private List<String> listType = Arrays.asList("Cà phê","Sinh tố","Nước ngọt","Snack");
//	private List<String> listStatus = Arrays.asList("Bình thường","Giảm giá 10%","Giảm giá 20%","Mua 2 tặng 1");

	@RequestMapping("/")
	public String viewIndex() {

		return "ad-index";
	}

	@RequestMapping("/customerList")
	public String viewCustomer() {

		return "ad-customer-list";
	}
	
	//===========================================================================
	//INVOICE HERE

	@RequestMapping("/orderList")
	public String viewOrder(Model model) {
		List<Invoice> listInvoice = invoiceService.getAllInvoice();
		List<InvoiceStatus> listInvoiceStatus = invoiceStatusService.getAllInvoiceStatus();
		model.addAttribute("listInvoice", listInvoice);
		model.addAttribute("listInvoiceStatus", listInvoiceStatus);
		return "ad-order-list";
	}
	
	@RequestMapping(value = "/editInvoice/{id}", method = RequestMethod.GET)
	public String viewInvoiceEdit(Model model, @PathVariable(name = "id")int id) {
		
		Invoice invoice = invoiceService.get(id);
		List<InvoiceStatus> invoiceStatus = invoiceStatusService.getAllInvoiceStatus();
		model.addAttribute("invoice", invoice);
		model.addAttribute("invoiceStatus", invoiceStatus);
		return "ad-order-edit";
	}
	
	@RequestMapping(value = "/saveInvoiceEdit/{id}", method = RequestMethod.POST)
	public String saveInvoiceEdit(Model model,@PathVariable(name = "id") int id,@ModelAttribute("invoice") Invoice invoice
			//@ModelAttribute("invoiceStatus") InvoiceStatus invoiceStatus
			) {
//		System.out.println("invoice getdate: "+invoice.getDay_created());
		invoice.setDay_updated(new Date());
		invoice.setId(id);
		//invoice.setInvoice_status(invoiceStatus.getStatus_name());
		invoiceService.save(invoice);
		return "redirect:/orderList";
	}
	
	

	//====================================================================================================
	//PRODUCT TYPE HERE
	@RequestMapping("/listProductType")
	public String viewProductTypeList(Model model) {
		List<ProductType> listType = productTypeService.getAllProductTypes();
		model.addAttribute("listType", listType);
		return "ad-product-type-list";
	}
	
	@RequestMapping(value = "/addProductType",method = RequestMethod.GET)
	public String viewProductTypeAdd(Model model) {
		
		ProductType type = new ProductType();
		model.addAttribute("type", type);
		return "ad-product-type-add";
	}
	
	@RequestMapping(value = "/addProductType",method = RequestMethod.POST)
	public String saveProductTypeAdd(@ModelAttribute("type") ProductType type) {
		
		productTypeService.save(type);
		
		return "redirect:/listProductType";
	}
	
	
	
	@RequestMapping(value = "/editProductType/{type_id}",method = RequestMethod.GET)
	public String viewProductTypeEdit(Model model,@PathVariable(name = "type_id")int type_id) {
		
		ProductType type = productTypeService.get(type_id);
		
		model.addAttribute("type",type);

		return "ad-product-type-edit";
	}
	

	
	@RequestMapping(value = "/saveProductType/{type_id}", method = RequestMethod.POST)
	public String saveProductType(Model model,@PathVariable(name = "type_id")int type_id,@ModelAttribute("type") ProductType type) {
		
		type.setType_id(type_id);
		productTypeService.save(type);
		
		return "redirect:/listProductType";
	}
	
	@RequestMapping("/deleteProductType/{type_id}")
	public String deleteProductType(@PathVariable(name = "type_id") Integer type_id) {
		productTypeService.delete(type_id);
		return "redirect:/listProductType";
	}
	
	//====================================================================================================
	//PRODUCT HERE

//	@RequestMapping("/searchProduct")
//	public String searchProductList(Model model,@Param("keyword")String keyword) {
//		List<Product> listProduct = productService.getAllProduct(keyword);
//	    List<ProductType> listProductType = productTypeService.getAllProductTypes();
//	    List<ProductStatus> listProductStatus = productStatusService.getAllProductStatus();
//	    model.addAttribute("listProduct", listProduct);
//	    model.addAttribute("keyword",keyword);
//	    model.addAttribute("listProductType", listProductType);
//	    model.addAttribute("listProductStatus", listProductStatus);
//
//		System.out.println("viewHomePage: " + listProduct.get(0).getFullPath());
//	    
//		return "ad-product-list";
//	}
	@RequestMapping("/listProduct")
	public String viewProductList(Model model) {
		String keyw = "";
		return viewPageProductList(model,1,"product_name","asc",keyw);
	}
	@RequestMapping("/page/{pageNum}")
	public String viewPageProductList(Model model,@PathVariable("pageNum") int pageNum
												 ,@Param("sortName") String sortName
												 ,@Param("direction") String direction
												 ,@Param("keyword") String keyword) {
//		if(sortName == null) {
//			sortName = "name";
//		}
//		if(direction == null) {
//			direction ="asc";
//		}
		
		Page<Product> page = productService.getProductPageSort(pageNum-1, sortName, direction, keyword);
		List<Product> listProduct = page.getContent();
		model.addAttribute("currentPage",pageNum);
		model.addAttribute("totalItem",page.getTotalElements());
		model.addAttribute("totalPage", page.getTotalPages());
		
		model.addAttribute("sortName",sortName);
		model.addAttribute("direction",direction);
		model.addAttribute("invertDirection", direction.equals("asc") ? "desc" : "asc");
		model.addAttribute("keyword",keyword);
		model.addAttribute("listProduct",listProduct);
		
		return "ad-product-list";
	}
	
	@RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
	public String editView(Model model,@PathVariable(name = "id")int id) {
		Product product = productService.get(id);
		List<ProductType> productType = productTypeService.getAllProductTypes();
		List<ProductStatus> productStatus = productStatusService.getAllProductStatus();
		
		model.addAttribute("productType", productType);
		model.addAttribute("product", product);
		model.addAttribute("productStatus", productStatus);
		
		return "ad-product-edit";
	}
	@RequestMapping(value = "/save/{id}", method = RequestMethod.POST)
	public String saveProduct(@RequestParam("fileImage") MultipartFile multipartFile,Model model,@PathVariable(name = "id")int id,
			@ModelAttribute("product") Product product, @ModelAttribute("status") ProductStatus status,@ModelAttribute("type") ProductType type) {
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		
		product.setId(id);
		product.setImage(fileName);
		
		product.setType(type.getType_name());
		product.setStatus(status.getStatus_name());
		
		productService.save(product);
//		productStatusService.save(status);
//		productTypeService.save(type);
		
		String uploadDir = Constant.UPLOAD_FOLDER + "/" + product.getId();
		
		try {
			FileUploadUtils.saveFile(uploadDir, fileName, multipartFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/listProduct";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		productService.delete(id);
		return "redirect:/listProduct";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String viewProductAdd(Model model, @ModelAttribute("status") ProductStatus status,@ModelAttribute("type") ProductType type) {
		Product product = new Product();
		List<ProductType> productType = productTypeService.getAllProductTypes();
		List<ProductStatus> productStatus = productStatusService.getAllProductStatus();
		model.addAttribute("productType", productType);
		model.addAttribute("product", product);
		model.addAttribute("productStatus", productStatus);
		return "ad-product-add";
	}
	

	//=========================================================================================
	//ACCOUNT HERE
	
	
	//Sử dụng method Get để đưa model lên
	@RequestMapping(value = "/newStaff", method = RequestMethod.GET)
	public String viewStaffAdd(Model model) {
		AccountDetails accountDetails = new AccountDetails();
		List<RoleDetails> listRole = roleImpl.getAll();
		model.addAttribute("admindetail", accountDetails);
		model.addAttribute("listRole",listRole);
		return "ad-staff-add";
	}
	@GetMapping("/editStaff/{id}")
	public String viewStaffEdit(Model model,@PathVariable(name = "id") int id) {
		AccountDetails accountDetails = accountDetailImpl.getId(id);
		List<RoleDetails> listRole = roleImpl.getAll();
		model.addAttribute("admindetail", accountDetails);
		
		for (RoleDetails roleDetails : listRole) {
			System.out.println("RoleID : "+roleDetails.getId()+"Role Name: "+roleDetails.getName());
		}
		System.out.println("Role Name: "+ accountDetails.getRoleName()+" Role ID: " + accountDetails.getRole_id()+"Admin ID: "+accountDetails.getId());
		model.addAttribute("listRole",listRole);
		return "ad-staff-edit";
	}
	@RequestMapping(value = "/addStaff/{id}",method = RequestMethod.POST)
	public String saveStaff(Model model,@PathVariable("id")int id,@RequestParam("fileImage") MultipartFile multipartFile,@ModelAttribute("admindetail") AccountDetails account) {
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//		System.out.println("Role Name: "+ account.getRoleName() + " Role ID: "+account.getRole_id());
		account.setPicture(fileName);
		
		accountDetailImpl.save(account);
		String uploadDir = Constant.UPLOAD_IMAGE + "/" + account.getId();
		
		try {
			FileUploadUtils.saveFile(uploadDir, fileName, multipartFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/listStaff";
	}
	// Sử dụng method Post để đẩy data vào.
	@RequestMapping(value = "/addStaff",method = RequestMethod.POST)
	public String saveStaffEdit(@RequestParam("fileImage") MultipartFile multipartFile,@ModelAttribute("admindetail") AccountDetails account,@ModelAttribute("role") RoleDetails role) {
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		account.setRole_id(role.getId());
		account.setRoleName(role.getName());
		
		account.setPicture(fileName);
		
		accountDetailImpl.save(account);
		System.out.println("Account: " + account.getId() + "Role: "+role.getId());
		String uploadDir = Constant.UPLOAD_IMAGE + "/" + account.getId();
		try {
			FileUploadUtils.saveFile(uploadDir, fileName, multipartFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/listStaff";
	}
	
	@RequestMapping("/listStaff")
	public String viewStaffList(Model model) {
//		AccountDetails acc = new AccountDetails();
		List<AccountDetails> acc =accountDetailImpl.getList();
		model.addAttribute("admindetail", acc);
		System.out.println("list employee: "+acc);
		return "ad-staff-list";
	}
	@RequestMapping("/deleteStaff/{id}")
	public String deleteStaff(@PathVariable(name = "id") Integer id) {
		accountDetailImpl.delete(id);
		return "redirect:/listStaff";
	} 
	
	
}
