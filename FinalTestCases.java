package com.example;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.data.Cart;
import com.example.data.ForgotPassword;

import com.example.data.PlacedOrder;
import com.example.data.Product;
import com.example.data.RetailerSignUp;
import com.example.data.UserSignUp;
import com.example.data.Wishlist;
import com.example.exception.CartException;
import com.example.exception.CustomerException;
import com.example.exception.UserNotFoundException;
import com.example.exception.WishlistException;

import com.example.pojo.RetailerTable;
import com.example.pojo.UserTable;
import com.example.repository.CartDAOImpl;
import com.example.repository.PlaceOrderImp;
import com.example.repository.ProductDAOImpl;
import com.example.repository.RetailerDAOImpl;
import com.example.repository.UserDAOImpl;
import com.example.repository.UserTableRepositoryImpl;
import com.example.repository.WishlistDAOImpl;

@SpringBootTest
class OnlineShoppingApplicationTests {

	
	
	@Autowired
	UserTableRepositoryImpl userRepoImpl;
	@Autowired
	PlaceOrderImp placeOrderImp;
	@Autowired
	UserDAOImpl userDaoImpl;
	@Autowired
	WishlistDAOImpl wishlistDaoImpl;
	@Autowired
	CartDAOImpl cartDaoImpl;
	@Autowired
	ProductDAOImpl productDaoImpl;
	@Autowired
	RetailerDAOImpl retailerDaoImpl;
	
	@Test
	void contextLoads() {
	}


	@Test
	public void getUserByEmailAndPasswordTest() throws UserNotFoundException{
		
		int id=userRepoImpl.getUserByEmailAndPassword("sergio@gmail.com","password@1234");
		if(id>0) {
			System.out.println("login successfully: Id is"+id);
		}
		else
		{
			System.out.println("User not found");
		}
	}//Not Running
	
	@Test
	 public void updatePasswordFromMaiTest() throws UserNotFoundException
	 {
		ForgotPassword reset=new ForgotPassword();
		reset.setEmail("sergio@gmail.com");
		reset.setPassword("password@1234");
		int rs=userRepoImpl.updatePasswordFromMail(reset);
		
		if(rs==100) {
			System.out.println("Password update successfully");
		}
		else{
			System.out.println("Not found");
		}
	 
	 }
	
	@Test
	public void getMyPlacedOrdersTest() {
		
		List<PlacedOrder> placeOrder=placeOrderImp.showPlacedOrders(323);
		for(PlacedOrder order:placeOrder) {
		System.out.println(order.getpBrand());	
		System.out.println(order.getpId());
		System.out.println(order.getpImage());
		System.out.println(order.getpName());
		System.out.println(order.getpOrderDate());
		System.out.println(order.getpPrice());
		System.out.println(order.getpQty());
		System.out.println(order.getpType());
		
			
		}
	}
	
	
	@Test
	public void getCartValuesTest(){
		List<Cart> cart=userRepoImpl.getCartOfUser(314);
		for(Cart c:cart) {
			System.out.println(c.getcId());
			System.out.println(c.getpBrand());
			System.out.println(c.getpId());
			System.out.println(c.getpImage1());
			System.out.println(c.getpName());
			System.out.println(c.getpPrice());
			System.out.println(c.getQty());
			System.out.println(c.getTotalPrice());
		}
		
	}
	
	@Test
	public void placeOrderTest() {
		List<Cart> cart=userRepoImpl.getCartOfUser(316);
		
		boolean check=placeOrderImp.placeOrder(cart, "COD");
		if(check==true) {
			System.out.println("order placed successfully");
		}
		else {
			System.out.println("Not placed please verify details");
		}
		
	}
	
	@Test
	public void addUserTest() throws CustomerException {
	UserSignUp sup=new UserSignUp("No:4 Pollachi road","avinash2gmail.com","9874564578","Avinash","avi@rockz");
	int test=userDaoImpl.addUser(sup);
	if(test>0)
	{
		System.out.println("Added succesfully!");
	}
	else
	{
		System.out.println("Not found");
	}
	
	}
	
	@Test
	public void getUserByEmailTest() throws CustomerException {
	UserTable s=userDaoImpl.getUserByEmail("lance@gmail.com");
	System.out.println(s.getUAddress());
	System.out.println(s.getUEmail());
	System.out.println(s.getUId());
	System.out.println(s.getUMobile());
	System.out.println(s.getUName());
	System.out.println(s.getUPassword());
	}
	

	@Test
	public void updateWishTest() throws WishlistException {
	boolean boo=wishlistDaoImpl.addToWishlist(302,403);
	if(boo==true) {

		System.out.println("Added successfully!");
	}
	else {
		System.out.println("Not Found");
	}
	}
	
	@Test
	public void deleteWishTest() throws WishlistException {
	boolean remove=wishlistDaoImpl. deleteWishlist(722);
	if(remove==true)
	System.out.println("Wish Removed");
	else
	System.out.println("Wish not removed");
	}

	@Test
	public void getWishTest() throws WishlistException {
	List<Wishlist> list=wishlistDaoImpl.getWishlistOfUser(301);
	for(Wishlist wish:list) {
		System.out.println(wish.getpBrand());
		System.out.println(wish.getpId());
		System.out.println(wish.getpImage1());
		System.out.println(wish.getpName());
		System.out.println(wish.getpPrice());
		System.out.println(wish.getwId());
	}
	}
	
	@Test
	public void addCartTest() throws CartException {
		 boolean s=cartDaoImpl.addToCart(303,402);
		if(s==true){
			System.out.println("Added to Cart");
		}
		else{
			System.out.println("Not Added");
		}
	}
	
	@Test
	public void deleteCartTest() throws CartException {
		 boolean remove=cartDaoImpl.deleteCart(800);
		 if(remove==true){
			System.out.println("Removed from Cart");
		}
		else{
			System.out.println("Not Removed");
		}
		
	}
	
	@Test
	public void searchProductByKeywordTest() throws CartException {
		 List<Product> pro1=productDaoImpl.getProductBySearchKeyWord("Macbook Mini");
		for(Product product:pro1) {
			System.out.println(product.getpBrand());
			System.out.println(product.getpCategory());
			System.out.println(product.getpDescription());
			System.out.println(product.getpId());
			System.out.println(product.getpImage1());
			System.out.println(product.getpImage2());
			System.out.println(product.getpName());
			System.out.println(product.getpPrice());
			System.out.println(product.getpQty());
			System.out.println(product.getpSubCategory());
		}
	}
	
	
	@Test
	public void sortProductTest() throws CartException {
		 List<Product> pro2=productDaoImpl.sortProduct("Macbook Mini", 1);
		for(int i = 0; i < pro2.size(); i++) {   
   	 		System.out.print(pro2.get(i));
		} 
	}
	
	@Test
	public void filterProductTest() throws CartException {
		 List<Product> pro3=productDaoImpl.filterProduct("Apple", 5000, 80000);
		for(int i = 0; i < pro3.size(); i++) {   
   	 		System.out.print(pro3.get(i));
		} 
	}
	
	@Test
	public void addWishDAOTest() throws WishlistException{
		boolean isAdded=wishlistDaoImpl.addToWishlist(316, 419);
		if(isAdded==true) {
			System.out.println("Wish Added");
		}
		else {
			System.out.println("Wish Not Added");
		}
	}
	
	@Test
	public void deleteWishDAOTest() throws WishlistException {
		boolean isDeleted=wishlistDaoImpl.deleteWishlist(721);
		if(isDeleted==true) {
			System.out.println("Wish Removed");
		}
		else {
			System.out.println("Wish not removed");
		}
	}
	
	@Test
	public void getWishDAOTest() throws WishlistException {
		List<Wishlist> getList=wishlistDaoImpl.getWishlistOfUser(310);
		for(int i = 0; i < getList.size(); i++) {   
   	 		System.out.print(getList.get(i));
		} 
	}
	


	@Test
	public void getRetailerbyIdTest() throws CustomerException{
		try {
	 RetailerSignUp s=retailerDaoImpl.getRetailerById(17);
	 if(s!=null) {
	 System.out.println(s.getrId());
	 System.out.println(s.getrName());
	 System.out.println(s.getrEmail());
	 System.out.println(s.getrPhone());
	 }
	 else {
		 throw new CustomerException("Retailer not Found");
	 }
	  
		}
		catch(RuntimeException e){
			System.out.println(e.getMessage());
		}
	}
	
	
	@Test
	public void addNewRetailerTest() throws CustomerException{
	 RetailerSignUp newRetailer=new RetailerSignUp("joot", "scott1234@gmail.com", "Joo123", "8989898654",22 );
		
	 int s=retailerDaoImpl.addRetailer(newRetailer);
	 
     System.out.println(s);
	}
	
	
	@Test
	public void updateRetailerTest() throws CustomerException{
	 RetailerSignUp newRetailer=new RetailerSignUp("Jokejoo", "joo21@gmail.com", "Joo123", "8989898654", 59);
		try {
	 RetailerTable s=retailerDaoImpl.updateRetailer(newRetailer);
	 if(s!=null)
	 {
		 System.out.println("Updated Successfully");
	 }
	 else
	 {
		 throw new CustomerException("Retailer not Found");
	 }
		}
		catch(RuntimeException e){
			System.out.println(e.getMessage());
		}
	 
	}
	
	@Test
	public void deleteRetailerByIdTest() throws CustomerException{
		try {
	 String s=retailerDaoImpl.deleteRetailerById(59);
	 System.out.println(s);
		}
		catch(Exception e){
			throw new CustomerException("No retailer found");
		}
	}
	
	@Test
	public void deleteProductByridTest() {
		String delete=	retailerDaoImpl.deleteRetailerById(59);
		if(delete!=null) {
    
	        System.out.println("Deleted Successfully"); 
	}
	else {
		System.out.println( "No product  found");
	}
	}
	
	@Test
	public void getRetailerByEmailAndPasswordTest() throws CustomerException{
		// TODO Auto-generated method stub
		
		int ok=retailerDaoImpl.getRetailerByEmailAndPassword("joo21@gmail.com","Joo123");
		if(ok>0)
			System.out.println("Found");
		else
			System.out.println("Not found");

	}
	
	@Test
	public void showAllRetailersTest() throws CustomerException{
		try {
			ArrayList<RetailerSignUp> s=(ArrayList<RetailerSignUp>) retailerDaoImpl.showAllRetailers();
	 if(s!=null) {
		 for(RetailerSignUp r : s)
			{
				int rId = r.getrId();
				String rName = r.getrName();
				String rPassword = r.getrPassword();
				String rEmail = r.getrEmail();
				String rPhone =r.getrPhone();
				System.out.println("retailer id"+rId);
				System.out.println("retailer name"+rName);
				System.out.println("retailer password"+rPassword);
				System.out.println("retailer email"+rEmail);
				System.out.println("retailer phone"+rPhone);
			}
	 }
	 else {
		 throw new CustomerException("Table not exists");
	 }
	  
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	
	@Test
	public void getProByIdTest() {
		Product pro1=productDaoImpl.getProductById(420);
		
			System.out.println(pro1.getpBrand());
			System.out.println(pro1.getpCategory());
			System.out.println(pro1.getpDescription());
			System.out.println(pro1.getpId());
			System.out.println(pro1.getpImage1());
			System.out.println(pro1.getpImage2());
			System.out.println(pro1.getpName());
			System.out.println(pro1.getpPrice());
			System.out.println(pro1.getpQty());
			System.out.println(pro1.getpSubCategory());
		}
}
	
	
