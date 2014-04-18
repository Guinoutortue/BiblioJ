package biblioj



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Livre)
class LivreTests {

    void testConstructeur() {
		Livre l1 = new Livre(titre:"Le Nom du Vent", nombreExemplaires: 45, nombreExemplairesDisponibles: 12)
		assert l1.validate()
		Livre l2 = new Livre(titre:"Le Nom du Vent 2", nombreExemplaires: 45, nombreExemplairesDisponibles: 80)
		assert !l2.validate()
		Livre l3 = new Livre(titre:"Le Nom du Vent 3", nombreExemplaires: 5, nombreExemplairesDisponibles:-12)
		assert !l3.validate()	
    }
}
