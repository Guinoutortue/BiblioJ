import biblioj.*

class BootStrap {

    def init = { 
		
		RecuperationLivreService rl = new RecuperationLivreService()
		def result
		Parser.parserCSV();
		
		result = rl.getByTitre("%rien%")
		println("\n\n\n")
		println(result)
		/*
		result = rl.getByNom('%ber%')
		println("\n\n\n")
		println(result)
		
		result = rl.getByType('Livre ado')
		println("\n\n\n")
		println(result)
		*/
    }
    def destroy = {
    }
}
