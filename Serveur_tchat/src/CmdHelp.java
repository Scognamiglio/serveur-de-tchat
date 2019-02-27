

	public class CmdHelp implements Commande{ // donne les commande
	private Utilisateur l_utilisateur;
	
	public CmdHelp(Utilisateur u){
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
		String reponse =  "  \nLIST = fais une liste des connecte "
				+ "\nQUIT=permet de se deconnecte"
				+ "\nMSGTO=pour l'utiliser utiliser la forme msgto [cible] le message"
				+ "\nDISCONNECTED=renvoie a la forme Identification"
				+ "\nRENAME=permet de changer le mot de passe	"
				+ "\nMOI=Indique qui tu es\n";
		reponse = reponse ;
		return reponse;
	}
	}
