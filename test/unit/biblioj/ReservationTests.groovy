package biblioj


import grails.test.mixin.*

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Reservation)
class ReservationTests {

	void testConstructeur() {
		Date d = new Date(2012, 5, 20, 13, 35)
		Reservation r = new Reservation(code: 25, dateReservation: d)
		assert r.validate()
		
	}
}
