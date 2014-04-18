package biblioj


class Parser {
	public static void parserCSV() {
		// Insert new codes
		def csv = new File("grails-app/conf/Les 1000 titres les plus recherches en 2012.csv")
		def livre
		csv.splitEachLine(';') { row ->
		   livre = Livre.findByTitre(row[1]) ?: new Livre(
			  titre: row[1],
			  nombreExemplaires: 3,
			  nombreExemplairesDisponibles: 3
		   ).save(failOnError: true, flush: true)
		}
	}
}
