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
6. 