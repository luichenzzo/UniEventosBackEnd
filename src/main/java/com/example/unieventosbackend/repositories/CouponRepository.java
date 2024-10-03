package com.example.unieventosbackend.repositories;

import com.example.unieventosbackend.model.documents.Coupon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends MongoRepository<Coupon, String> {
    // Buscar por "codigo"
    @Query
    List<Coupon> findByCodigo(String codigo);

    // Buscar por "nombre"
    @Query
    List<Coupon> findByNombre(String nombre);

    // Buscar por "porcentajeDescuento"
    @Query
    List<Coupon> findByPorcentajeDescuento(double porcentajeDescuento);

    // Buscar por "fechaVencimiento"
    @Query
    List<Coupon> findByFechaVencimiento(String fechaVencimiento);

    // Actualizar "codigo" por "id"
    @Query("{ '_id': ?0 } { $set: { 'codigo': ?1 } }")
    void updateCodigoById(String id, String codigo);

    // Actualizar "nombre" por "id"
    @Query("{ '_id': ?0 } { $set: { 'nombre': ?1 } }")
    void updateNombreById(String id, String nombre);

    // Actualizar "porcentajeDescuento" por "id"
    @Query("{ '_id': ?0 } { $set: { 'porcentajeDescuento': ?1 } }")
    void updatePorcentajeDescuentoById(String id, double porcentajeDescuento);

    // Actualizar "fechaVencimiento" por "id"
    @Query("{ '_id': ?0 } { $set: { 'fechaVencimiento': ?1 } }")
    void updateFechaVencimientoById(String id, String fechaVencimiento);

    // Eliminar por "id"
    void deleteById(String id);

    // Eliminar por "codigo"
    void deleteByCodigo(String codigo);
}
