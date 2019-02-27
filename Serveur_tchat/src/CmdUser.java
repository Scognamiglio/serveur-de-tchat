

public class CmdUser implements Commande{ //permet de passer de identification à utilisateur
	private Identification l_identification; 
	private String Le_login; 
	
	public CmdUser(Identification i){ 
		l_identification = i;
	}
	
	public void analyse(String[] s)  throws ErreurDeSyntaxeException { 
		if(s.length != 2){
			throw new ErreurDeSyntaxeException("parametres incorrect");
		}
		Le_login = s[1];
	}
	public String execute()  throws ErreurExecutionException { 
		String reponse;
		TableUtilisateurs tab_ut;
		tab_ut = l_identification.getServeur().getTableUtilisateurs();
		
		
		if(Le_login.length() < 6 || Le_login.length() > 15)	{reponse = "-ERR USER " + Le_login + " : taille incorrecte";}
		
		else if(tab_ut.loginExiste(Le_login))				{reponse = "-ERR USER " + Le_login + " : pas disponible";}
		
		
		else{
			l_identification.getServeur().enregistreUtilisateur(Le_login,l_identification.getSocket());
			reponse = "+OK USER " + Le_login;
			l_identification.verif_user=true;
		}
		return reponse;
	}
}
