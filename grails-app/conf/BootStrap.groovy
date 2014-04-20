import biblioj.*

class BootStrap {

    def init = { 
		
		RecuperationLivreService rl = new RecuperationLivreService()

		def result = new ArrayList<Livre>()
		Parser.parserCSV();
		

		result = rl.getByTitre("%rien%")
		println("\n\n\n")
		println(result)
		println(result.size())
		
		result = rl.getByNom('%Collins%')
		println("\n\n\n")
		println(result)
		println(result.size())

		result = rl.getByType('Livre ado')
		println("\n\n\n")
		println(result)
		println(result.size())
    }
    def destroy = {
    }
}
