import biblioj.*

class BootStrap {

    def init = { 
		
		RecuperationLivre rl = new RecuperationLivre()
		def result
		Parser.parserCSV();
		/*
		result = rl.getByTitre('%rien%')
		println("\n\n\n")
		println(result)
		result = rl.getByNom('%ber%')
		println("\n\n\n")
		println(result)
		*/
		result = rl.getByType('Livre ado')
		println("\n\n\n")
		println(result)
		
    }
    def destroy = {
    }
}
