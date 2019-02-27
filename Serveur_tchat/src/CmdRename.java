	public class CmdRename implements Commande{ //change le nom
	private Utilisateur l_utilisateur;
	private String Le_login;
	
	public CmdRename(Utilisateur u){
		l_utilisateur = u;
	}
	public void analyse(String[] s)  throws ErreurDeSyntaxeException { 
		if(s.length != 2){
			throw new ErreurDeSyntaxeException("parametre incorrect");
		}
		Le_login=s[1];
	}
	public String execute()  throws ErreurExecutionException {
		String reponse="";
		Serv srv = l_utilisateur.getServeur();
		TableUtilisateurs tab_ut = srv.getTableUtilisateurs();
		String[] logins = tab_ut.getAllLogins();
		
		
		if(Le_login.length() < 6 || Le_login.length() > 15)	{reponse = "-ERR USER " + Le_login + " : taille incorrecte";}
		
		else if(tab_ut.loginExiste(Le_login))				{reponse = "-ERR USER " + Le_login + " : pas disponible";}
		
		else{                       //aide
			l_utilisateur =l_utilisateur.getServeur().getTableUtilisateurs().supprime(l_utilisateur.getLogin());
					String login=l_utilisateur.SetLogin(Le_login);
					tab_ut.ajoute(l_utilisateur);
			l_utilisateur.getServeur().diffuse(Le_login, Notification.RENOME," "+login +"to"+Le_login);
			reponse="+OK RENAME";
		}
			
		return reponse;
	}
	}