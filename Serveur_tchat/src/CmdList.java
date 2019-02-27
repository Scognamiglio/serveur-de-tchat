
public class CmdList implements Commande{ // donne tout les utilisateurs
	private Utilisateur l_utilisateur; 
	
	public CmdList(Utilisateur u){
		l_utilisateur = u;
	}
	public void analyse(String[] s)  throws ErreurDeSyntaxeException { 
		if(s.length != 1){
			throw new ErreurDeSyntaxeException("paremetre incorrect");
		}
	}
	public String execute()  throws ErreurExecutionException { 
		Serv srv = l_utilisateur.getServeur();
		TableUtilisateurs tab_ut = srv.getTableUtilisateurs();
		String[] logins = tab_ut.getAllLogins();
		String reponse = logins[0]+ "\n";
		for(int i = 1; i < logins.length; i++){
			reponse = reponse+logins[i]+"\n";
		}
		reponse = reponse +"+OK LIST END";
		return reponse;
	}
}