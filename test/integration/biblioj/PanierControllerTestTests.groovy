package biblioj

import static org.junit.Assert.*
import org.junit.*

class PanierControllerTestTests {

	def cont
	

    @Before
    void setUp() {
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

	@Test
	void login(){
		
		cont = new PanierController()
		cont.params.username = "jordan"
		cont.login()
		assertTrue(Panier.findByUsername("jordan") != null)
		assertEquals '/', cont.response.redirectedUrl
		
		
		
	}
	
    @Test
    void ajouter(){
		/*
		cont = new PanierController()
		cont.session.user = "jordan"
		Panier p = new Panier(username:"jordan")
		cont.params.cache = "Limonov"
		cont.ajouter()
		assertTrue (Panier.findByUsername("jordan").getMeslivres() == 1)
		//assertEquals "/livre/list", cont.response.redirectedUrl
		 */

    }
	
	@Test
	void retirer(){
		/*
		cont = new PanierController()
		cont.params.username = "jordan"
		cont.login()
		cont.params.cache = "L'embrasement"
		cont.ajouter()
		cont.params.cache = "L'embrasement"
		cont.retirer()
		assertTrue (Panier.findByUsername("jordan").getMeslivres() == 0)
		assertEquals "/panier/list", cont.response.redirectedUrl
		*/
	}
}
