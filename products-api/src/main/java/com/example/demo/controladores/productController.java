package com.example.demo.controladores;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Product;
import com.example.demo.reposities.productRepository;

@RestController
@RequestMapping(value = "/productos")
public class productController {

	@Autowired
	productRepository repository;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public Collection<Product> getListaProductos() {
		Iterable<Product> ListaProductos = repository.findAll();

		return (Collection<Product>) ListaProductos;

	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Product getProducto(@PathVariable(name = "id") Long id) {

		Optional<Product> producto = repository.findById(id);

		Product result = null;
		if (producto.isPresent()) {
			result = producto.get();
		}
		return result;
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Product createProducto(@RequestBody Product producto) {
		Product nuevoProducto = repository.save(producto);
		return nuevoProducto;

	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void deleteProducto(@PathVariable(name = "id") Long id) {
		repository.deleteById(id);

	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Product updateProducto(@PathVariable(name = "id") Long id, @RequestBody Product producto) {
		Optional<Product> oProducto = repository.findById(id);
		if (oProducto.isPresent()) {
			Product actual = oProducto.get();
			actual.setId(id);
			actual.setNombre(producto.getNombre());
			actual.setPrecio(producto.getPrecio());
			Product prodModificado = repository.save(actual);
			return prodModificado;
		}
		return null;

	}
}
