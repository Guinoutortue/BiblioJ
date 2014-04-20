package biblioj

import java.util.Set;

class Auteur {
	String nom
	String prenom
	static hasMany = [livres : Livre]
	static belongsTo = [Livre]
	Set livres = new HashSet()
	
    static constraints = {
		nom nullable:false 
		prenom nullable:false
		livres nullable:true 
    }
}
