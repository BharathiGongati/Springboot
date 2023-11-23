package org.jsp.lombok;

public class TestLombok {
	public static void main(String[] args) {
		User u1=new User(1,"ABC",888,"1@gmail.com","bittu@123");
		User u2=new User(1,"ABC",888,"1@gmail.com","bittu@123");
		System.out.println(u1.equals(u2));
		System.out.println(u1.hashCode());
		System.out.println(u2.hashCode());
		User u3=new User();
		u3.setId(12);
		System.out.println(u3.getId());
		
		
	}

	
}
