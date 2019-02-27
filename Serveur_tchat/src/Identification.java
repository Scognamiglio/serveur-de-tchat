import java.io.IOException;
import java.net.Socket;



public class Identification extends Client{

	public Identification(Serv s, Socket so) throws IOException { 
		super(s, so);
	}
	boolean verif_user=false;
	public boolean traiter() { 
		String ligne="" , reponse = " ", tab_cmd[];
		
		CmdUser cmd;
		try{
			if(L_entre.ready()){
				
				if(!verif_user){
					ligne = L_entre.readLine();
				}
				if(ligne == null){
					System.out.println("deconnexion non attendu de " + getIp());
					return false;
				}
				ligne = ligne.trim();
				tab_cmd = decoupe(ligne);
				if(tab_cmd.length >0){
					if(tab_cmd[0].toUpperCase().equals(Commande.USER)){
						cmd = new CmdUser(this);
						cmd.analyse(tab_cmd);
						reponse = cmd.execute();
						System.out.println("Reponse : " + reponse);
					
					
				}else{
					throw new ErreurDeSyntaxeException(tab_cmd[0] + " : commande inconnue iden");
				}
				envoyer(reponse);}
			}
		} catch(IOException e){
			System.out.println("Erreur d'E/S lors de l'utilisation du socket");
			stopper("Connexion interompue par le serveur");
			
			return false;
		}
		catch(ErreurDeSyntaxeException e){envoyer("-ERR " + e.getMessage());verif_user=false;	return true;}
		
		
		catch(ErreurExecutionException e){
			System.out.println("Erreur d'E/S lors de l'utilisation du socket");
			stopper("Connexion interompue par le serveur");
			
			
	        
	        
		}if(reponse.charAt(0) == '+'){	 // vu que la seul commande disponible est USER
	            return false;			// si la reponse a un + dedans, c'est que l'on est
	            						//plus en phase d'identification
	        }return true;
	   
	}
}
