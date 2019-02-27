import java.io.IOException;
import java.net.Socket;



public class Utilisateur extends Client{
	private String le_login; 
	
	public Utilisateur(String l, Serv s, Socket so) throws IOException { 
		super(s, so);
		le_login = l;
	}
	public String getLogin(){ 
		return le_login;
	}

	public boolean traiter(){ 
		String ligne;	      
		String[] tab_cmd;	  								
		Commande cmd;										
		String reponse;
		Boolean fin_client = true;
		try {
			if(L_entre.ready()){
				ligne = L_entre.readLine();
				if(ligne == null){
					System.out.println("Deconnexion de " + getSocket());
					return false;
				}
				ligne = ligne.trim();
				tab_cmd = decoupe(ligne);
				if(tab_cmd.length >0){
					
					
				if(tab_cmd[0].toUpperCase().equals(Commande.MSGTO)){
					cmd = new CmdMsgTo(this);
				}
				else if(tab_cmd[0].toUpperCase().equals(Commande.LIST)){
					cmd = new CmdList(this);
				}
				else if(tab_cmd[0].toUpperCase().equals(Commande.QUIT)){
					cmd = new CmdQuit(this);
					fin_client = false;
					
				}
				else if(tab_cmd[0].toUpperCase().equals(Commande.HELP)){
					cmd = new CmdHelp(this);
				}
				else if(tab_cmd[0].toUpperCase().equals(Commande.DISCONNECTED)){
				
					cmd= new CmdDisconnected(this);
					fin_client = false;
				}
				else if(tab_cmd[0].toUpperCase().equals(Commande.RENAME)){
					
					cmd= new CmdRename(this);
					
				}
				else if(tab_cmd[0].toUpperCase().equals(Commande.MOI)){
					cmd=new CmdMoi(this);
				}
				else{
					throw new ErreurDeSyntaxeException(tab_cmd[0] + " : commande inconnue");
				}
				cmd.analyse(tab_cmd);
				reponse = cmd.execute();
				if(!tab_cmd[0].toUpperCase().equals(Commande.QUIT)){
					envoyer(reponse);
				}}
			} 
		}catch(IOException e){
				System.out.println("Erreur d'E/S lors l'utilisation du socket");
				stopper("Connexion coupe par le serveur");
				return false;
			}
			catch(ErreurDeSyntaxeException e){
				envoyer("-ERR " + e.getMessage());
				return true;
			}
			catch(ErreurExecutionException e){
				envoyer("-ERR " + e.getMessage());
				return true;
			}
		return fin_client;
	}

	public boolean equals(Object anObject) {
	    if (anObject instanceof Utilisateur) {
	        return le_login.equals(((Utilisateur)anObject).le_login);
	    }
	    return false;
	}
	public String SetLogin(String a){
		String reponse=le_login;
		le_login=a;
		return le_login;
	}
}
