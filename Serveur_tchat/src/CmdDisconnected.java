	public class CmdDisconnected implements Commande{ //renvoi l'utilisateur a l'identification
	private Utilisateur l_utilisateur;
	
	public CmdDisconnected(Utilisateur u){
		l_utilisateur = u;
	}
	public void analyse(String[] s)  throws ErreurDeSyntaxeException { 
		if(s.length != 1){
			throw new ErreurDeSyntaxeException("parametre incorrect");
		}
	}
	public String execute()  throws ErreurExecutionException { 
		Serv srv = l_utilisateur.getServeur();
		TableUtilisateurs tab_ut = srv.getTableUtilisateurs();
		String[] logins = tab_ut.getAllLogins();
		srv.creerIdentification(l_utilisateur.getSocket());
		srv.desenregistrerUtilisateur(logins[0]);
		
		
		String reponse="";
		return reponse;
	}
	}