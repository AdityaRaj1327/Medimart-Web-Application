package com.softpro.MediMart.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softpro.MediMart.model.Response;

public interface ResponseRepository extends JpaRepository<Response, String > {

}
