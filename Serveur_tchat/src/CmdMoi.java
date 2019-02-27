
public class CmdMoi implements Commande{ //permet de savoir qui on est
	private Utilisateur l_utilisateur;   // principalement utile pour les tests..
	
	public CmdMoi(Utilisateur u){
		l_utilisateur = u;
	}
	public void analyse(String[] s)  throws ErreurDeSyntaxeException { 
		if(s.length != 1){
			throw new ErreurDeSyntaxeException("paremetre incorrect");
		}
	}
	public String execute()  throws ErreurExecutionException { 
		String reponse = "vous etes "+l_utilisateur.getLogin()+"\n+OK MOI";
		return reponse;
	}
}