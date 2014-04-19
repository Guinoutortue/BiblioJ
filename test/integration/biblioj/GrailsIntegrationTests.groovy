package biblioj

import static org.junit.Assert.*
import org.junit.*

class GrailsIntegrationTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testSomething() {
		def a = new Auteur(nom: "Guinedor", prenom: "Guillaume").save()
		new Livre(titre: "Moi, empereur", nombreExemplaires: 5, nombreExemplairesDisponibles: 5).save()
		assertTrue(Livre.findByTitre("Moi, empereur").getAuteurs()==null)
		Livre.findByTitre("Moi, empereur").addToAuteurs(Auteur.findByNom("Guinedor"))
		assertTrue(Livre.findByTitre("Moi, empereur").getAuteurs().size()==1)
	}
}
