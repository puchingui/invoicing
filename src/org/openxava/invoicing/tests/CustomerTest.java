package org.openxava.invoicing.tests;

import org.openxava.tests.*;

public class CustomerTest extends ModuleTestBase {

	public CustomerTest(String testName) {
		super(testName, "Invoicing", "Customer");
	}
	
	public void testCreateReadUpdateDelete() throws Exception {
		//Crear
		execute("CRUD.new");
		setValue("number", "77");
		setValue("name", "JUNIT Customer");
		setValue("address.street", "JUNIT street");
		setValue("address.zipCode", "77555");
		setValue("address.city", "The JUNIT city");
		setValue("address.state", "The JUNIT state");
		
		execute("CRUD.save");
		assertNoErrors();
		assertValue("number", "");
		assertValue("address.street", "");
		assertValue("address.zipCode", "");
		assertValue("address.city", "");
		assertValue("address.state", "");
		
		//Leer
		setValue("number", "77");
		execute("CRUD.refresh");
		assertValue("number", "77");
		assertValue("name", "JUNIT Customer");
		assertValue("address.street", "JUNIT street");
		assertValue("address.zipCode", "77555");
		assertValue("address.city", "The JUNIT city");
		assertValue("address.state", "The JUNIT state");
		
		//Actualizar
		setValue("name", "JUNIT Customer MODIFIED");
		execute("CRUD.save");
		assertNoErrors();
		assertValue("number", "");
		assertValue("name", "");
		
		//Verifica si se ha modificado
		setValue("number", "77");
		execute("CRUD.refresh");
		assertValue("number", "77");
		assertValue("name", "JUNIT Customer MODIFIED");
		
		//Borrar
		execute("CRUD.delete");
		assertMessage("Customer deleted successfully");
	}

}
