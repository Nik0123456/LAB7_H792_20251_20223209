package gtics.lab7_20223209.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gtics.lab7_20223209.entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    @Query(value = "SELECT idProveedor, razonSocial, nombreComercial, ruc, telefono, correo, pais, representanteLegal, dniRepresentante, tipoProveedor, categoria, CASE WHEN estado = 1 THEN 'Activo' ELSE 'Inactivo' END AS estado FROM proveedor", nativeQuery = true)
    List<Proveedor> listarProveedoresSinCamposExcluidos();

    @Query(value = "SELECT idProveedor, razonSocial, nombreComercial, ruc, telefono, correo, pais, representanteLegal, dniRepresentante, tipoProveedor, categoria, CASE WHEN estado = 1 THEN 'Activo' ELSE 'Inactivo' END AS estado FROM proveedor WHERE idProveedor = ?1", nativeQuery = true)
    Proveedor buscarProveedorPorIdSinCamposExcluidos(Integer id);

}
