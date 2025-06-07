package gtics.lab7_20223209.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gtics.lab7_20223209.entity.Proveedor;
import gtics.lab7_20223209.repository.ProveedorRepository;

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

    //Editar
    @PostMapping(value = "/editar")
    public ResponseEntity<HashMap<String, Object>> editarProveedor(@RequestBody Proveedor proveedor) {

        HashMap<String, Object> responseMap = new HashMap<>();

        if(proveedor.getIdProveedor() > 0) {
            Optional<Proveedor> existingProveedor = proveedorRepository.findById(proveedor.getIdProveedor());

            if (existingProveedor.isPresent()) {

                proveedorRepository.save(proveedor);

                responseMap.put("estado", "editado");

                return ResponseEntity.status(HttpStatus.OK).body(responseMap);
            }
            else{
                responseMap.put("estado", "error");
                responseMap.put("mensaje", "Proveedor no encontrado");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
            }
        }
        else{
            responseMap.put("estado", "error");
            responseMap.put("mensaje", "Debe ingresar un ID válido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
        }
    }

    //Editar
    @PatchMapping(value = "/editar/{id}")
    public ResponseEntity<HashMap<String, Object>> editarProveedorParcial(@PathVariable("id") Integer id, @RequestBody Proveedor proveedor) {
        HashMap<String, Object> responseMap = new HashMap<>();
        Optional<Proveedor> optionalProveedor = proveedorRepository.findById(id);
        if (optionalProveedor.isPresent()) {
            Proveedor proveedorExistente = optionalProveedor.get();
            if (proveedor.getRazonSocial() != null) proveedorExistente.setRazonSocial(proveedor.getRazonSocial());
            if (proveedor.getNombreComercial() != null) proveedorExistente.setNombreComercial(proveedor.getNombreComercial());
            if (proveedor.getRuc() != null) proveedorExistente.setRuc(proveedor.getRuc());
            if (proveedor.getTelefono() != null) proveedorExistente.setTelefono(proveedor.getTelefono());
            if (proveedor.getCorreo() != null) proveedorExistente.setCorreo(proveedor.getCorreo());
            if (proveedor.getSitioWeb() != null) proveedorExistente.setSitioWeb(proveedor.getSitioWeb());
            if (proveedor.getDireccionFisica() != null) proveedorExistente.setDireccionFisica(proveedor.getDireccionFisica());
            if (proveedor.getPais() != null) proveedorExistente.setPais(proveedor.getPais());
            if (proveedor.getRepresentanteLegal() != null) proveedorExistente.setRepresentanteLegal(proveedor.getRepresentanteLegal());
            if (proveedor.getDniRepresentante() != null) proveedorExistente.setDniRepresentante(proveedor.getDniRepresentante());
            if (proveedor.getTipoProveedor() != null) proveedorExistente.setTipoProveedor(proveedor.getTipoProveedor());
            if (proveedor.getCategoria() != null) proveedorExistente.setCategoria(proveedor.getCategoria());
            if (proveedor.getFacturacionAnualDolares() != null) proveedorExistente.setFacturacionAnualDolares(proveedor.getFacturacionAnualDolares());
            proveedorRepository.save(proveedorExistente);
            responseMap.put("estado", "editado");
            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        } else {
            responseMap.put("estado", "error");
            responseMap.put("mensaje", "Proveedor no encontrado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
        }
    }

    //Eliminacion lógico
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<HashMap<String, Object>> eliminarLogicoProveedor(@PathVariable("id") Integer id) {
        HashMap<String, Object> responseMap = new HashMap<>();
        Optional<Proveedor> optionalProveedor = proveedorRepository.findById(id);
        if (optionalProveedor.isPresent()) {
            Proveedor proveedor = optionalProveedor.get();
            proveedor.setEstado(false);
            proveedorRepository.save(proveedor);
            responseMap.put("estado", "eliminado");
            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        } else {
            responseMap.put("estado", "error");
            responseMap.put("mensaje", "Proveedor no encontrado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
        }
    }
}
