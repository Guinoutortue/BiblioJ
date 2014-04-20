package biblioj

class Panier {
	static hasMany = [meslivres: Livre]
	String username
	
    static constraints = {
		meslivres nullable:true 
		username nullable:false
    }
}
