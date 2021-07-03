##Plan
1. Entity types
2. Entity manager and persistence context
3. @Transactional
4.
5.
N + 1
L1, L2 cache
Dirty checking mechanism



##Tips
1. To execute an SQL script we can use schema.sql to set up some initial data.
2. @PersistenceContext 
3. We can use @NamedQueries in entities to execute JPQL queries using JPA.
4. *spring.jpa.properties.hibernate.generate_statistics=true* to show session statistics.
5. Entity types: 
    1. Transient (not represented in persistence context, doesn't have an identifier unless a generator is used).
    2. Managed (is associated in persistence context, may or may not be present in th DB yet).
    3. Detached (has an identifier, removed from persistence context).
    4. Removed (entity has an identifier, scheduled for removal from the DB).
   ![img.png](https://i.stack.imgur.com/CpfAe.png)
6. EntityManager.flush() sends all changes to the database before the transaction is committed.
7. EntityManager.detach() makes entity detached and removes from persistence context, so we need to merge() it to save changes after detach() invocation.
8. EntityManager.clear() clears the entire persistence context making all entities detached.
9. EntityManager.refresh() fetches data from the database and override existing entity state.
10. 