import java.util.ArrayList;

// pour l'utiliser utiliser la forme msgto [cible] le message

public class CmdMsgTo implements Commande{
	private Utilisateur l_utilisateur; 
	private String[] les_destinataires; 
	private String le_msg; 
	
	public CmdMsgTo(Utilisateur u){
		l_utilisateur = u;
	}

	public void analyse(String[] s) throws ErreurDeSyntaxeException { 
	    ArrayList<String> dst = new ArrayList<String>();
	    if (s.length < 3) {
	        throw new ErreurDeSyntaxeException("Mauvais parametre");
	    }
	    if (! s[1].equals("[")) {
	        throw new ErreurDeSyntaxeException("mauvais destinataire");
	    }
	    int i;
	    for (i = 2 ; i < s.length && ! s[i].equals("]") ; i++) {
	        dst.add(s[i]);
	    }
	    if (i == s.length) {
	        throw new ErreurDeSyntaxeException("mauvais destinataire 2");                      
	    }
	    les_destinataires = dst.toArray(new String [dst.size()]);
	  
	    StringBuilder buf = new StringBuilder("");
	    for (i++ ; i < s.length ; i++) {
	        if (! buf.equals("")) {
	            buf.append(" ");
	        }
	        buf.append(s[i]);
	    }
	    le_msg = buf.toString();
	}
	public String execute()  throws ErreurExecutionException { 
		Serv srv = l_utilisateur.getServeur();
		TableUtilisateurs tab_ut = srv.getTableUtilisateurs();
		String reponse;
		if(les_destinataires.length == 1 && les_destinataires[0].toUpperCase().equals("ALL")){
			srv.diffuse(l_utilisateur.getLogin(), Notification.MSGFROM, le_msg);
		}
		else{
			for(int i = 0; i < les_destinataires.length; i++){
				String desti=les_destinataires[i];
				System.out.print(desti);
				if(desti.equals("all")){
					throw new ErreurExecutionException("all est pas un utilisateur,il s'utilise seul");
					
				}
				else if(tab_ut.getUtilisateur(les_destinataires[i]) == null){
					throw new ErreurExecutionException("utilisateur " + les_destinataires[i] + " inconnu");
				}
			}
			srv.diffuse(l_utilisateur.getLogin(),Notification.MSGFROM, le_msg,les_destinataires);
		}
		reponse = "+OK MSGTO à ";
		for(int i = 0; i < les_destinataires.length; i++){
		reponse=reponse+ les_destinataires[i]+" ";
		}
		return reponse;
	}
}
