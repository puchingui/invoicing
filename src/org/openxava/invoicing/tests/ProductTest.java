package org.openxava.invoicing.tests;

import java.math.*;

import org.openxava.invoicing.model.*;
import org.openxava.tests.*;

import static org.openxava.jpa.XPersistence.*;

public class ProductTest extends ModuleTestBase {

	// Objetos a usar durante la prueba
	private Author author;
	private Category category;
	private Product product1;
	private Product product2;
	
	public ProductTest(String testName) {
		super(testName, "Invoicing", "Product");
	}
	
	// Preparar los datos para la prueba
	protected void setUp() throws Exception {
		createProducts();
		super.setUp();
	}
	
	// Se ejecuta siempre al terminar la prueba
	protected void tearDown() throws Exception {
		super.tearDown();
		removeProducts();
	}

	public void testRemoveFromList() throws Exception {
		//89
		setConditionValues(new String[] {"", "JUNIT"});
		setConditionComparators(new String[] {"=", "contains_comparator"});
		execute("List.filter");
		assertListRowCount(2);
		checkRow(1);
		execute("CRUD.deleteSelected");
		assertListRowCount(1);
	}
	
	public void testChangePrice() throws Exception {
		//89 Buscar product1
		execute("CRUD.new");
		setValue("number", Integer.toString(product1.getNumber()));
		execute("CRUD.refresh");
		assertValue("price", "10.00");
		
		//Cambia el precio
		setValue("price", "12.00");
		execute("CRUD.save");
		assertNoErrors();
		assertValue("price", "");
		
		//Verificar
		setValue("number", Integer.toString(product1.getNumber()));
		execute("CRUD.refresh");
		assertValue("price", "12.00");
	}
	
	public void createProducts() {
		//87 Crear objetos java
		author = new Author();
		author.setName("JUNIT Author");
		category = new Category();
		category.setDescription("JUNIT Category");
		
		product1 = new Product();
		product1.setNumber(901);
		product1.setDescription("JUNIT Product 1");
		product1.setAuthor(author);
		product1.setCategory(category);
		product1.setPrice(new BigDecimal("10"));
		
		product2 = new Product();
		product2.setNumber(902);
		product2.setDescription("JUNIT Product 2");
		product2.setAuthor(author);
		product2.setCategory(category);
		product2.setPrice(new BigDecimal("20"));
		
		// Marcar los objetos como persistentes
		getManager().persist(author);
		getManager().persist(category);
		getManager().persist(product1);
		getManager().persist(product2);
		
		/* Confirma los cambios en la base de datos
		 * commit() es de XPersistence. Graba todos los objetos en la
		 * base de datos y confirma la transaccion
		 */
		commit();
	}
	
	public void removeProducts() {
		//88
		remove(product1, product2, author, category);
		commit();
	}
	
	private void remove(Object ... entities) {
		//88 usamos argumentos varags
		for(Object entity : entities) {
			getManager().remove(getManager().merge(entity));
		}
	}
	
}
