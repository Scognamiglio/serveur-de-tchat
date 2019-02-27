
public class CmdQuit implements Commande{ // ferme la connexion..
	private Utilisateur L_user; 
	
	public CmdQuit(Utilisateur user){
		L_user = user;
	}
	public void analyse(String[] s)  throws ErreurDeSyntaxeException { 
		if(s.length != 1){
			throw new ErreurDeSyntaxeException("parametre incorrect");
		}
	}
	public String execute()  throws ErreurExecutionException { 
		String reponse = "+OK QUIT";
		Serv srv = L_user.getServeur();
		L_user.stopper(reponse);
		srv.desenregistrerUtilisateur(L_user.getLogin());
		return reponse;
	}
}