package biblioj

class TypeDocument {
	
	String intitule
	static belongsTo = [Livre]
	
    static constraints = {
		intitule nullable:false
    }
}
