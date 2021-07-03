### Entity types
Entity types:
1. Transient (not represented in persistence context, doesn't have an identifier unless a generator is used).
2. Managed (has an identifier, is associated in persistence context, may or may not be present in th DB yet).
3. Detached (has an identifier, removed from persistence context).
4. Removed (entity has an identifier, scheduled for removal from the DB).

![img.png](https://i.stack.imgur.com/CpfAe.png)

### Persistence context
Persistence context is a storage of entities for transaction. Also used by Hibernate as L1 cache and cannot be disabled. Managed by Entity manager.

### Entity manager
Entity manager is an interface which provides operations with entities. Apart from CRUD operations it has:
1. EntityManager.flush() sends all changes to the database before the transaction is committed.
2. EntityManager.detach() makes entity detached and removes from persistence context, so we need to merge() it to save changes after detach() invocation.
3. EntityManager.clear() clears the entire persistence context making all entities detached.
4. EntityManager.refresh() fetches data from the database and override existing entity state.

### LazyInitialisationException
LazyInitialisationException occurs when we try to get some lazy loaded data when current session is closed. @Transactional may fix the issue.

### L1, L2 cache
L1 cache scope -> persistence context.
L2 cache scope -> multiple sessions / session factory.

### Dirty checking mechanism
Hibernate checks if during transaction there are any changes in all the persistence context entities and update them.

### N + 1 select problem solving
1. Eager fetch type.
2. Entity graph.
3. Join fetch