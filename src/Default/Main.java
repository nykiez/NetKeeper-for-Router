package Default;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		
		String user,password;
		
		user="1777*******";
		password="******";
		
		CXKUsername test=new CXKUsername(user);
		
		user=test.Realusername();
		
		user=user.substring(2);//I don't know why my router works this way
		
		System.out.println(user);
		
		RouterPPPoE r= new RouterPPPoE("192.168.0.1");
		
		try {
			r.PPPoE(user, password);
			JOptionPane.showMessageDialog(null, "OK!", "info", JOptionPane.INFORMATION_MESSAGE); 
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Something error!", "info", JOptionPane.ERROR_MESSAGE); 
		} 
	}

}
