import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;


public abstract class Client {
	private Serv Le_Ssrv; 
	private Socket Le_Socket; 
	BufferedWriter la_sortie; 
	BufferedReader L_entre; 
	
	
	public Client(Serv s, Socket so) throws IOException{ 
		Le_Ssrv = s;
		Le_Socket = so;
		L_entre = new BufferedReader(new InputStreamReader(Le_Socket.getInputStream(), "UTF-8"));
		la_sortie = new BufferedWriter(new OutputStreamWriter(Le_Socket.getOutputStream(),"UTF-8"));
	}
	
	public abstract boolean traiter(); 
	public Socket getSocket(){return Le_Socket;}
	public Serv getServeur(){return Le_Ssrv;}
	
	
	public void envoyer(String msg){ 
		try{
			la_sortie.write(msg);
			la_sortie.newLine();
			la_sortie.flush();
			System.out.println("Envoie de : " + msg);
		}
		catch(IOException e){
			System.out.println("Erreur d'E/S lors de l'envoi d'un message");
		}
	}
	public String getIp(){ 
		return Le_Socket.getInetAddress().getHostAddress();
	}

	public String[] decoupe(String s) { 
	    Scanner sc = new Scanner(s);
	    ArrayList<String> tab = new ArrayList<String>();
	    String tab_ret [];
	    Pattern p = Pattern.compile("\\[" +"|\\]" +"|\"[^\"]*\"" +"|[\\p{IsLatin}\\p{Graph}&&[^\\]\\[]]+");
	    
	    
	    String token;
	    
	    while ((token = sc.findInLine(p)) != null) {
		tab.add(token);
	    }
	    tab_ret = new String [tab.size()];
	    sc.close();
	    return tab.toArray(tab_ret);
	}
	public void stopper(String msg){ 
		if(msg != null){
			envoyer(msg);
			try{
				Le_Socket.close();
			}
			catch(IOException e){
				System.out.println("probleme a la fermeture du socket");
			}
		}
	}
}
