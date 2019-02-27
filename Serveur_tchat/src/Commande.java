
public interface Commande {
	public static final String USER = "USER", MSGTO = "MSGTO",LIST = "LIST",QUIT = "QUIT", HELP="HELP"
			, DISCONNECTED="DISCONNECTED", RENAME="RENAME", MOI="MOI";

	
	public void analyse(String[] commande) throws ErreurDeSyntaxeException;
	
	
	public String execute() throws ErreurExecutionException;
	
}
