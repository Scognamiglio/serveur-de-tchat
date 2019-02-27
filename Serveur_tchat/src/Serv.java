import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.Queue;


public class Serv {
private Queue<Client> File;
private ServerSocket Le_Ssrv;
private TableUtilisateurs les_utilisateurs;
Utilisateur user;


public Serv() throws IOException {
	Le_Ssrv = new ServerSocket(3333);
	Le_Ssrv.setSoTimeout(50);
	File = new ArrayDeque<Client>(50);
	les_utilisateurs = new TableUtilisateurs();
}

public void lancer() throws IOException{
	Socket client;
	
	while(true){
		client=null;
		try{
			client=Le_Ssrv.accept();
		}catch(SocketTimeoutException e){}
		
		
	
	if(client!=null){
		System.out.println("nouvelle connexion");
		creerIdentification(client);
	}
	traiterClientSuivant();
}
}
public TableUtilisateurs getTableUtilisateurs(){ 
	return les_utilisateurs;
}
public void diffuse(String login, Notification notif, String msg){
	
		Utilisateur[] user = les_utilisateurs.getLoginsSauf(login);
		if(notif.equals(Notification.MSGFROM)){
			for(int i = 0; i < user.length; i++){
				
				user[i].envoyer(notif + " [" + login + "] " + msg);
			}
		}
		else{
			
			for(int i = 0; i< user.length; i++){
				user[i].envoyer(notif +" "+ msg);
			}
		}
	}
public void diffuse(String login, Notification notif, String msg, String[] destinataires){
		Utilisateur[] user = new Utilisateur[destinataires.length];
		for(int i = 0; i < user.length; i++){
				user[i] = les_utilisateurs.getUtilisateur(destinataires[i]);
		}
		if(notif.equals(Notification.MSGFROM)){
			for(int i = 0; i < user.length; i++){
				user[i].envoyer(notif + " [" + login + "] " + msg);
			}
		}
		else{
			for(int i = 0; i< user.length; i++){
				user[i].envoyer(notif + msg);
			}
		}
	}

public void enregistreUtilisateur(String login, Socket so) { 
	user = null;
	try {
		user = new Utilisateur(login, this, so);
	}catch (IOException e) {
		System.out.println("Erreur pour la creation Utilisateur");
	}
	les_utilisateurs.ajoute(user);
	try{
		File.offer(user);
	}catch (RuntimeException e){
		System.out.println("Erreur lors de l'ajout dans la file");
	}
	diffuse(login, Notification.CONNECT, login);
}

public void desenregistrerUtilisateur(String login){ 
	Utilisateur user =les_utilisateurs.supprime(login);
	if(user != null){
		
			
			diffuse(login, Notification.DISCONNECT, login);
		}
	}

public void creerIdentification(Socket client){ 
	Client Le_client = null;
	try{
		Le_client = new Identification(this, client);
	}
	catch(IOException e){
		System.out.println("Erreur creation de identificatifiont");
	}
	Le_client.envoyer("Bienvenue sur le tchat");
	try{
		File.offer(Le_client);
	}catch (RuntimeException e){
		System.out.println("Erreur lors de l'ajout dans la file");
	}
}
private void traiterClientSuivant() throws NullPointerException{ 
	Client Le_client;
	if(!File.isEmpty()){
		Le_client = File.poll();
		if(Le_client.traiter()){
			File.offer(Le_client);
		}
	}
}
	public String SetLogin(String le_login){
		return "";
	}

public static void main(String [] args) throws IOException{
	Serv srv = new Serv();
	srv.lancer();
}}
