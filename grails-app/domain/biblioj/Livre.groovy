package biblioj

class Livre {
	String titre
	int nombreExemplaires
	int nombreExemplairesDisponibles
	static hasMany = [auteurs : Auteur, reservations: Reservation]
	static belongsTo = [Auteur]
	TypeDocument typeDocument
	
	static constraints = {
		titre nullable:false
		nombreExemplaires nullable:false, min:0
		nombreExemplairesDisponibles nullable:false, min:0, validator:{ val, obj -> val<= obj.nombreExemplaires}
		auteurs nullable:true 
		reservations nullable:true
		typeDocument nullable:true 
	}
}
