package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class Mar1HotelSystemApplicationRepository {
    @Autowired
	EntityManager entityManager;
}
