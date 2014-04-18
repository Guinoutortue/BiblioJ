package biblioj



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Auteur)
class AuteurTests {

    void testConstructeur() {
       Auteur a = new Auteur(nom:"Guinedor", prenom:"Guillaume")
	   assert a.validate()
       a = new Auteur(nom:"Guinedor")
	   assert !a.validate()
       a = new Auteur(prenom:"Guillaume")
	   assert !a.validate()
    }
}
