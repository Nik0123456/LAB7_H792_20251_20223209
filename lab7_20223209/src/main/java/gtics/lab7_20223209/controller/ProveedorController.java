package gtics.lab7_20223209.controller;

import gtics.lab7_20223209.entity.Proveedor;
import gtics.lab7_20223209.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    ProveedorRepository proveedorRepository;

    //Listar
    @GetMapping(value = "/listar")
    public List<Proveedor> listarProveedores() {
        return proveedorRepository.findAll();
    }

    //Crear
    @PostMapping(value = "/crear")
    public ResponseEntity<HashMap<String, Object>> crearProveedor(@RequestBody Proveedor proveedor,
                                                                  @RequestParam(value = "fetchId", required = false) boolean fetchId){
        HashMap<String, Object> responseMap = new HashMap<>();

        proveedorRepository.save(proveedor);

        responseMap.put("id", proveedor.getIdProveedor());

        responseMap.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
    }

}
