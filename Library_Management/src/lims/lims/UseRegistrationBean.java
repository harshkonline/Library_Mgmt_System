package lims.lims;

public class UseRegistrationBean {
	public static void main(String[] args) {
		RegistrationBean bean=new RegistrationBean();
		String status=bean.registerBook("Oracle");
		System.out.println("Result="+status);
	}

}
