package com.group4.coffeeblend.client.coffeeblendSpringSuite;



import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.group4.coffeeblend.client.coffeeblendSpringSuite.helpers.ShopCartUtil;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.helpers.UserLogInUtil;
//import com.group4.coffeeblend.client.coffeeblendSpringSuite.helpers.ShowMenuUtil;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.CartInfo;
//import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.MenuInfo;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.Product;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.ShipInfo;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.helpers.AuthProvider;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.AccountDetail;
//import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.Customer;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.services.CustomerServices;
// com.group4.coffeeblend.client.coffeeblendSpringSuite.services.OrderServices;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.services.ProductServices;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.services.OrderServices;

@Controller 
public class MainController {
	@Autowired
	private CustomerServices customerServices;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private ProductServices productService;
	@Autowired
	private OrderServices orderService;
	
	@GetMapping("/")
	public String viewHomePage(HttpServletRequest request, Model model) {
		List<Product> top4Product = productService.getTopProducts(4);
		List<Product> coffeeProduct = productService.getProductByCategory("Cà phê", 3);
		List<Product> teaProduct = productService.getProductByCategory("Trà", 3);
		List<Product> smoothiesProduct = productService.getProductByCategory("Sinh tố" , 3);
		List<Product> milkteaProduct = productService.getProductByCategory("Trà sữa" ,3);
		List<Product> snackProduct = productService.getProductByCategory("Snack" , 3);
		AccountDetail currentCustomer = UserLogInUtil.getUserInSession(request);
		model.addAttribute("top4",top4Product );
		model.addAttribute("coffees",coffeeProduct );
		model.addAttribute("teas",teaProduct );
		model.addAttribute("smoothies",smoothiesProduct );
		model.addAttribute("milkteas",milkteaProduct );
		model.addAttribute("snacks",snackProduct );
		model.addAttribute("curCustomer", currentCustomer);
		return "index";
		
	}
	
	@RequestMapping("/about")
	public String viewAboutPage(HttpServletRequest request,Model model) {
		AccountDetail currentCustomer = UserLogInUtil.getUserInSession(request);
		model.addAttribute("curCustomer", currentCustomer);
		return "about";}

	@GetMapping(value = "/menu")
	public String viewMenuPage(HttpServletRequest request,Model model) {
		AccountDetail currentCustomer = UserLogInUtil.getUserInSession(request);
		List<Product> topProduct = productService.getTopProducts(5);
		List<Product> bsProduct = productService.getBestSellerProducts();
		List<Product> discoverCoffee = productService.getProductByCategory("Cà phê", 8);
		List<Product> discoverTea = productService.getProductByCategory("Trà", 8);
		List<Product> discoverSmoothies = productService.getProductByCategory("Sinh tố" ,8);
		List<Product> discoverMilktea = productService.getProductByCategory("Trà sữa" ,8);
		List<Product> discoverSnack = productService.getProductByCategory("Snack" , 8);
		model.addAttribute("menuDisplay", topProduct);
		model.addAttribute("bestSeller", bsProduct);
		model.addAttribute("discoverCoffee", discoverCoffee);
		model.addAttribute("discoverTea",discoverTea );
		model.addAttribute("discoverSmoothies",discoverSmoothies );
		model.addAttribute("discoverMilktea",discoverMilktea );
		model.addAttribute("discoverSnack",discoverSnack);
		model.addAttribute("curCustomer", currentCustomer);
		return "menu";
	}

	@GetMapping(value = "/info/{id}")
	public String showInfoProduct(@PathVariable(name = "id") Integer id, Model model) {
//		ModelAndView modelAndView = new ModelAndView("product-details");
//		Product product =  productService.get(id);
//		System.out.println("product is: "+ product.getProductName());
//		modelAndView.addObject("product", product);
//		return modelAndView;
		
		
		Product product =  productService.get(id);
		model.addAttribute("product", product);
		return "product-details";
	}


//	@RequestMapping("/product-detail")
//	public String viewProductDetailPage(Model model) {return "product-detail";}

	@RequestMapping("/blog")
	public String viewBlogPage(HttpServletRequest request,Model model) 
	{
		AccountDetail currentCustomer = UserLogInUtil.getUserInSession(request);
		model.addAttribute("curCustomer", currentCustomer);
		return "blog";}
	
	@RequestMapping("/contact")
	public String viewContactPage(HttpServletRequest request,Model model) {
		AccountDetail currentCustomer = UserLogInUtil.getUserInSession(request);
		model.addAttribute("curCustomer", currentCustomer);
		return "contact";}
	
	@GetMapping("/register")
	public String showRegister(Model model) {
		AccountDetail thisCustomer = new AccountDetail();
		model.addAttribute("AccountDetail", thisCustomer);
		return "register";
	}
	
	@PostMapping("/register")	
	public String postRegister(@Valid @ModelAttribute("AccountDetail") AccountDetail customer, BindingResult bindingResult) {
		AccountDetail currentCustomer = customerServices.getByEmail(customer.getEmail());
		
		if(bindingResult.hasErrors()) {
			System.out.println("error");

			return "register";
		}
		if(currentCustomer == null) {
			customerServices.registerNewCustomer(customer, AuthProvider.BASIC);
			sendEmail(customer.getEmail());
			return "register_success";
		}
		else return "register_failed";
	}
	
	@GetMapping("/login")
	public String getMapping(HttpServletRequest request,Model model) {
		AccountDetail thisCustomer = new AccountDetail();
		model.addAttribute("AccountDetail", thisCustomer);
		return "login";
	}
	
	
	@PostMapping("/login")	
	public String postLogin(@ModelAttribute("AccountDetail") AccountDetail customer,
			HttpServletRequest request) {
		AccountDetail currentCustomer = customerServices.getByEmail(customer.getEmail());
		
		if(currentCustomer == null) {
			return "login_with_fault";
		}
		else {
			if(CoffeeMintPasswordEncryptor.checkPassword(customer.getPassword(),currentCustomer.getPassword())) {
				System.out.println("password matched!");
				 UserLogInUtil.setAccountDetail(request, currentCustomer);
				return "redirect:/";
			}
			else {
				System.out.println("password not matched!");
				return "login_with_fault";
			}
			
		}
	}
	
	@GetMapping("/logout")
	public String logOut(HttpServletRequest request, Model model) {
		AccountDetail currentCustomer = UserLogInUtil.getUserInSession(request);
		UserLogInUtil.removeUserInSession(request);
		return "redirect:/";
	}
	
	private void sendEmail(String toMail) {
		String fromMail = "ldhung311@gmail.com";
		MimeMessage message = mailSender.createMimeMessage();
		//MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setSubject("CoffeeBlend Register Success");
			helper.setFrom(fromMail);
			helper.setTo(toMail);
			helper.setText("<h1> Verification of CoffeeBlend </h1> <br> ");
			mailSender.send(message);
		}
		catch(MessagingException ex) {
			ex.printStackTrace();
		} 
		
	}
	
	@RequestMapping("/addToCart")
	public String buyProductHandler(HttpServletRequest request, Model model,
			@RequestParam(value = "code", defaultValue = "") Integer code, @RequestParam(value = "quantity", defaultValue = "")Integer quantity) {
		System.out.println("Quantity: " + quantity);
		Product product = productService.get(code);
		if (product != null) {
			CartInfo cartInfo = ShopCartUtil.getCartInSession(request);
			cartInfo.addProduct(product, 1);
		}
		
		return "redirect:/shoppingCart";
	}
	
	@RequestMapping(value = {"/shoppingCart"}, method = RequestMethod.GET )
	public String viewShoppingCart(HttpServletRequest request, Model model) {
		CartInfo myCart = ShopCartUtil.getCartInSession(request);
		String priceTotal = myCart.getTotalPrice(myCart);
		System.out.println(priceTotal);
		model.addAttribute("cartForm" , myCart);
		model.addAttribute("priceTotal" , priceTotal);
		return "cart";
	}
	
	@RequestMapping(value = {"/update-quantity"},method = RequestMethod.POST )
	public String updateShoppingCart(HttpServletRequest request, Model model, 
			@ModelAttribute("quantity") CartInfo cartForm) {
		CartInfo myCart = ShopCartUtil.getCartInSession(request);
		myCart.updateQuantity(cartForm);
		
		//model.addAttribute("cartForm" , myCart);
		return "redirect:/shoppingCart";
	}
	
	@RequestMapping(value = {"/includeLogoNum"}, method = RequestMethod.GET)
	public String updateLogo(HttpServletRequest request, Model model) {
		CartInfo cartInfo = ShopCartUtil.getCartInSession(request);
		Integer logoNum = cartInfo.getCartLines().size();
		if (cartInfo.isEmpty()) {
			model.addAttribute("logoNum", logoNum);
			return "redirect:/shoppingCart";
		}
		//Integer logoNum = cartInfo.getCartLines().size();
		
		model.addAttribute("logoNum", logoNum);
		
		return "cart";
	}
	
	@RequestMapping(value = {"/shoppingCartShipInfo"}, method = RequestMethod.GET)
	public String shoppingCartShipInfo(HttpServletRequest request, Model model) {
		CartInfo cartInfo = ShopCartUtil.getCartInSession(request);
		if (cartInfo.isEmpty()) {
			return "redirect:/shoppingCart";
		}
		ShipInfo shipInfo = new ShipInfo();
		
		model.addAttribute("shipInfo", shipInfo);
		
		return "shoppingCartShipInfo";
	}
	
	@RequestMapping(value = {"/shoppingCartShipInfo"}, method = RequestMethod.POST)
	public String saveShipInfo(HttpServletRequest request, Model model,
			@ModelAttribute("shipInfo") ShipInfo shipInfo) {
		
		CartInfo cartInfo = ShopCartUtil.getCartInSession(request);
		cartInfo.setShipInfo(shipInfo);
		
		return "redirect:/shoppingCartConfirmation";
	}
	
	
	@RequestMapping(value = {"/shoppingCartConfirmation"}, method = RequestMethod.GET)
	public String viewShoppingCartConfirm(HttpServletRequest request, Model model) {
		
		CartInfo cartInfo = ShopCartUtil.getCartInSession(request);
		if (cartInfo.isEmpty()) {
			return "redirect:/shoppingCart";
		}
		model.addAttribute("myCart", cartInfo);
		
		return "shoppingCartConfirmation";
	}
	
	@RequestMapping(value = {"/shoppingCartConfirmation"}, method = RequestMethod.POST)
	public String saveShoppingCartConfirm(HttpServletRequest request, Model model) {
		CartInfo cartInfo = ShopCartUtil.getCartInSession(request);
		if (cartInfo.isEmpty()) {
			return "redirect:/shoppingCart";
		}
		
		//save order to database; -> generate order_num;
		//orderService.saveOrder(cartInfo);
		
		//->> gui email thong tin don hang + order_num
		
		ShopCartUtil.removeCartInSession(request);
		
		ShopCartUtil.saveLastCartInSession(request, cartInfo);
		
		return "redirect:/shoppingCartFinalize";
	}
	
	@RequestMapping("/shoppingCartFinalize")
	public String shoppingCartFinalize(HttpServletRequest request, Model model) {
		CartInfo lastOrderedCart = ShopCartUtil.loadLastCartInSession(request);
		
		if (lastOrderedCart == null) {
			return "redirect:/shoppingCart";
		}
		model.addAttribute("lastOrderedCart", lastOrderedCart);
		
		return "shoppingCartFinalize";
		
	}
	
	
	@RequestMapping("/deleteItem")
	public String removeProductHandler(HttpServletRequest request, Model model,
			@RequestParam(value = "code", defaultValue = "") Integer code) {
		Product product = productService.get(code);
		if (product != null) {	
			CartInfo cartInfo = ShopCartUtil.getCartInSession(request);
			cartInfo.removeProduct(product);
		}
		
		return "redirect:/shoppingCart";
	}
	
		
	
	@GetMapping( value = "/new-shipping")
	public String checkOutWithUser(HttpServletRequest request, Model model) {
		AccountDetail currentCustomer = UserLogInUtil.getUserInSession(request);
		CartInfo myCart = ShopCartUtil.getCartInSession(request);
		if(myCart.isEmpty()) return "redirect:/shoppingCart";
		if(currentCustomer == null) {
			return "redirect:/login";
		}
		String priceTotal = myCart.getTotalPrice(myCart);
		System.out.println("cur customer: "+ currentCustomer.getName());
		ShipInfo shipInfo = new ShipInfo();
		model.addAttribute("currentCustomer", currentCustomer);
		model.addAttribute("priceTotal", priceTotal);
		model.addAttribute("shipInfo", shipInfo);
		return "checkout";	
	}
	
	@GetMapping(value = "/customerInfo")
	public String displayCustomerInfo(HttpServletRequest request, Model model) {
		AccountDetail currentCustomer = UserLogInUtil.getUserInSession(request);
		if(currentCustomer == null) return "redirect:/login";
		System.out.println("cur customer: "+ currentCustomer.getName());
		model.addAttribute("currentCustomer", currentCustomer);
		return "info-user";
	}

	@PostMapping("/save-new-shipping")
	public String saveNewShipping(HttpServletRequest request, @ModelAttribute("shipInfo") ShipInfo shipInfo, BindingResult result) {
		if(result.hasErrors()) return "redirect:/new-shipping";
		System.out.println("save-new-ship");
		
		CartInfo cartInfo = ShopCartUtil.getCartInSession(request);
		if (cartInfo.isEmpty()) {
			return "redirect:/shoppingCart";
		}
		cartInfo.setShipInfo(shipInfo);
		System.out.println("saved");

		orderService.saveNewShipping(cartInfo);
		ShopCartUtil.removeCartInSession(request);
		ShopCartUtil.saveLastCartInSession(request, cartInfo);
		
		return "order-success";
	}
	
	
	
//	@GetMapping(value = "/search")
//	public String searchKey(Model model, @Param("key") String key) {
//		System.out.println(key);
//		List<Product> list = productService.find(key);
//		model.addAttribute("result", list);
//		return "shop";
//	}
	
//	@GetMapping(value = "/info/{id}")
//	public String showInfoProduct(@PathVariable(name = "id") Integer id, Model model) {		
//		Product product =  productService.get(id);
//		model.addAttribute("product", product);
//		return "product-details";
	
}
