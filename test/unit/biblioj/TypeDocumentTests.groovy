package biblioj



import grails.test.mixin.*
import groovy.xml.streamingmarkupsupport.BaseMarkupBuilder.Document;

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TypeDocument)
class TypeDocumentTests {

    void testConstructeur() {
		TypeDocument td1 = new TypeDocument(intitule:null)
		assert !td1.validate()
		TypeDocument td2 = new TypeDocument(intitule:"Livre pour ado")
		assert td2.validate()
		
    }
}
