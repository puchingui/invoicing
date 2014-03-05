package org.openxava.invoicing.tests;

import org.openxava.tests.*;

//90
public class AuthorTest extends ModuleTestBase {

	public AuthorTest(String testName) {
		super(testName, "Invoicing", "Author");
	}

	public void testReadAuthor() throws Exception {
		assertValueInList(0, 0, "JAVIER CORCOBADO");

		//al cambiar al modo detalle visualiza el primer objeto de la lista
		execute("Mode.detailAndFirst");
		assertValue("name", "JAVIER CORCOBADO");

		//tiene 2 productos
		assertCollectionRowCount("products", 2);
		
		//fila 0 de products tiene 2 en la columna number
		assertValueInCollection("products", 0, "number", "2");
		assertValueInCollection("products", 0, "description", "Arco iris de lágrimas");
		assertValueInCollection("products", 1, "number", "3");
		assertValueInCollection("products", 1, "description", "Ritmo de sangre");
	}

}
