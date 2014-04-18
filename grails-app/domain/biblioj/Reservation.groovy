package biblioj

class Reservation {
	int code
	Date dateReservation
	static hasMany = [livres : Livre]
	static belongsTo = [Livre]
	
    static constraints = {
		code validator:{ val -> Reservation.findByCode(val)==null } 
		code nullable:false 
		dateReservation nullable:false 
		livres nullable:true
    }
}
